package connections;

import java.io.Serializable;
import java.util.Date;
import Core.Clients;
import Core.Commands;

// ver 2017 04 10 v 15
public class Message implements Serializable {	/* l'oggetto prodotto da questa classe verrà serializzato ed usato per tx info */
	private static final long serialVersionUID =1;	
	
	private 	Clients	 	UType;
	private 	Commands 	Cmd;
	private		String		MesId;
	private 	Date		DateOfRequest;
	private 	String		SQLQuery;	
	private 	String  	text;
	//Costruttori
	public Message (Commands cmd){
			setCommands(cmd);
			setDateOfRequest(new Date());
	}	
	public Message (Commands cmd,Clients Ut,String IdClient){
		setCommands(cmd);
		setUType(Ut);
		setDateOfRequest(new Date());
		setMesId(DateOfRequest.toString().concat(getCmd().toString().concat(IdClient)));
	}
	// Metodo utilizzato per verifica da GuardianTimeOut
	public boolean equalTo(Message x){
		boolean equal=false;
		if (x.getMesId()	==	this.getMesId()){
			equal=true;
		}
		return equal;
	}

	public Commands getCommand(){
	return getCmd();	
	}	
	public void setCommands (Commands cmd){
	this.setCmd(cmd);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDateOfRequest() {
		return DateOfRequest;
	}
	public void setDateOfRequest(Date dateOfRequest) {
		DateOfRequest = dateOfRequest;
	}
	public Clients getUType() {
		return UType;
	}
	public void setUType(Clients uType) {
		UType = uType;
	}
	public Commands getCmd() {
		return Cmd;
	}
	public void setCmd(Commands cmd) {
		Cmd = cmd;
	}
	public String getMesId() {
		return MesId;
	}
	public void setMesId(String mesId) {
		MesId = mesId;
	}	
	
}
