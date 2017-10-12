package connections;

public class MessageRealServer {

	private Message 	Msg;
	private ServerReal 	Srv;
	
	public MessageRealServer (	Message msg,ServerReal srv	){
		setMsg(msg);
		setSrv(srv);	
	}

	public Message getMsg() {
		return Msg;
	}
	public void setMsg(Message msg) {
		Msg = msg;
	}
	public ServerReal getSrv() {
		return Srv;
	}
	public void setSrv(ServerReal srv) {
		Srv = srv;
	}	
}
