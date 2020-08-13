package finalZ.tree;

import java.util.ArrayList;

import finalZ.FinalZ;
import finalZ.exceptions.ModuleNotExistsException;
import finalZ.module.AModule;
import finalZ.module.ModuleInfo;

public class ModuleForest {

	private ArrayList<ModuleTree> m_Trees;
	
	public ModuleForest()
	{
		m_Trees = new ArrayList<>();
	}
	
	public ModuleTree GetTree(int treeId)
	{
		return m_Trees.get(treeId);
	}
	
	public ModuleTree GetTreeByName(String name)
	{
		for (ModuleTree tree : m_Trees)
			if (tree.GetName().equals(name))
				return tree;
		return null;
	}

	public ModuleTree NewTree(String name, String moduleName) throws Exception
	{
		return NewTree(name, FinalZ.Instance().getModuleInfo(moduleName));
	}

	public ModuleTree NewTree(String name, ModuleInfo moduleInfo)
	{
		int id = m_Trees.size() + 1;
		ModuleTree newModuleTree = new ModuleTree(this, name, id, new TreeNode(id + ".1", moduleInfo, null));
		m_Trees.add(newModuleTree);
		
		return newModuleTree;
	}
	
	public void Jump(String nodeId)
	{
		
	}
	
	public String Print()
	{
		String s = "";
		for (int i = 0; i < m_Trees.size(); i++)
			s += m_Trees.get(i) + "\n";
		return s;
	}
	
	public String PrintTrace(ArrayList<String> tracePath)
	{
		String s = "";
		for (int i = 0; i < m_Trees.size(); i++)
			s += m_Trees.get(i).PrintTrace(tracePath) + "\n";
		return s;
	}
	
}
