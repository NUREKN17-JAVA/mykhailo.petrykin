package ua.nure.cs.petrykin.usermanagement.agent;

import java.util.Collection;

import jade.core.Agent;
import ua.nure.cs.petrykin.usermanagement.db.DaoFactory;
import ua.nure.cs.petrykin.usermanagement.db.DatabaseException;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName()+" started");
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName()+" terminated.");
		super.takeDown();
	}
	
	public void search(String firstName, String lastName) throws SearchException{
		try {
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName,lastName);
			if(users.size()>0) {
				showUsers(users);
			}else {
				
			}
		} catch(DatabaseException e) {
			throw new SearchException(e);
		}
	}
	private void showUsers(Collection user) {
		
	}
}