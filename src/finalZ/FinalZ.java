package finalZ;

import java.util.ArrayList;

import finalZ.exceptions.ExecuteException;
import finalZ.flow.AFlow;

public class FinalZ {
	
	public static ArrayList<String> ExecuteFlow(AFlow flow) throws ExecuteException
	{
		return new FlowEnv().ExecuteFlow(flow);
	}
}

