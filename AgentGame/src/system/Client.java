package system;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.net.ConnectException;

import model.Agent;
import model.Agency;
import Runnable.AgentMain;
import Runnable.Generator;

public class Client extends Node implements Runnable {
	
	private Random r;
	
	public Client(Agent agent) {
		super(agent);
		 r = new Random();
	}
	
	public void run() {
		Socket client;
		while(agent.isSecreatAvailable() && !agent.getAgency().isGameOver()) {
			try{
				client = makeSocket();
				agent.setClientPort(client.getLocalPort());
				
				try (Scanner socketSc = new Scanner(client.getInputStream()); PrintWriter socketPw = new PrintWriter(client.getOutputStream());) {
					System.out.print("Sikeresen kapocslodott a Szerverhez");
					String name = socketSc.nextLine();
					System.out.println("Név: " + name);
					
					int agencyTip = tipForAgencyCode(name);
					sendMessage(socketPw,agencyTip);
					
					if(socketSc.hasNextLine() && socketSc.nextLine().equals("OK")) {
						System.out.println("A tipp helyes!");
						agent.getTipsOfAgencyCode().get(name).put(agencyTip, true);
						
						if(agencyTip == agent.getAgency().getCode()) {
							System.out.println("A két ügynökség megegyezik!");
							sendMessage(socketPw,agent.getRandomSecret(false));
							System.out.println("Titkok elküldve");
							agent.getSecrets().put(socketSc.nextLine(), true);
							System.out.println("Kapcsolat bontva!");
						}else {
							System.out.println("Eltérõ a két ügynökség!");
							sendMessage(socketPw,"???");
							
							int otherAgent = Integer.parseInt(socketSc.nextLine());
							int agentCodeTip = tipForAgentCode(name,otherAgent);
							sendMessage(socketPw, agentCodeTip);
							
							if (socketSc.hasNextLine()) {
								System.out.println("Titok eltárolva");
								agent.getSecrets().put(socketSc.nextLine(), true);
								

								if (!agent.isCorrectTips(name)) {
									System.out.println("Ilyen tipp még nem volt. Titok eltárolva!");
									agent.getTipsOfAgentCode().get(name).put(agentCodeTip, true);
								}
							}	
						}
					}
					
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private Socket makeSocket() {
		while (!agent.getAgency().isGameOver()) {
			try {
				return new Socket(AgentMain.ADRESS, Generator.generatePortNumber(agent.getServerPort()));
			} catch (IOException ex) {
				System.out.println("Ez a port foglalt");
				continue;
			}
		}
		return null;
	}
	
	public void sleep() {
		System.out.println("Várakozás véletlen ideig");
		
		try {
			Thread.sleep(Generator.generateTimeoutNumber());
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Várakozás megszakadt!");
		}
	}
	
	private int tipForAgencyCode(String name) {
		int agencyCodeTip;
		if (agent.getTipsOfAgencyCode().containsKey(name)) {
			if (agent.isCorrectTips(name)) {
				agencyCodeTip = agent.getTipsForAgencyName(name);
			} else {
				Set<Integer> badTips = agent.getTipsOfAgencyCode().get(name).keySet();
				agencyCodeTip = r.nextInt(Agency.count) + 1;
				while (badTips.contains(agencyCodeTip))
					agencyCodeTip = r.nextInt(Agency.count) + 1;
				agent.getTipsOfAgencyCode().get(name).put(agencyCodeTip, false);
			}
		}else {
			agent.getTipsOfAgencyCode().put(name, new HashMap<Integer, Boolean>());
			agencyCodeTip = r.nextInt(Agency.count) + 1;
			agent.getTipsOfAgencyCode().get(name).put(agencyCodeTip, false);
		}
		return agencyCodeTip;
	}
	
	private int tipForAgentCode(String name, int agentsCount) {
		int agentCodeTip;
		if (agent.getTipsOfAgentCode().containsKey(name)) {
			if (agent.isCorrectTips(name)) {
				agentCodeTip = agent.getTipsForAgentName(name);
			}else {
				Set<Integer> badTips = agent.getTipsOfAgentCode().get(name).keySet();
				
				agentCodeTip = r.nextInt(agentsCount)+1;
				while (badTips.contains(agentCodeTip)) {
					agentCodeTip = r.nextInt(agentsCount)+1;
				}
				agent.getTipsOfAgentCode().get(name).put(agentCodeTip, false);
			}
		} else {
			agent.getTipsOfAgentCode().put(name, new HashMap<Integer, Boolean>());
			agentCodeTip = r.nextInt(agentsCount)+1;
			agent.getTipsOfAgentCode().get(name).put(agentCodeTip, false);
		}
		
		return agentCodeTip;
	}
}
