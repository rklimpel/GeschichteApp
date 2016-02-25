//
//  MenuViewController.swift
//  GeschichteApp
//
//  Created by Finn Gaida on 03/12/15.
//  Copyright © 2015 Finn Gaida. All rights reserved.
//

import UIKit
import SwiftyJSON
import TSMarkdownParser

final class MenuViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var topBar: UINavigationBar!
    @IBOutlet weak var backButtonItem: UIBarButtonItem!
    @IBOutlet weak var tableView: UITableView!
    var button:HamburgerButton?
    
    var json:JSON?
    
    let mainColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    let derivatedColor = UIColor(red: 92.0/255.0, green: 64.0/255.0, blue: 56.0/255.0, alpha: 1.0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        topBar.tintColor = UIColor.whiteColor()
        topBar.barTintColor = derivatedColor
        topBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
        
        let sb = UIView(frame: CGRectMake(0, 0, 2000, 20))
        sb.backgroundColor = derivatedColor
        self.view.addSubview(sb)
        
        let headerImg = UIImageView(image: UIImage(named: "mauersprung"))
        headerImg.frame = CGRectMake(0, -(self.view.frame.width * 0.5), self.view.frame.width, self.view.frame.width * 0.5)
        tableView.contentOffset = CGPointMake(0, headerImg.frame.height)
        tableView.addSubview(headerImg)
        
        Helper.getJSON { (result) -> () in
            self.json = result
            self.tableView.reloadData()
        }
        
    }
    
    override func viewWillAppear(animated: Bool) {
        button = HamburgerButton(frame: CGRectMake(230, 15, topBar.frame.height * 1.5, topBar.frame.height))
        button!.addTarget(self, action: "hide", forControlEvents: UIControlEvents.TouchUpInside)
        topBar.addSubview(button!)
    }
    
    override func viewDidAppear(animated: Bool) {
        
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, Int64(0.01 * Double(NSEC_PER_SEC))), dispatch_get_main_queue()) { () -> Void in
            self.button!.showsMenu = false
        }
    }
    
    func hide() {
        dismissViewControllerAnimated(true, completion: nil)
    }
    
    // MARK: - Managing the Status Bar
    
    override func preferredStatusBarStyle() -> UIStatusBarStyle {
        return .LightContent
    }
    
    // MARK: - UITableView DataSource Methods
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return json?["sections"].count ?? 0
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return json?["sections"][section]["articles"].count ?? 0
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return json?["sections"][section]["title"].string
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: UITableViewCellStyle.Default, reuseIdentifier: "Cell")
        
        cell.textLabel?.text = json?["sections"][indexPath.section]["articles"][indexPath.row]["title"].string
        
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        tableView.deselectRowAtIndexPath(indexPath, animated: true)
        
        let section = json?["sections"][indexPath.section]["title"].stringValue
        let title = json?["sections"][indexPath.section]["articles"][indexPath.row]["title"].stringValue
        let author = json?["sections"][indexPath.section]["articles"][indexPath.row]["author"].stringValue
        let content = json?["sections"][indexPath.section]["articles"][indexPath.row]["text"].stringValue
        
        let parser = TSMarkdownParser.standardParser()
        parser.paragraphFont = UIFont(name: "Vollkorn", size: 20)
        let contents = parser.attributedStringFromMarkdown(content, attributes: nil)
        let article = Article(section: section, title: title, author: author, contents: contents)
        
        Helper.sharedHelper.currentArticle = article
        
        dismissViewControllerAnimated(true) { () -> Void in
            NSNotificationCenter.defaultCenter().postNotificationName("ArticleSelectedNotification", object: nil)
        }
    }
}
