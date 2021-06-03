import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.TimerTask;

public class Backup extends TimerTask{
	public Backup() {
		
	}

	public void run() {
		try {
			File sourceFile = new File("data/up.txt");
			File destinationFile = new File("backup/" + sourceFile.getName());
	
			FileInputStream fileInputStream = new FileInputStream(sourceFile);
			FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
	
			int bufferSize;
			byte[] bufffer = new byte[512];
			while ((bufferSize = fileInputStream.read(bufffer)) > 0) {
			    fileOutputStream.write(bufffer, 0, bufferSize);
			}
			fileInputStream.close();
			fileOutputStream.close();
			System.out.println("Backup Successfull");
		}
		catch (Exception e) {
			System.out.println("Error in Data Store");
		}		
		
	}
}
