package system;
import java.io.PrintWriter;
import model.Agent;

// Ebbõl lesznek majd leszármaztatva
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
		System.out.println("Az elküldött üzenet: " + msg);
	}
	
}
