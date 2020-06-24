package finalZ.trace;

import finalZ.flow.AFlow.Action;

public class ActionInfo {
	
	Action action;
	String from;
	String des;
	
	public ActionInfo(Action action, String from, String des)
	{
		this.action = action;
		this.from = from;
		this.des = des;
	}
}
