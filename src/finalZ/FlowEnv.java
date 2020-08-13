package finalZ;

import java.util.ArrayList;
import java.util.Stack;

import finalZ.exceptions.ExecuteException;
import finalZ.exceptions.VarDoesNotExistException;
import finalZ.flow.AFlow;
import finalZ.trace.TraceInfo;

public class FlowEnv {
	
	public FlowEnv()
	{
		m_Scopes = new Stack<>();
		m_Scopes.push(new Scope());
	}
	
	private static Stack<Scope> m_Scopes;
	
	private Scope FindScope(String varName) throws VarDoesNotExistException
	{
		Scope scope = null;
		for (int i = m_Scopes.size() - 1; i >= 0; i--)
		{
			scope = m_Scopes.elementAt(i);
			if (scope.HasVar(varName)) break;
		}
		if (scope == null) throw new VarDoesNotExistException(varName);
		return scope;
	}
	
	public Object GetVar(String varName) throws VarDoesNotExistException
	{
		Scope scope = FindScope(varName);
		if (scope == null) throw new VarDoesNotExistException(varName);
		
		return scope.GetVar(varName);
	}
	
	public void SetVar(String varName, Object value) throws VarDoesNotExistException
	{
		Scope scope = FindScope(varName);
		scope.SetVar(varName, value);
	}
	
	public void ReleaseVar(String varName) throws VarDoesNotExistException
	{
		Scope scope = FindScope(varName);
		scope.ReleaseVar(varName);
	}
	
	public Object TakeOutVar(String varName) throws VarDoesNotExistException
	{
		Scope scope = FindScope(varName);
		return scope.TakeOutVar(varName);
	}
	
	private void EnterScope()
	{
		m_Scopes.push(new Scope());
	}
	
	private Scope ExitScope()
	{
		return m_Scopes.pop();
	}
	
	public ArrayList<String> ExecuteFlow(AFlow flow) throws ExecuteException
	{
		ArrayList<String> tracePath = null;
		try
		{
			EnterScope();
			Log.Debug("Excecute " + flow.getClass().getSimpleName() + "\n" + flow.PrintForest());
			tracePath = flow.Excecute(this);
			Log.Debug("Trace path " + flow.getClass().getSimpleName() + "\n" + flow.PrintTrace(tracePath));
			ExitScope();
			
		}
		catch (Exception e)
		{
			Log.Exception("Executing " + flow.getClass().getSimpleName() + " Failed", e);
		}
		return tracePath;
	}
	
	public ArrayList<String> ExecuteFlow(AFlow flow, int entryPort) throws Exception
	{
		ArrayList<String> tracePath = null;
		try
		{
			EnterScope();
			Log.Debug("Excecute " + flow.getClass().getSimpleName() + "\n" + flow.PrintForest());
			tracePath = flow.Excecute(this, entryPort);
			ExitScope();
		}
		catch (ExecuteException e)
		{
			Log.Exception("Executing " + flow.getClass().getSimpleName() + " failed", e);
		}
		return tracePath;
	}
}
