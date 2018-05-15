package model;

import java.util.List;
import java.util.ArrayList;

public class Agency {
	private String name;
	private int code;
	private List<Agent> agents;
	public static int numberOfAgency;
	private boolean isGameOver;
	public static int count = 2;
	
	public Agency(String name, int code, List<Agent> agents) {
		super();
		this.name = name;
		this.code = code;
		this.agents = agents;
		this.isGameOver = false;
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
	
	
}
