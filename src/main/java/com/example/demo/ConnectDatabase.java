package com.example.demo;

import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

@Service
public class ConnectDatabase {
	
	private static final String FILE_PATH = "connectData.txt";
	
	private List<Notification> requests;
	private SkillXChangeDatabase userDatabase = new SkillXChangeDatabase();
	private List<UserProfile> users = userDatabase.getAllUserProfiles();
	private UserProfile sender;
	private UserProfile receiver;
	
	public ConnectDatabase()
	{
		requests = new ArrayList<>();
		loadRequests();
	}
	
	private void loadRequests()
	{
		try {
            File connectDataFile = new File(FILE_PATH);
            BufferedReader reader = new BufferedReader(new FileReader(connectDataFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] connectData = line.split(",");
                if (connectData.length >= 2) {
                    String receiverUsername = connectData[0];
                    String senderUsername = connectData[1];
                    for(UserProfile user : users)
                    {
                    	if(receiverUsername == user.getUsername())
                    		receiver = user;
                    	if(senderUsername == user.getUsername())
                    		sender = user;
                    }
                    Notification connect = new Notification(sender, receiver);
                    requests.add(connect);
                    sender.sendConnect(receiver);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveConnectRequests(UserProfile receiver, UserProfile sender)
	{
	    try {
	    	String receiverUsername = receiver.getUsername();
			String senderUsername = sender.getUsername();
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
	        StringBuilder connectData = new StringBuilder();
	        connectData.append(receiverUsername).append(",").append(senderUsername);
	        writer.write(connectData.toString());
	        writer.newLine();
	        writer.close();
	    }catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	public List<Notification> getAllConnectRequests() {
        return requests;
    }
	    
}
