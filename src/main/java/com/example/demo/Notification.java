package com.example.demo;

public class Notification {
	//private attributes
	private UserProfile sender;
	private UserProfile receiver;
	private boolean accept;

	//Constructor
	public Notification(UserProfile sender, UserProfile receiver)
	{
		this.sender = sender;
		this.receiver = receiver;
		this.accept = false;
	}
	
	//Getter Methods
	public UserProfile getSender()
	{ return this.sender; }
	
	public UserProfile getReceiver()
	{ return this.receiver; }
	
	public boolean getAcceptance()
	{ return this.accept; }
	
	//Method that gets called when user connect is accepted
	public void connectAccepted()
	{ 
		this.accept = true; 
		receiver.addConnection(this);
	}
	
}
