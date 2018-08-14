package finalZ.flow;

import java.util.ArrayList;

import finalZ.module.Module;
import finalZ.tree.ModuleTree;
import finalZ.tree.TreeNode;

public abstract class AFlow {
	
	public static final int k_iFOWRWARD = 0;
	public static final int k_iBRANCH = 1;
	public static final int k_iJUMP = 2;
	
	protected ModuleTree m_moduleTree;
	
	public AFlow()
	{
		try
		{
			Build();
			System.out.println(m_moduleTree);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ModuleTree NewTree(Module module)
	{
		m_moduleTree = new ModuleTree(new TreeNode("1", module, null));
		
		return m_moduleTree;
	}
	
	public ArrayList<TraceInfo> Excecute() throws Exception
	{
		ArrayList<TraceInfo> tracePath = new ArrayList<>();
		m_moduleTree.Execute(tracePath);
		return tracePath;
	}
	
	public abstract ModuleTree Build() throws Exception;
}
