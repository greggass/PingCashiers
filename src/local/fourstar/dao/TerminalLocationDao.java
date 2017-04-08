package local.fourstar.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import local.fourstar.model.TerminalLocation;

public class TerminalLocationDao {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("oracle_clas");
	
	// ==================================================
	//     read - All Payment Manager terminals
	// ==================================================
	public static List<TerminalLocation> read() {
		
		List<TerminalLocation> terminals = null;
		
		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		
		try {
			// Get a transaction
			transaction = manager.getTransaction();
			
			// Begin the transaction
			transaction.begin();
			
			// Get a list of Applications
			terminals = manager.createNamedQuery("TerminalLocation.findAll", TerminalLocation.class).getResultList();
	
			// Commit the transaction
			transaction.commit();
		} catch(Exception ex) {
			// Roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
		return terminals;
	}

	// ==================================================
	//     Update - Ping (Echo - port 7) results
	// ==================================================
	public static void update(String machineName, String pingResult, Date pingDate) {
		
		// Create an EntityManager
		EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			
			// Begin the transaction
			transaction.begin();

			// Get a list of Applications
			TerminalLocation terminal = manager.find(TerminalLocation.class, machineName);
			
			// Update the record
			terminal.setPingAttemptedDt(pingDate);
			terminal.setPingResult(pingResult);
			
			// Save the application
			manager.persist(terminal);
			
			// Commit the transaction
			transaction.commit();			
 		} catch(Exception ex) {
			// Roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the exception
			ex.printStackTrace();
		} finally {
			// Close the EntityManager
			manager.close();
		}
	}
}
