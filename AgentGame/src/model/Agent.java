package model;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Agent {
	
	// Szálak:
	private Thread clientThread;
	private Thread serverThread;
	
	// Titkosügynök adatai:
	private Agency agency;
	private int agentCode;
	
	private List<String> names;
	private Map<String, Boolean> secrets;
	private Map<String, Map<Integer, Boolean>> agencyCodeTips;
	private Map<String, Map<Integer, Boolean>> agentCodeTips;
	
	// Random Generáláshoz
	private Random r;
	
	
	// Getters and Setters:
	public Thread getClientThread() {
		return clientThread;
	}

	public void setClientThread(Thread clientThread) {
		this.clientThread = clientThread;
	}

	public Thread getServerThread() {
		return serverThread;
	}

	public void setServerThread(Thread serverThread) {
		this.serverThread = serverThread;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public int getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(int agentCode) {
		this.agentCode = agentCode;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public Map<String, Boolean> getSecrets() {
		return secrets;
	}

	public void setSecrets(Map<String, Boolean> secrets) {
		this.secrets = secrets;
	}

	public Map<String, Map<Integer, Boolean>> getAgencyCodeTips() {
		return agencyCodeTips;
	}

	public void setAgencyCodeTips(Map<String, Map<Integer, Boolean>> agencyCodeTips) {
		this.agencyCodeTips = agencyCodeTips;
	}

	public Map<String, Map<Integer, Boolean>> getAgentCodeTips() {
		return agentCodeTips;
	}

	public void setAgentCodeTips(Map<String, Map<Integer, Boolean>> agentCodeTips) {
		this.agentCodeTips = agentCodeTips;
	}

}
