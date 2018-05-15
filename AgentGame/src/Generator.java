import java.util.Random;

public class Generator {
	public static Random r;
	
	public static int generatePortNumber() {
		r = new Random();
		return r.nextInt(AgentMain.MAX_PORT - AgentMain.MIN_PORT) + AgentMain.MIN_PORT;
	}
	
	public static int generatePortNumber(int port) {
		r = new Random();
		int generatedPort = r.nextInt(AgentMain.MAX_PORT - AgentMain.MIN_PORT) + AgentMain.MIN_PORT;
		// Or -> int genereatedPort = generatePortNumber();
		while(port == generatedPort) {
			generatedPort = r.nextInt(AgentMain.MAX_PORT - AgentMain.MIN_PORT) + AgentMain.MIN_PORT;
		}
		return generatedPort;
	}
	
	public static int generateTimeoutNumber() {
		r = new Random();
		return r.nextInt(AgentMain.MAX_TIMEOUT - AgentMain.MIN_TIMEOUT) + AgentMain.MIN_TIMEOUT;
	}
	
	
}
