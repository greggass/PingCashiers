package local.fourstar.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the PM_TERMINAL_LOCATION database table.
 * 
 */
@Entity
@Table(name="PM_TERMINAL_LOCATION")
@NamedQuery(name="TerminalLocation.findAll", query="SELECT t FROM TerminalLocation t WHERE t.posSecurePc = 'Y' AND t.terminalName LIKE '%Addison%' ORDER BY t.terminalName")
public class TerminalLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Id                                                                                                                                                                                                                        
	@Column(name="TERMINAL_NAME", insertable=false, updatable=false)
	private String terminalName;

	@Column(name="MACHINE_NAME", insertable=false, updatable=false)
	private String machineName;

	@Column(name="IP_ADDRESS", insertable=false, updatable=false)
	private String ipAddress;

	@Column(name="POS_SECURE_PC", insertable=false, updatable=false)
	private String posSecurePc;

	@Column(name="PING_RESULT")
	private String pingResult;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PING_ATTEMPTED_DT")
	private Date pingAttemptedDt;

	@Transient
	private String updateDatabase = "N";
	
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

	public String getPingResult() {
		return this.pingResult;
	}

	public String getPingAttemptedDt() {
		if ( this.pingAttemptedDt == null) {
			return "NULL";
		} else {
			return sdf.format(this.pingAttemptedDt);
		}
	}

	public String getUpdateDatabase() {
		return this.updateDatabase;
	}

	public void setPingResult(String pingResult) {
		this.pingResult = pingResult;
	}

	public void setPingAttemptedDt(Date pingAttemptedDt) {
		this.pingAttemptedDt = pingAttemptedDt;
	}

	public void setUpdateDatabase(String updateDatabase) {
		this.updateDatabase = updateDatabase;
	}

}