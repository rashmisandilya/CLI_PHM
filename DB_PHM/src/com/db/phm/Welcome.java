package com.db.phm;

import java.sql.SQLException;

import com.db.phm.SQLConnection;

public class Welcome {
	
	private SQLConnection sqlConn;
	
	public Welcome(SQLConnection sqlConn){
		this.sqlConn = sqlConn;
	}
	public Welcome(){
		
	}
	public void start() {
		// TODO Auto-generated method stub
		
		System.out.println("*************************************************************************************\n");
		System.out.println("\t\t\tWelcome to Personal Health Mangement System.\n");
		System.out.println("*************************************************************************************");
		homeScreen();
		
	}
	public void homeScreen (){
		try{		
		Helper.printMessage("Please enter your choice.");
		Helper.printMessage("1 Login");
		Helper.printMessage("2 Signup");
		Helper.printMessage("3 Exit");
		boolean flag = true;
		while(flag){
				int choice = Integer.parseInt(Helper.getConsoleValue());
				switch(choice){
				case 1:
					loginScreen(1);
					flag = false;
					break;
				case 2:
					signupScreen(2);
				case 0:
					Helper.printMessage("Successfully exited PHMS");
					flag = false;
					break;
				default:
					System.out.println("Invalid choice: Please enter your choice again.\n");						
				}
			}
		}
		catch(Exception e){
			Helper.printErrorMessage();
			homeScreen();
		}
	}
	
	public void loginScreen(int choice){
		try{
			boolean flag = true;
			while(flag){
					switch(choice){
					case 1:
						System.out.println("------Please Enter your login credentials------");
						try{
							Login login = new Login(sqlConn);
							flag= false;
							break;
						}
						catch(IncorrectCredentialException e){
							System.out.println(e.toString()+" Please try again.");
							flag = true;
							continue;
						}
					case 0:
						logout();
						flag = false;
						break;
					default:
						System.out.println("Invalid choice: Please enter again.");
							
					}
				}
		}
		catch(Exception e){
			System.out.println("Something went wroong!!! Please try again...");			
			loginScreen(0);

		}
	}
	
	public void signupScreen(int choice) throws SQLException{
		// TODO Auto-generated method stub
		try{
			boolean flag = true;
			while(flag){
					switch(choice){
					case 2:
						System.out.println("------Please Enter your details------");
						SignUp signup = new SignUp(sqlConn);
						flag= false;
						break;
					case 0:
						logout();
						flag = false;
						break;
					default:
						System.out.println("Invalid choice: Please enter again.");
							
					}
				}
		}
		catch(Exception e){
			System.out.println("Something went wroong!!! Please try again...");			
			loginScreen(0);

		}
		
	}
	
	public void logout() {
		Helper.printMessage("Logged out successfully !!!\n");
		homeScreen();
}
}
