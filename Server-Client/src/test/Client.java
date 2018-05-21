package test;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

	public static void main(String[] args) {
		final String GEP = "localhost";
		final int port = 12345;
		
		try {
			Socket s = new Socket(GEP,port);
			Scanner sc = new Scanner(s.getInputStream(), "utf-8");
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			
			pw.println("Hello Wordl!");
			pw.flush();
			
			String be = sc.nextLine();
			System.out.println(be);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
