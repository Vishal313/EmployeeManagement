import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class EmployeeManagement {
	
	public static void main(String[] args) {	
		
//		backMeUp();				
		new ValidateUser();		
		String empLevel = ValidateUser.empLevel;
		displayRoleWise(empLevel);
	}
	
	public static void displayRoleWise(String empLevel){
		Scanner sc = new Scanner(System.in);
		
		int choice = 1;
		
		switch (empLevel) {
			case "HR":
				
				while (choice != 4) {
					System.out.println("---------------------------");
					System.out.println("1. Add Employee");
					System.out.println("2. Delete Employee");
					System.out.println("3. View All Employees");
					System.out.println("4. Logout");
					System.out.println("---------------------------");
					System.out.print("Enter Your Choice: ");
					choice = sc.nextInt();	
					sc.nextLine();
					if (choice == 1) {
						System.out.print("Enter Name: ");
						String name = sc.nextLine();
						System.out.print("Enter Employee ID: ");
						int id = sc.nextInt();
						sc.nextLine();
						System.out.print("Enter Designation: ");
						String dsgn = sc.nextLine();
						try {
				            FileOutputStream f = new FileOutputStream(new File("data/Employee.txt"), true);
				            ObjectOutputStream o = new ObjectOutputStream(f);
				            o.writeObject(new Employee(name, id, dsgn));
				            o.close();
				            f.close();		
						} 
						catch (FileNotFoundException e) {
				            System.out.println("File not found");
				        } catch (IOException e) {
				            System.out.println("Error initializing stream");
				        } 
					}
					if (choice == 2) {
						System.out.print("Enter Employee ID to Delete: ");
						int id = sc.nextInt();
						ArrayList<Employee> emplist = new ArrayList<Employee>();
						try {
							FileInputStream fi = new FileInputStream(new File("data/Employee.txt"));
				            ObjectInputStream oi = new ObjectInputStream(fi);
				            boolean keepReading = true;
				            try {
					            while (keepReading) {
					            	Employee e1 = (Employee) oi.readObject();
					            	if (e1.emp_id != id) {
					            		emplist.add(e1);
					            	}
						            oi = new ObjectInputStream(fi);
					            }
				            } catch(EOFException e) {
				                keepReading = false;
				            } catch (Exception ex) {
				                ex.printStackTrace();
				            }
				            oi.close();
				            fi.close();
						}
						catch (FileNotFoundException e) {
				            System.out.println("File not found");
				        } catch (IOException e) {
				            System.out.println("Error initializing stream");
				        }
						
						try {
							new FileOutputStream("data/Employee.txt").close(); // clear existing file
				            FileOutputStream f = new FileOutputStream(new File("data/Employee.txt"));
				            ObjectOutputStream o = new ObjectOutputStream(f);
				            
				            for (int i = 0; i < emplist.size(); i++) { 
				            	o.writeObject(new Employee(emplist.get(i).emp_name, emplist.get(i).emp_id, emplist.get(i).emp_designation));
//				            	o = new ObjectOutputStream(f);
				            }
				            o.close();
				            f.close();		
						} 
						catch (FileNotFoundException e) {
				            System.out.println("File not found");
				        } catch (IOException e) {
				            System.out.println("Error initializing stream");
				        }  
						
					}
					if (choice == 3) {
						try {
							FileInputStream fi = new FileInputStream(new File("data/Employee.txt"));
				            ObjectInputStream oi = new ObjectInputStream(fi);
				            boolean keepReading = true;
				            try {
					            while (keepReading) {
					            	Employee e = (Employee) oi.readObject();
						            System.out.println(e.toString());
						            oi = new ObjectInputStream(fi);
					            }
				            } catch(EOFException e) {
				                keepReading = false;
				            } catch (Exception ex) {
				                ex.printStackTrace();
				            } 
				            oi.close();
				            fi.close();
						}
						catch (FileNotFoundException e) {
				            System.out.println("File not found");
				        } catch (IOException e) {
				            System.out.println("Error initializing stream");
				        } 
					}
				}
				break;
			case "Manager":
				break;
			case "TL":
				break;
			case "Employee":
				break;
			
		}
		
	}
	
	public static void backMeUp(){
		Timer time = new Timer();             
		Backup bk = new Backup(); 			
        time.schedule(bk, 0, 5000); // 5000ms is 5 seconds (60 X 60 X 1000) for 1 hour
        // this backups the file every 5 second
	}
}
