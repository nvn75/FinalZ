package finalZ.flow;

public class TraceInfo {
	
	int action;
	String fromNode;
	String desNode;
	
	public TraceInfo(int action, String from, String des)
	{
		this.action = action;
		this.fromNode = from;
		this.desNode = des;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return action + " " + fromNode + " " + desNode;
	}
	
}
