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
import TSMarkdownParser

class MasterViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, FlowingMenuDelegate, UIViewControllerTransitioningDelegate {
    
    @IBOutlet weak var navigationBar: UINavigationBar!
    @IBOutlet weak var navItem: UINavigationItem!
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet var flowingMenuTransitionManager: FlowingMenuTransitionManager!
    
    var menu: UIViewController?
    var button: HamburgerButton!
    
    let PresentSegueName = "PresentMenuSegue"
    let ArticleSelectedNotification = "ArticleSelectedNotification"
    let mainColor = UIColor(red: 119.0/255.0, green: 84.0/255.0, blue: 72.0/255.0, alpha: 1.0)
    let derivatedColor = UIColor(red: 92.0/255.0, green: 64.0/255.0, blue: 56.0/255.0, alpha: 1.0)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        button = HamburgerButton(frame: CGRectMake(20, 15, navigationBar.frame.height * 1.5, navigationBar.frame.height))
        navigationBar.addSubview(button)
        
        flowingMenuTransitionManager.setInteractivePresentationView(view)
        flowingMenuTransitionManager.delegate = self
        
        navigationBar.tintColor = .whiteColor()
        navigationBar.barTintColor = derivatedColor
        navigationBar.titleTextAttributes = [NSForegroundColorAttributeName: UIColor.whiteColor()]
        navigationBar.layer.masksToBounds = false
        
        let sb = UIView(frame: CGRectMake(0, 0, self.view.frame.width, 20))
        sb.backgroundColor = derivatedColor
        self.view.addSubview(sb)
        
        NSNotificationCenter.defaultCenter().addObserver(self, selector: "reload", name: ArticleSelectedNotification, object: nil)
        
        // Article setup
        guard let article = Helper.sharedHelper.currentArticle else {print("damn");return}
        print("Article: \(article.contents)")
        
        navItem.title = article.title
        
        let margin:CGFloat = 10
        let title = UILabel(frame: CGRectMake(margin, margin, scrollView.frame.width - margin * 2, 50))
        title.textAlignment = NSTextAlignment.Center
        title.textColor = UIColor.darkGrayColor()
        title.font = UIFont(name: "AmericanTypewriter-Bold", size: 40)
        title.text = article.title
        scrollView.addSubview(title)
        
        let content = UILabel(frame: CGRectMake(margin, title.frame.origin.y + title.frame.height + margin, scrollView.frame.width - margin * 2, 300))
        content.textColor = UIColor.blackColor()
        content.font = UIFont(name: "AmericanTypewriter", size: 20)
        content.lineBreakMode = NSLineBreakMode.ByWordWrapping
        content.textAlignment = NSTextAlignment.Justified
        content.attributedText = article.contents
        scrollView.addSubview(content)
        
    }
    
    func reload() {
        presentViewController((self.storyboard?.instantiateInitialViewController())!, animated: false, completion: nil)
    }
    
    func showMenu() {
        setButtonState(!button.showsMenu)
        flowingMenuNeedsPresentMenu(flowingMenuTransitionManager)
    }
    
    func setButtonState(state: Bool) {
        self.button.showsMenu = !state
    }
    
    func testShowArticle() {
        let new = self.storyboard?.instantiateInitialViewController()
        new?.modalPresentationStyle = UIModalPresentationStyle.Custom
        new?.transitioningDelegate = self
        self.presentViewController(new!, animated: true, completion: nil
        )
    }
    
    
    // MARK: Transition Delegate
    func animationControllerForPresentedController(presented: UIViewController, presentingController presenting: UIViewController, sourceController source: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        let transition = JTMaterialTransition(animatedView: self.navigationBar)
        transition.reverse = false
        return transition
    }
    
    func animationControllerForDismissedController(dismissed: UIViewController) -> UIViewControllerAnimatedTransitioning? {
        let transition = JTMaterialTransition(animatedView: self.navigationBar)
        transition.reverse = true
        return transition
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
        
        setButtonState(true)
    }
    
    func flowingMenuNeedsDismissMenu(flowingMenu: FlowingMenuTransitionManager) {
        menu?.dismissViewControllerAnimated(true, completion: nil)
        setButtonState(false)
    }
    
    func flowingMenuTransitionCancelled() {
        setButtonState(false)
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

