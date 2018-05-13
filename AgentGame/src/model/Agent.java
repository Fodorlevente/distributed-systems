package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import system.Client;
import system.Server;

public class Agent {
	
	// Szálak:
	private Thread clientThread;
	private Thread serverThread;
	private int serverPort, clientPort;
	
	// Titkosügynök adatai:
	private Agency agency;
	private int agentCode;
	
	private List<String> names;
	private Map<String, Boolean> secrets;
	private Map<String, Map<Integer, Boolean>> tipsOfAgencyCode;
	private Map<String, Map<Integer, Boolean>> tipsOfAgentCode;
	
	// Random Generáláshoz
	private Random r;
	
	// Constructor with all fields
	public Agent(Thread clientThread, Thread serverThread, Agency agency, int agentCode, List<String> names,
			Map<String, Boolean> secrets, Map<String, Map<Integer, Boolean>> tipsOfAgencyCode,
			Map<String, Map<Integer, Boolean>> tipsOfAgentCode) {
		super();
		this.clientThread = clientThread;
		this.serverThread = serverThread;
		this.agency = agency;
		this.agentCode = agentCode;
		this.names = names;
		this.secrets = secrets;
		this.tipsOfAgencyCode = tipsOfAgencyCode;
		this.tipsOfAgentCode = tipsOfAgentCode;
	}
	
	// Main Constructor
	public Agent(Agency agency, int agentCode, int serverPort, int clientPort) {
		makeAgent(agency, agentCode);
		this.clientPort = clientPort;
		this.serverPort = serverPort;
	}

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

	public Map<String, Map<Integer, Boolean>> getTipsOfAgencyCode() {
		return tipsOfAgencyCode;
	}

	public void setTipsOfAgencyCode(Map<String, Map<Integer, Boolean>> tipsOfAgencyCode) {
		this.tipsOfAgencyCode = tipsOfAgencyCode;
	}

	public Map<String, Map<Integer, Boolean>> getTipsOfAgentCode() {
		return tipsOfAgentCode;
	}

	public void setTipsOfAgentCode(Map<String, Map<Integer, Boolean>> tipsOfAgentCode) {
		this.tipsOfAgentCode = tipsOfAgentCode;
	}
	
	private void makeAgent(Agency agency, int agentCode) {
		r = new Random();
		tipsOfAgencyCode = new HashMap<>();
		secrets = new HashMap<>();
		tipsOfAgentCode = new HashMap<>();

		this.agency = agency;
		this.agentCode = agentCode;

		System.out.println(String.format("Ügynökség neve: " + agency.getName() + ". Ügynök kódja: " + agentCode));
		readFromFile();
		
		System.out.println("Álnevek: ");
		for (int i = 0; i < names.size(); i++) {
			System.out.println("\t" + names.get(i));
		}
		
		System.out.println("Titkok:");
		for(String s: secrets.keySet()) {
			System.out.println(s);
		}
		
		serverThread = new Thread(new Server(this));
		clientThread = new Thread(new Client(this));
	}

	public void threadStart() {
		this.clientThread.start();
		System.out.println(String.format(agency.getCode() + " kliens szál elindítva!"));

		this.serverThread.start();
		System.out.println(String.format(agency.getCode() + " szerver szál elindítva!"));
	}
	
	// random generált álnév visszaadása
	public String getRandomName() {
		return this.names.get(this.r.nextInt(this.names.size()));
	}
	
	public boolean isSecreatAvailable() {
		return this.secrets.containsValue(true);
	}
	
	private String getFileName() {
		return ("agent" + agency.getCode() + "-" + agentCode + ".txt");
	}
	
	// Beolvasás fájlból readInfromation -> readFromFile
	private void readFromFile() {
		try (Scanner sc = new Scanner(new File(getFileName()));) {
			this.names = Arrays.asList(sc.nextLine().split(" "));
			this.secrets.put(sc.nextLine(), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Nem létezõ fájl!");
			System.exit(1);
		}
	}
	
	public int getTipsForAgencyName(String name) {
		int tips = 0;
		for (int agencyCode: tipsOfAgencyCode.get(name).keySet()) {
			if (tipsOfAgencyCode.get(name).get(agencyCode) == true) {
				tips = agencyCode;
			}
		}
		return tips;
	}
	
	public boolean isCorrectTips(String name) {
		boolean correct = false;
		for (int agentCode: tipsOfAgentCode.get(name).keySet()) {
			if (tipsOfAgentCode.get(name).get(agentCode) == true) {
				correct = true;
				break;
			}
		}
		return correct;		
	}
	
	public int getTipsForAgentName(String name) {
		int tips = 0;
		for (int agentCode: tipsOfAgentCode.get(name).keySet()) {
			if (tipsOfAgentCode.get(name).get(agentCode) == true) {
				tips = agentCode;
			}
		}
		return tips;
	}
	
}
