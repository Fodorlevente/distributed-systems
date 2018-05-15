package system;
import java.io.PrintWriter;
import model.Agent;

// Ebb�l lesznek majd lesz�rmaztatva
public abstract class Node {
	protected Agent agent;
	
	public Node(Agent agent) {
		this.agent = agent;
	}
	
	protected void sendMessage(PrintWriter pw, String msg) {
		pw.println(msg);
		pw.flush();
	}
	
	protected void sendMessage(PrintWriter pw, int msg) {
		pw.println(msg);
		pw.flush();
	}
	
	protected void printMessage(String msg) {
		System.out.println("Az elk�ld�tt �zenet: " + msg);
	}
	
}
