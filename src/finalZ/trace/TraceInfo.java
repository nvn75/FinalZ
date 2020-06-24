package finalZ.trace;

import java.util.ArrayList;
import finalZ.tree.ModuleTree;

public class TraceInfo 
{
	ModuleTree m_ModuleTree;
	ArrayList<ActionInfo> m_TracePath;
	
	public TraceInfo()
	{
		m_TracePath = new ArrayList<>();
	}
	
	public void LogAction(ActionInfo actionInfo)
	{
		m_TracePath.add(actionInfo);
	}
	
	public ModuleTree GetModuleTree()
	{
		return m_ModuleTree;
	}
}
