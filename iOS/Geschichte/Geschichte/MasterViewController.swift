//
//  MasterViewController.swift
//  Geschichte
//
//  Created by Finn Gaida on 20.01.16.
//  Copyright © 2016 Finn Gaida. All rights reserved.
//

import UIKit
import JTMaterialTransition
import SAConfettiView
import FlowingMenu

class MasterViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, FlowingMenuDelegate {
    
    @IBOutlet weak var navigationBar: UINavigationBar!
    @IBOutlet var flowingMenuTransitionManager: FlowingMenuTransitionManager!
    
    let PresentSegueName = "PresentMenuSegue"
    let mainColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    let derivatedColor = UIColor(red: 92.0/255.0, green: 64.0/255.0, blue: 56.0/255.0, alpha: 1.0)
    
    var menu: UIViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let button = UIButton(frame: CGRectMake(0, 0, navigationBar.frame.height * 1.5, navigationBar.frame.height))
        button.setImage(UIImage(named: ""), forState: UIControlState.Normal)
        button.setImage(UIImage(named: ""), forState: UIControlState.Highlighted)
        navigationBar.addSubview(button)
        
        flowingMenuTransitionManager.setInteractivePresentationView(view)
        flowingMenuTransitionManager.delegate = self
        
        navigationBar.tintColor = .whiteColor()
        navigationBar.barTintColor = mainColor
        navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
        
        let sb = UIView(frame: CGRectMake(0, 0, UIScreen.mainScreen().bounds.width, 20))
        sb.backgroundColor = derivatedColor
        self.view.addSubview(sb)
        
        self.view.backgroundColor = UIColor.lightGrayColor()
    }
    
    func showMenu() {
        performSegueWithIdentifier("PresentMenuSegue", sender: self)
    }
    
    // MARK: - Interacting with Storyboards and Segues
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if segue.identifier == PresentSegueName {
            let vc = segue.destinationViewController
            vc.transitioningDelegate = flowingMenuTransitionManager
            
            flowingMenuTransitionManager.setInteractiveDismissView(vc.view)
            
            menu = vc
        }
    }
    
    @IBAction func unwindToMainViewController(sender: UIStoryboardSegue) {
    }
    
    // MARK: - Managing the Status Bar
    
    override func preferredStatusBarStyle() -> UIStatusBarStyle {
        return .LightContent
    }
    
    // MARK: - FlowingMenu Delegate Methods
    
    func colorOfElasticShapeInFlowingMenu(flowingMenu: FlowingMenuTransitionManager) -> UIColor? {
        return mainColor
    }
    
    func flowingMenuNeedsPresentMenu(flowingMenu: FlowingMenuTransitionManager) {
        performSegueWithIdentifier(PresentSegueName, sender: self)
    }
    
    func flowingMenuNeedsDismissMenu(flowingMenu: FlowingMenuTransitionManager) {
        menu?.dismissViewControllerAnimated(true, completion: nil)
    }

    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


    // MARK: - Table View
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }

    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell", forIndexPath: indexPath)

        return cell
    }

    func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }


}

