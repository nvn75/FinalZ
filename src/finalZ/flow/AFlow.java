package finalZ.flow;

import java.util.ArrayList;

import finalZ.FlowEnv;
import finalZ.exceptions.ExecuteException;
import finalZ.trace.TraceInfo;
import finalZ.tree.ModuleForest;
import finalZ.tree.ModuleTree;

public abstract class AFlow {
	
	public enum Action{ MOVE, JUMP, FINISH }; 
	
	protected ModuleForest m_moduleForest;
	
	public AFlow()
	{
		try
		{
			m_moduleForest = Build();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String PrintForest()
	{
		return m_moduleForest.Print();
	}
	
	public String PrintTrace(ArrayList<String> tracePath)
	{
		return m_moduleForest.PrintTrace(tracePath);
	}
	
	public ArrayList<String> Excecute(FlowEnv env, int treeId) throws ExecuteException
	{
		ArrayList<String> tracePath = new ArrayList<>();
		m_moduleForest.GetTree(treeId).Execute(env, tracePath);
		return tracePath;
	}
	
	public ArrayList<String> Excecute(FlowEnv env, String name) throws ExecuteException
	{
		ArrayList<String> tracePath = new ArrayList<>();
		m_moduleForest.GetTreeByName(name).Execute(env, tracePath);
		return tracePath;
	}
	
	public ArrayList<String> Excecute(FlowEnv env) throws ExecuteException
	{
		ModuleTree mainTree = m_moduleForest.GetTreeByName("main");
		if (mainTree == null) return null;
		ArrayList<String> tracePath = new ArrayList<>();
		m_moduleForest.GetTreeByName("main").Execute(env, tracePath);
		return tracePath;
	}
	
	public abstract ModuleForest Build() throws Exception;
}
