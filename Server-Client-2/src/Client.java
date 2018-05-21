import java.util.*;
import java.io.*;
import java.net.*;

public class Client {
	
	public static void main(String[] args) {
		final String GEP = "localhost";
		final int PORT = 12345;
		
		try {
			Socket s = new Socket(GEP,PORT);
			Scanner sc = new Scanner(s.getInputStream(), "utf-8");
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			Scanner scFile = new Scanner(new File("in.txt"));
			PrintWriter pwFile = new PrintWriter(new File("out.txt"));
			
			while(scFile.hasNextInt()) {
				int num = scFile.nextInt();
				pw.println(num);
			}
			pw.println(0);
			pw.flush();
			
			while(sc.hasNextInt()) {
				int num = sc.nextInt();
				pwFile.println(num);
			}
			pwFile.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
