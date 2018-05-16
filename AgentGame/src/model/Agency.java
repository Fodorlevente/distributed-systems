package model;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class Agency {
	private String name;
	private int code;
	private List<Agent> agents;
	public static int numberOfAgency;
	private boolean isGameOver;
	public static int count = 2;
	
	private List<String> secrets;
	
	public Agency(String name, int code, List<Agent> agents) {
		super();
		this.name = name;
		this.code = code;
		this.agents = agents;
		this.isGameOver = false;
	}
	
	public Agency(String name, int code) {
		this.name = name;
		this.code = code;
		this.isGameOver = false;

		this.agents = new ArrayList<>();
		this.secrets = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<Agent> getAgents() {
		return agents;
	}
	public void setAgents(List<Agent> agents) {
		this.agents = agents;
	}
	public static int getNumberOfAgency() {
		return numberOfAgency;
	}
	public static void setNumberOfAgency(int numberOfAgency) {
		Agency.numberOfAgency = numberOfAgency;
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
	
	public void addAgent(Agent agent) {
		agents.add(agent);
	}
	
	public int getNumberOfAgents() {
		return agents.size();
	}
	
	public List<String> getSecrets() {
		return this.secrets;
	}
	
	public boolean isAllSecreatRevealed(List<String> secrets) {
		boolean l = false;
		for (Agent agent : agents) {
			int counter = 0;
			for (String secret : secrets) {
				if (!agent.getSecrets().keySet().contains(secret))
					break;
				else {
					counter++;
				}	
			}
			if (counter == secrets.size()){
				l = true;
				break;
			}
		}
		return l;
	}
	
	public void addScerets(Set<String> secrets) {
		for (String secret : secrets) {
			if (!this.secrets.contains(secret)) {
				this.secrets.add(secret);
			}
		}
	}
	
	public boolean isAgentAvailable() {
		boolean available = false;
		for (Agent agent : agents)
			available |= !agent.isArrested();
		return available;
	}

}
