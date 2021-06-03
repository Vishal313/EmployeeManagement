import java.util.Timer;

public class EmployeeManagement {

	public static void main(String[] args) {	
		
		backMeUp();				
		new ValidateUser();		
		String empLevel = ValidateUser.empLevel;
		
	}
	
	
	public static void backMeUp(){
		Timer time = new Timer();             
		Backup bk = new Backup(); 			
        time.schedule(bk, 0, 5000); // 5000ms is 5 seconds (60 X 60 X 1000) for 1 hour
        // this backups the file every 5 second
	}
}
