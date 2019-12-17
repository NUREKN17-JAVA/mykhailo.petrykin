package ua.nure.cs.petrykin.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import ua.nure.cs.petrykin.usermanagement.db.DaoFactory;
import ua.nure.cs.petrykin.usermanagement.db.DatabaseException;
import ua.nure.cs.petrykin.usermanagement.domain.User;

public class SearchAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1193396105443361352L;
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
			Collection <User>  users = DaoFactory.getInstance().getUserDao().find(firstName,lastName);
			if(users.size()>0) {
				showUsers(users);
			}else {
				addBehaviour(new SearchRequestBehaviour(new AID[] {}, firstName,lastName));
			}
		} catch(DatabaseException e) {
			throw new SearchException(e);
		}
	}
	public void showUsers(Collection <User> user) {
		
	}
}
