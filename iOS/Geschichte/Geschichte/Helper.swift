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
                let response = try NSData(contentsOfURL: NSURL(string: url)!, options: NSDataReadingOptions.DataReadingMappedIfSafe)
                let json = JSON(data: response)
                
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
    let title:String?
    let author:String?
    let contents:NSAttributedString?
}