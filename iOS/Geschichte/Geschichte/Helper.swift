//
//  Helper.swift
//  Geschichte
//
//  Created by Finn Gaida on 22.01.16.
//  Copyright © 2016 Finn Gaida. All rights reserved.
//

import UIKit

class Helper: NSObject {
    class func getJSON() -> Dictionary<String,String> {
        
        let url = "http://finngaida.de/schule/geschichte"
        let response = NSString(contentsOfURL: NSURL(string: url)!, encoding: NSUTF8StringEncoding)
        
    }
}
