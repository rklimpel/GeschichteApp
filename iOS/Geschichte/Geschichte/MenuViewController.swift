//
//  MenuViewController.swift
//  GeschichteApp
//
//  Created by Finn Gaida on 03/12/15.
//  Copyright © 2015 Finn Gaida. All rights reserved.
//

import UIKit

final class MenuViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet weak var topBar: UINavigationBar!
    @IBOutlet weak var backButtonItem: UIBarButtonItem!
    @IBOutlet weak var tableView: UITableView!
    
    let mainColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    let derivatedColor = UIColor(red: 92.0/255.0, green: 64.0/255.0, blue: 56.0/255.0, alpha: 1.0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        topBar.tintColor = UIColor.whiteColor()
        topBar.barTintColor = mainColor
        topBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
        topBar.layer.masksToBounds = false
        
        let sb = UIView(frame: CGRectMake(0, 0, self.view.frame.width, 20))
        sb.backgroundColor = derivatedColor
        self.view.addSubview(sb)
    }
    
    // MARK: - Managing the Status Bar
    
    override func preferredStatusBarStyle() -> UIStatusBarStyle {
        return .LightContent
    }
    
    // MARK: - UITableView DataSource Methods
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0: return 5
        case 1: return 6
        case 2: return 4
        default: return 0
        }
    }
    
    func tableView(tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch section {
        case 0: return "Mauerbau"
        case 1: return "Planwirtschaft"
        case 2: return "Marktwirtschaft"
        default: return ""
        }
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: UITableViewCellStyle.Default, reuseIdentifier: "Cell")
        
        cell.textLabel?.text = "\(indexPath)"
        
        return cell
    }
}
