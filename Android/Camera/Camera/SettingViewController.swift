//
//  SettingViewController.swift
//  TimelineKeeping
//
//  Created by Le Thanh Tan on 9/28/16.
//  Copyright © 2016 Le Thanh Tan. All rights reserved.
//

import UIKit

class SettingViewController: UIViewController {

  @IBOutlet weak var ipTextField: UITextField!
  @IBOutlet weak var accountIdTextField: UITextField!
  @IBOutlet weak var timeoutTextField: UITextField!
  
  
  override func viewDidLoad() {
    super.viewDidLoad()
    let ip = NSUserDefaults.standardUserDefaults().objectForKey("ip") as? String ?? "192.168.43.93"
    ipTextField.text = ip
    
    let accoundId = NSUserDefaults.standardUserDefaults().objectForKey("accoundID") as? String ?? "4"
    accountIdTextField.text = accoundId
    
    let timeout = NSUserDefaults.standardUserDefaults().objectForKey("timeout") as? String ?? "15"
    timeoutTextField.text = timeout
  }

  @IBAction func onSaveTapped(sender: UIBarButtonItem) {
    let ip = ipTextField.text
    NSUserDefaults.standardUserDefaults().setObject(ip, forKey: "ip")
    
    let accoundId = accountIdTextField.text
    NSUserDefaults.standardUserDefaults().setObject(accoundId, forKey: "accoundID")
    
    let timeout = timeoutTextField.text
    NSUserDefaults.standardUserDefaults().setObject(timeout, forKey: "timeout")
    
    NSUserDefaults.standardUserDefaults().synchronize()
    ipTextField.resignFirstResponder()
    accountIdTextField.resignFirstResponder()
    timeoutTextField.resignFirstResponder()
  }
  
  
}
