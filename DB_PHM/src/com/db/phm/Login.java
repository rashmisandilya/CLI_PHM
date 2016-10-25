package com.db.phm;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Login {
	
	private SQLConnection sqlConn;
	
	public Login(SQLConnection sqlConn) throws IncorrectCredentialException{
		
		this.sqlConn = sqlConn;
		// TODO Auto-generated constructor stub
		try{
			validateLogin();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public Login(){
		
	}
	private void validateLogin() throws IncorrectCredentialException{
		
		String username = null;
		String password = null;
		System.out.println("Enter user id:");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		username = scanner.nextLine();
		System.out.println("Enter password:");
		password = scanner.nextLine();
		
		//System.out.println("Username is " + username + " and password is " + password);
		try {
		validateUserLogin(username, password);
		
		} 
		catch(IncorrectCredentialException e){
			throw e;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void validateUserLogin(String username, String password) throws IncorrectCredentialException, SQLException {
		// TODO Auto-generated method stub
		
		String pwd = null;
		String isPatient = null;
		String isHS = null;
		String isSick = null;
		String firstname = null;
		String lastname = null;
       
			PreparedStatement preparedStatement = null;
			try{
				String selectSQL = "SELECT FirstName, LastName, password, isPatient, isHS, isSick FROM Users WHERE UserId = ? and password = ?";
				SQLConnection sqlcon = new SQLConnection();
				preparedStatement = sqlcon.conn.prepareCall(selectSQL);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, password);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					
					 firstname = rs.getString("FirstName");
					 lastname = rs.getString("LastName");
					 pwd = rs.getString("password");
					 isPatient = rs.getString("isPatient");
					 isHS = rs.getString("isHS");
					 isSick = rs.getString("isSick");
					System.out.println("firstname : " + firstname);
					System.out.println("lastname : " + lastname);					
					System.out.println("firstname : " + firstname);
					System.out.println("pwd : " + pwd);
					System.out.println("ispatient : " + isPatient);
					System.out.println("isHS : " + isHS);
					System.out.println("isSick : " + isSick);
				}
				if(pwd == null)
					throw new IncorrectCredentialException();
				if(pwd != null && password.equals(pwd))
				{
					Helper.printMessage("Login successful");
					if(Integer.parseInt(isPatient) == 1)
					{
						//Helper.printMessage("Need to show patient view");
						patientScreen(username, firstname, lastname, isSick);
					}
					else if(Integer.parseInt(isHS) == 1)
					{
						//Helper.printMessage("Need to show Health supporter view");
						healthSupporterScreen(username, firstname, lastname);
					}
					else
					{
						// if both patient and health supporter, show the patient view 
						Helper.printMessage("Need to show patient view");
					}						
					
				}
		}
		catch (IncorrectCredentialException e){
			System.out.println(e.toString()+" Please try again.");
			Welcome wc = new Welcome();
			wc.homeScreen();
		}
		catch (SQLException e) {
			throw e;
		}
			finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				}
}

	private void healthSupporterScreen(String username, String firstname, String lastname) {
		// TODO Auto-generated method stub
		//System.out.println("Welcome "+firstname +" " +lastname);
		//HealthSupporter hs = new HealthSupporter(username,firstname, lastname);
		//
	}

	private void patientScreen(String username, String firstname, String lastname, String isSick) {
		// TODO Auto-generated method stub
		//System.out.println("Welcome "+firstname +" " +lastname);
		Patient patient = new Patient(username,firstname, lastname, isSick);
	}
}
		
