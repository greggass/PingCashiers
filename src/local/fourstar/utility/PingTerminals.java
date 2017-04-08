package local.fourstar.utility;

import java.util.List;

import local.fourstar.dao.TerminalLocationDao;
import local.fourstar.model.TerminalLocation;

public class PingTerminals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("=== Terminals ===");

		List<TerminalLocation> terminals = TerminalLocationDao.read();
		if (terminals != null) {
			for (TerminalLocation terminal : terminals) {
				System.out.println( padRightSpaces(terminal.getTerminalName(), 35)
						+ padRightSpaces(terminal.getMachineName(), 10)
						+ padRightSpaces(terminal.getIpAddress(),15)
						+ padRightSpaces(terminal.getPosSecurePc(),3)
						+ padRightSpaces(terminal.getPingResult(),10)
						+ terminal.getPingAttemptedDt().toString()
						);
			}
		}
	}
	
	private static String padRightSpaces ( String str, int n ) {
		return String.format("%1$-" + n + "s", str);
		
	}

}
