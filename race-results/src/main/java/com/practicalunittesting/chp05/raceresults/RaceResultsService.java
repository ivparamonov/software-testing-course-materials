package com.practicalunittesting.chp05.raceresults;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This is a service whose role is to inform interested parties
 * about the results of races. 
 * <ul>
 * <li>It should allow clients to subscribe (which means they start receiving messages),
 * <li>It should allow subscribers to unsubscribe (which means they stop receiving messages),
 * <li>Every time a new message comes, it should be sent to all subscribers.
 * <li>Each subscriber should receive a message only once.
 * </ul>
 * @author Tomek Kaczanowski
 */
public class RaceResultsService {

	private Collection<Client> clients = new LinkedList<Client>();

	public void addSubscriber(Client client) {
		clients.add(client);
	}

	public void send(Message message) {
		for (Client client : clients) {
			client.receive(message);
		}
	}

	public void removeSubscriber(Client client) {
		clients.remove(null);
	}
}
