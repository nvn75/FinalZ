package finalZ;

import java.util.ArrayList;
import java.util.Stack;

import finalZ.exceptions.VarDoesNotExistException;
import finalZ.flow.AFlow;
import finalZ.flow.TraceInfo;

public class ModuleEnv {
	
	public static ModuleEnv Ins = new ModuleEnv();
	
	private ModuleEnv()
	{
		m_Scopes = new Stack<>();
		m_Scopes.push(new Scope());
	}
	
	private static Stack<Scope> m_Scopes;
	
	public Object getVar(String varName) throws VarDoesNotExistException
	{
		Scope scope = null;
		for (int i = m_Scopes.size() - 1; i >= 0; i--)
		{
			scope = m_Scopes.elementAt(i);
			if (scope.HasVar(varName)) break;
		}
		if (scope == null) throw new VarDoesNotExistException();
		
		return scope.GetVar(varName);
	}
	
	public void setVar(String varName, Object value) throws VarDoesNotExistException
	{
		Scope scope = null;
		for (int i = m_Scopes.size() - 1; i >= 0; i--)
		{
			scope = m_Scopes.elementAt(i);
			if (scope.HasVar(varName)) break;
		}
		if (scope == null) throw new VarDoesNotExistException();
		scope.SetVar(varName, value);
	}
	
	private void EnterScope()
	{
		m_Scopes.push(new Scope());
	}
	
	private Scope ExitScope()
	{
		return m_Scopes.pop();
	}
	
	public void ExecuteFlow(AFlow flow) throws Exception
	{
		EnterScope();
		ArrayList<TraceInfo> tracePath = flow.Excecute();
		for (int i = 0; i < tracePath.size(); i++)
			System.out.println(tracePath.get(i));
		ExitScope();
	}
}
