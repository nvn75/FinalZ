package finalZ;

import java.util.HashMap;

import finalZ.exceptions.VarDoesNotExistException;

public class Scope 
{
	private HashMap<String, Object> m_Vars;
	
	Scope()
	{
		m_Vars = new HashMap<>();
	}
	
	Scope(HashMap<String, Object> vars)
	{
		m_Vars = vars;
	}
	
	boolean HasVar(String varName)
	{
		return m_Vars.containsKey(varName);
	}
	
	Object GetVar(String varName) throws VarDoesNotExistException
	{
		if (!m_Vars.containsKey(varName)) throw new VarDoesNotExistException(varName);
		return m_Vars.get(varName);
	}
	
	void SetVar(String varName, Object value)
	{
		m_Vars.put(varName, value);
	}
	
	void ReleaseVar(String varName) throws VarDoesNotExistException
	{
		if (!m_Vars.containsKey(varName)) throw new VarDoesNotExistException(varName);
		m_Vars.remove(varName);
	}
	
	Object TakeOutVar(String varName) throws VarDoesNotExistException
	{
		if (!m_Vars.containsKey(varName)) throw new VarDoesNotExistException(varName);
		Object value = m_Vars.get(varName);
		m_Vars.remove(varName);
		return value;
	}
	
}
