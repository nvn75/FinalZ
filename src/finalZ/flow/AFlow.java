package finalZ.flow;

import finalZ.module.Module;
import finalZ.tree.ModuleTree;
import finalZ.tree.TreeNode;

public abstract class AFlow {
	
	protected ModuleTree m_moduleTree;
	
	public AFlow()
	{
		m_moduleTree = null;
	}

	public AFlow AddModule(Module module)
	{
		if (m_moduleTree == null)
		{
			m_moduleTree = new ModuleTree(new TreeNode(module, null));
		}
		m_moduleTree.AddNode(module);
		return this;
	}
	
	public AFlow AddBranchModule(Module module)
	{
		m_moduleTree.AddBranch(module);
		return this;
	}
	
	public void Excecute() throws Exception
	{
		m_moduleTree.GoRoot();
		m_moduleTree.m_curNode.Execute();
	}
	
	public abstract ModuleTree Build();
}
