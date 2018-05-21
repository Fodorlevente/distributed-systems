import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		
		final int PORT = 12345;
		
		try {
			while(true) {
				ServerSocket ss = new ServerSocket(PORT);
				processNewClient(ss);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private static void processNewClient(ServerSocket ss) {
		try {
			Socket s = ss.accept();
			Scanner sc = new Scanner(s.getInputStream(), "utf-8");
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			while(sc.hasNextInt()) {
				int num = sc.nextInt();
				if(num == 0) {
					break;
				}
				
				int result = f(num);
				pw.println(result);
			}
			pw.flush();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static int f(int num) {
		return num * 2;
	}

}
