package Runnable;

import model.Agency;
import model.Agent;
import java.util.ArrayList;
import java.util.List;
import Runnable.Generator;

public class AgentMain {
	
	// min max várakozási idõ:
	public static int MIN_TIMEOUT;
	public static int MAX_TIMEOUT;
	// konstansok
	public static final String ADRESS = "localhost";
	public static final int MIN_PORT = 20095;
	public static final int MAX_PORT = 20100;
	
	public static void main(String[] args) {
		List<Thread> agentThreads = new ArrayList<>();
		
		try {
			System.out.println("Ügynökjáték betöltése...");
			
			int aAgents = Integer.parseInt(args[0]);
			int bAgents = Integer.parseInt(args[1]);
			System.out.println("1. ügynökség ügynökeinek száma: " + aAgents);
			System.out.println("2. ügynökség ügynökeinek száma: " + bAgents);
			
			int minTime = Integer.parseInt(args[2]);
			int maxTime = Integer.parseInt(args[3]);
			MIN_TIMEOUT = minTime;
			MAX_TIMEOUT = maxTime;
			System.out.println("Timeout:");
			System.out.println("\tAlsó korlát: " + MIN_TIMEOUT);
			System.out.println("\tFelsõ korlát: " + MAX_TIMEOUT);
			
			Agency aAgency = constructAgency("A", 1, aAgents);
			Agency bAgency = constructAgency("B", 2, bAgents);
			
			agentThreads.addAll(makeThreads(aAgency.getAgents()));
			agentThreads.addAll(makeThreads(bAgency.getAgents()));
			
			for (Thread thread: agentThreads) {
				thread.start();
			}
			
			while (aAgency.isAgentAvailable() && bAgency.isAgentAvailable() && !aAgency.isAllSecreatRevealed(bAgency.getSecrets()) && !bAgency.isAllSecreatRevealed(aAgency.getSecrets())) {
				Thread.sleep(1000);
			}
			
			System.out.println("Játék vége!");
			
			aAgency.setGameOver(true);
			bAgency.setGameOver(true);
			
			printScore(aAgency,bAgency);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		


	}
	
	public static Thread makeAgentThread(Agent agent) {
		return new Thread(() -> {
			agent.threadStart();
		});
	}
	
	private static Agency constructAgency(String name, int code, int agents) {
		Agency agency = new Agency(name, code);
		System.out.println("Új ügynökség: " + name);
		for (int i = 0; i < agents; i++) {
			agency.addAgent(new Agent(agency, i + 1));
		}
		System.out.println("Új ügynök: " + agents + "a(z) " + name + "Ügynökségnél");
	
		return agency;
	}
	
	private static List<Thread> makeThreads(List<Agent> agents) {
		List<Thread> threads = new ArrayList<>();
		
		for (Agent agent: agents) {
			threads.add(makeAgentThread(agent));
		}
		
		return threads;
	}
	
	private static void printScore(Agency agencyA, Agency agencyB) {
		if (agencyA.isAllSecreatRevealed(agencyB.getSecrets())) {
			System.out.println("A ügynökség nyert!");
		} else if (agencyB.isAllSecreatRevealed(agencyA.getSecrets())) {
			System.out.println("B ügynökség nyert!");
		} else if (agencyA.isAgentAvailable()) {
			System.out.println("A ügynökség nyert!");
		} else if (agencyB.isAgentAvailable()) {
			System.out.println("B ügynökség nyert!");
		}
	}
	
}