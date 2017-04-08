package local.fourstar.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PM_TERMINAL_LOCATION database table.
 * 
 */
@Entity
@Table(name="PM_TERMINAL_LOCATION")
@NamedQuery(name="TerminalLocation.findAll", query="SELECT t FROM TerminalLocation t WHERE t.posSecurePc = 'Y'")
public class TerminalLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MACHINE_NAME", insertable=false, updatable=false)
	private String machineName;

	@Column(name="IP_ADDRESS", insertable=false, updatable=false)
	private String ipAddress;

	@Column(name="TERMINAL_NAME", insertable=false, updatable=false)
	private String terminalName;

	@Column(name="POS_SECURE_PC", insertable=false, updatable=false)
	private String posSecurePc;

	@Temporal(TemporalType.DATE)
	@Column(name="PING_ATTEMPTED_DT")
	private Date pingAttemptedDt;

	@Column(name="PING_RESULT")
	private String pingResult;

	public TerminalLocation() {
	}

	public String getTerminalName() {
		return this.terminalName;
	}

	public String getMachineName() {
		return this.machineName;
	}

	public String getIpAddress() {
		return this.ipAddress;
	}

	public String getPosSecurePc() {
		return this.posSecurePc;
	}

	public Date getPingAttemptedDt() {
		return this.pingAttemptedDt;
	}

	public void setPingAttemptedDt(Date pingAttemptedDt) {
		
//		Date pingAttemptedDt = new Date();
		
		this.pingAttemptedDt = pingAttemptedDt;
	}

	public String getPingResult() {
		return this.pingResult;
	}

	public void setPingResult(String pingResult) {
		this.pingResult = pingResult;
	}

}