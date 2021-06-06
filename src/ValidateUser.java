import java.io.File;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ValidateUser {
	private static Scanner x;
	public static String empLevel = "";
	public static String name = "";
	
	public ValidateUser() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		boolean login = true;
		String username = "";
		String password = "";
		String filepath = "data/up.txt";
		
		while (login) {
			System.out.print("Enter Your UserName: ");
			username = sc.nextLine();
			System.out.print("Enter Your Password: ");
			password = sc.nextLine();
			password = getMd5(password);
			login = verifyLogin(username, password, filepath);
		}
	}
	
	public static boolean verifyLogin(String username, String password, String filepath) {
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
		String role = "";
		try {
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while (x.hasNext() && !found) {
				tempUsername = x.next().trim();
				tempPassword = x.next().trim();
				role = x.next().trim();
				
				if (tempUsername.equals(username.trim()) && tempPassword.equals(password.trim())) {
					found = true;
					empLevel = role;
					name = tempUsername;
				}
			}
			x.close();
			if (found) {
				System.out.println("| Logged in as " + role.trim() + " |");
			}
			else {
				System.out.println("Login Failed");
				System.out.println("-------------------------------");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Error in Data Store");
		}
		
		return !found;
	}
	
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			  
	        byte[] messageDigest = md.digest(input.getBytes());
	
	        BigInteger no = new BigInteger(1, messageDigest);
	
	        String hashtext = no.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
		}
	}

}
