package com.db.phm;

import com.db.phm.SQLConnection;
import com.db.phm.Welcome;

public class MainClass {
	
    public static void main(String[] args) {
    	
    	//System.out.println("Hello Rashmi");
    	SQLConnection conn = new SQLConnection();
    	Welcome console = null;
		try {
			console = new Welcome(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	console.start();
    }
    
}
