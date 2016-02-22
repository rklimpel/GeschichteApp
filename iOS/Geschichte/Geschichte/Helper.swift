//
//  Helper.swift
//  Geschichte
//
//  Created by Finn Gaida on 22.01.16.
//  Copyright © 2016 Finn Gaida. All rights reserved.
//

import UIKit
import SwiftyJSON

class Helper: NSObject {
    
    static let sharedHelper = Helper()
    
    var currentArticle:Article?
    
    class func getJSON(completion: (JSON) -> ()) {
        
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0)) { () -> Void in
            
            do {
                let url = "http://finngaida.de/school/geschichte/index.json"
                let remote = try NSData(contentsOfURL: NSURL(string: url)!, options: NSDataReadingOptions.DataReadingMappedIfSafe)
                let local = try NSData(contentsOfFile: NSBundle.mainBundle().pathForResource("index", ofType: "json")!, options: NSDataReadingOptions.DataReadingMappedIfSafe)
                let json = JSON(data: remote)
                
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    completion(json)
                })
                
            } catch {
                
                dispatch_async(dispatch_get_main_queue(), { () -> Void in
                    print("Error: \(error)")
                    completion(nil)
                })
            }
            
        }
    }
}

struct Article {
    let section: String?
    let title:String?
    let author:String?
    let contents:NSAttributedString?
}

extension UIFont {
    func heightOfString (string: NSAttributedString, constrainedToWidth width: CGFloat) -> CGFloat {
        return string.string.boundingRectWithSize(CGSize(width: width, height: CGFloat(FLT_MAX)),
            options: NSStringDrawingOptions.UsesLineFragmentOrigin,
            attributes: [NSFontAttributeName: self],
            context: nil).size.height
    }
}