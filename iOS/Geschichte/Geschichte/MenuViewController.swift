//
//  MenuViewController.swift
//  FlowingMenuExample
//
//  Created by Yannick LORIOT on 03/12/15.
//  Copyright © 2015 Yannick LORIOT. All rights reserved.
//

import UIKit

final class MenuViewController: UIViewController, UITableViewDataSource {
  @IBOutlet weak var topBar: UINavigationBar!
  @IBOutlet weak var backButtonItem: UIBarButtonItem!

  override func viewDidLoad() {
    super.viewDidLoad()

    topBar.tintColor = UIColor.whiteColor()
    topBar.barTintColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    topBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
    self.view.backgroundColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
  }

  // MARK: - Managing the Status Bar

  override func preferredStatusBarStyle() -> UIStatusBarStyle {
    return .LightContent
  }

  // MARK: - UITableView DataSource Methods

  func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return 0
  }

  func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
    let cell = tableView.dequeueReusableCellWithIdentifier("Cell")!

    cell.contentView.backgroundColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)

    return cell
  }
}
