package finalZ;

import java.util.Stack;

import finalZ.exceptions.VarDoesNotExistException;
import finalZ.flow.AFlow;

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
		Scope curScope = m_Scopes.get(0);
		return curScope.GetVar(varName);
	}
	
	public void setVar(String varName, Object value)
	{
		Scope curScope = m_Scopes.get(0);
		curScope.SetVar(varName, value);
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
		flow.Build();
		
		EnterScope();
		flow.Excecute();
		ExitScope();
	}
}
