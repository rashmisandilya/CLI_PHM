package com.db.phm;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

public class Patient extends User{
	
	public String username = null;
	public String pwd = null;
	public String firstname = null;
	public String lastname = null;
	public String isSick = null;
	
	public Patient(String username) {
		super(username);
		// TODO Auto-generated constructor stub
		this.username = username;
		
	}

	public Patient(String username, String firstname, String lastname, String isSick) {
		super(username, firstname, lastname);
		// TODO Auto-generated constructor stub
		this.username = username;
		this.pwd = pwd;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isSick = isSick;
		showMenuItems();
	}
	
	public  void showMenuItems() {
		System.out.println("Please select from the below options: ");
		System.out.println("\n1. Profile \t\t\t\t 2. Diagnoses");
		System.out.println("3. Health Indicator \t\t 4. Alerts");
		System.out.println("5. Health Supporters\t\t\t\t 6. Logout");
		
		selectAnAction();
		
		System.out.println("Please select from the below options: ");
		System.out.println("\n1. Go back to Menu \t\t\t\t 2. Logout");
		Scanner scanner = new Scanner(System.in);
		String value = scanner.nextLine();
		int choice = Integer.parseInt(value);
		switch(choice){
		case 1:
			showMenuItems();
			break;
		case 2:
			Welcome wc = new Welcome();
			wc.logout();
			break;
		}
	}
	
	public  void selectAnAction() {
		//Diagnoses resource = new Diagnoses(this.userName, this.userType);
		try{
			boolean flag = true;
			while(flag){
					//@SuppressWarnings("Diagnoses")
					Scanner scanner = new Scanner(System.in);
					String value = scanner.nextLine();
					int choice = Integer.parseInt(value);
					switch(choice){
					case 1:
						//Profile
						showProfile();
						flag=false;
						break;
					case 2:
						//Diagnoses
						Diagnoses dd = new Diagnoses(username,firstname,lastname, isSick);
						dd.showDiseaseDiagnosed();
						flag = false;
						break;
					case 3:
						//Health Indicators. Show observation table for each health indicator Ex-weight, blood pressure, etc applicable to a person
						Observation obs = new Observation(username,firstname,lastname, isSick);
						obs.showObservationReading();
						flag = false;
						break;
					case 4:
						//Alerts
						Alerts al = new Alerts(username, firstname, lastname, isSick);
						al.showAlerts();
						flag = false;
						break;
					case 5:
						//Health Supporters
						showHealthSupporter();
						flag = false;
						break;
					case 6:
						Welcome wc = new Welcome();
						wc.logout();
						flag = false;
						break;
					default:
						System.out.println("Invalid choice: Please enter again.");
							
					}
				}
			}
			catch(Exception e){
				System.out.println("Something bad happened!!! Please try again...");
				showMenuItems();

			}
		}
	private void showHealthSupporter() {
		// TODO Auto-generated method stub
		
	}

	protected void showProfile(){
		PreparedStatement preparedStatement = null;
		System.out.println("In show profile");
		try{
			String selectSQL = "SELECT Gender, Apartment, Street, City, State, zipcode, DOB, isHS, isSick FROM Users WHERE UserId = ? and password = ?";
			System.out.println("selectSQL \n" + selectSQL);
			System.out.println("username \n" + username);
			SQLConnection sqlcon = new SQLConnection();
			preparedStatement = sqlcon.conn.prepareCall(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, pwd);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next() ) {
				System.out.println("Not a valid user id.");
				return;
			}
			else
			{	
			  while(rs.next())
			  {
				String gender = rs.getString("Gender");
				String apartment = rs.getString("Apartment");
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zipcode = rs.getString("zipcode");
				java.sql.Date dbSqldob  = rs.getDate("DOB");
				 // debug print and check here
				System.out.println("dbSqldob : "+dbSqldob);
				java.util.Date dob = new java.util.Date(dbSqldob.getTime());
				System.out.println("dob : "+dob);
				String isHS = rs.getString("isHS");
				String isSick = rs.getString("isSick");
				String patientCategory = null;
				String userType= null;
				if(isSick.equals("1"))
					patientCategory = "sick";
				else
					patientCategory = "well";
				
				if(isHS.equals("1"))
					userType = "patient and Health Supporter";
				else
					userType = "patient";
				

	            System.out.println("User ID: "+username +"\n" + 
	            					"Name: "+firstname+"\t \t"+lastname+"\n" + 
	            					"Gender: "+gender +"\n"+
	            					"Date Of Birth: "+dob +"\n"+
	            					"Address: "+street +", "+apartment+", "+city+", "+state+"- "+zipcode +"\n"+
	            					"Patient Category: "+patientCategory +"\n" +
	            					"User Type: "+userType);

	            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	            				
			 }
			  System.out.println("Do you want to update profile?  Enter 1 \n "
			  		+ "Back to Menu: Enter 2\n");
				Scanner scanner = new Scanner(System.in);
				int value = Integer.parseInt(scanner.nextLine());
				switch(value)
				{
				case 1: 
					updateProfileMenu();
					break;
				case 2:
					showMenuItems();
					break; 
				}	
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong. Try again!");
			updateProfileMenu();
			}	

}

	private void updateProfileMenu() {
		// TODO Auto-generated method stub
		
	}
}
