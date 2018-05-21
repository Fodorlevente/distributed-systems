package test;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		final int port = 12345;
		
		try {
			ServerSocket ss = new ServerSocket(port);
			Socket s = ss.accept();
			Scanner sc = new Scanner(s.getInputStream(), "utf-8");
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			String be = sc.nextLine();
			System.out.println(be);
			pw.println(be);
			pw.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
