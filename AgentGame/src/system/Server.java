package system;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.net.ConnectException;
import java.net.ServerSocket;

import model.Agent;
import model.Agency;
import Runnable.AgentMain;
import Runnable.Generator;


public class Server extends Node implements Runnable {
	
	public Server(Agent agent) {
		super(agent);
	}
	
	public void run() {
		while(agent.isSecreatAvailable() && !agent.getAgency().isGameOver()) {
			try {
				ServerSocket server = makeServer();
				agent.setServerPort(server.getLocalPort());
				server.setSoTimeout(AgentMain.MAX_TIMEOUT);
				
				try (Socket client = server.accept(); Scanner socketSc = new Scanner(client.getInputStream()); PrintWriter socketPw = new PrintWriter(client.getOutputStream());) {
					System.out.print("Sikeresen kapocslodott a Klienshez");
					
					sendMessage(socketPw, agent.getRandomName());
					System.out.println("Véletlen generált név elküldve!");
					
					int inputTip = Integer.parseInt(socketSc.nextLine());
					System.out.println("Beérkezett tipp: "  + inputTip);
					
					if (inputTip == agent.getAgency().getCode()) {
						System.out.println("A kliens tippje helyes!");
						sendMessage(socketPw, "OK");
						
						String msg = socketSc.nextLine();
						
						if (msg.equals("???")) {
							System.out.println("A két ügynökség eltérõ!");
							
							sendMessage(socketPw, agent.getAgency().getNumberOfAgents());
							
							int agentCodeTip = Integer.parseInt(socketSc.nextLine());
							System.out.println("Tippelt ügynök azonosítója: " + agentCodeTip);
							
							if (agent.getAgentCode() == agentCodeTip) {
								System.out.println("Helyes tipp! Titok elküldése.");
								sendMessage(socketPw, agent.getRandomSecret(true));
							}else {
								System.out.println("Helytelen tipp");
								client.close();
							}
							
						}else {
							System.out.println("A két ügynök azonos ügynökséghez tartozik!");
							agent.getSecrets().put(msg, true);
							sendMessage(socketPw, agent.getRandomSecret(false));
							System.out.println("Random generált titok elküldése");
							System.out.println("Kapcsolat bontása!");
							client.close();
						}
							
					}else {
						System.out.println("Helytelen tipp! Kapcsolat bontása!");
						client.close();
					}
					server.close();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}
					
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private ServerSocket makeServer() {
		System.out.println("Szerver generálása véletlen porton: ");
		while (!agent.getAgency().isGameOver()) {
			try {
				return new ServerSocket(Generator.generatePortNumber((agent.getClientPort())));
			} catch (IOException ex) {
				System.err.println("Foglalt a port");
				continue;
			}
		}
		return null;
	}
	
}
