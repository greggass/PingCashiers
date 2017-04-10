package local.fourstar.utility;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import local.fourstar.dao.TerminalLocationDao;
import local.fourstar.model.TerminalLocation;

public class PingTerminals {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
    public static void main(String[] args) {
    	
//    	TerminalLocationDao dao = new TerminalLocationDao();
    	
		// Get list of terminals to check
		List<TerminalLocation> terminals = TerminalLocationDao.read();
		
		if ( terminals != null ) {

			System.out.println("pingAllTerminals");
			pingAllTerminals(terminals);
			
			System.out.println("update");
			TerminalLocationDao.update(terminals);

// TODO Tie into  ACTIVE.ORACLE_DB_SYNC_LOG
// TODO Log Error when IP address fails to match
			
			System.out.println("listAllTerminals");
			listAllTerminals(terminals);
			
		}		
	}
	
    private static void pingAllTerminals( List<TerminalLocation> terminals) {
    
 		if (terminals != null) {

			Date currentDate = new Date();			
			System.out.println("Current Date = " + sdf.format(currentDate));
	
			for (TerminalLocation terminal : terminals) {

				System.out.println("Terminal -- " + terminal.getTerminalName() );
				
				try {
					InetAddress inet = InetAddress.getByName( terminal.getMachineName() );

//					System.out.println("Terminal:  " + terminal.getMachineName() + " - " + terminal.getIpAddress() + " - " + inet.getHostAddress() ); 
					
					if ( terminal.getIpAddress().trim().equals( new String ( inet.getHostAddress().trim() ) ) ) {

						// Check that machine exists
						// Uses Echo (port 7) for check
						if ( inet.isReachable(5000) ) {			// Timeout = 5000 milliseconds
							terminal.setPingResult("Wow3");
						} else {
							terminal.setPingResult("Sodapop");
						}
							
						// Ping Attempted = Now
						terminal.setPingAttemptedDt(currentDate);
						
						// Update result in database
						terminal.setUpdateDatabase("Y");	
						
					} else {
						System.out.println("IP Address mismatch: " + terminal.getMachineName() + " - " + terminal.getIpAddress() + " - " + inet.getHostAddress() ); 
					}

				} catch ( Exception ex ) {
					// Print the exception
					//ex.printStackTrace();

					System.out.println("ERROR:  Terminal -- " + terminal.getTerminalName() );
				}
			}
		}
    }

	private static void listAllTerminals( List<TerminalLocation> terminals) {
	
		if (terminals != null) {
			
			System.out.println("=== Terminals MySql ===");
			
			for (TerminalLocation terminal : terminals) {
	
				System.out.println( padRightSpaces(terminal.getTerminalName(), 38)
						+ padRightSpaces(terminal.getMachineName(), 13)
						+ padRightSpaces(terminal.getIpAddress(),18)
						+ padRightSpaces(terminal.getPosSecurePc(),3)
						+ padRightSpaces(terminal.getPingResult(),10)
						+ padRightSpaces(terminal.getPingAttemptedDt(),15)
						+ padRightSpaces(terminal.getUpdateDatabase(),5)
						);
			}
		}
    }
    
	private static String padRightSpaces ( String str, int n ) {
		return String.format("%1$-" + n + "s", str);
		
	}

}
