//
//  MenuViewController.swift
//  GeschichteApp
//
//  Created by Finn Gaida on 03/12/15.
//  Copyright © 2015 Finn Gaida. All rights reserved.
//

import UIKit
import SwiftyJSON

final class MenuViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var topBar: UINavigationBar!
    @IBOutlet weak var backButtonItem: UIBarButtonItem!
    @IBOutlet weak var tableView: UITableView!
    var json:JSON?
    
    let mainColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    let derivatedColor = UIColor(red: 92.0/255.0, green: 64.0/255.0, blue: 56.0/255.0, alpha: 1.0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        topBar.tintColor = UIColor.whiteColor()
        topBar.barTintColor = derivatedColor
        topBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
        topBar.layer.masksToBounds = false
        
        let sb = UIView(frame: CGRectMake(0, 0, self.view.frame.width, 20))
        sb.backgroundColor = derivatedColor
        self.view.addSubview(sb)
        
        Helper.getJSON { (result) -> () in
            self.json = result
            self.tableView.reloadData()
        }

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
    }
}
