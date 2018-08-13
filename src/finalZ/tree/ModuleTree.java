package finalZ.tree;

import finalZ.module.Module;

public class ModuleTree {
	
	public TreeNode m_Root;
	public TreeNode m_curNode;
	
	public ModuleTree(TreeNode root)
	{
		m_Root = root;
		m_curNode = root;
	}
	
	public boolean AddNode(Module module)
	{
		if (m_curNode.m_fwNode != null) return false;
		m_curNode.m_fwNode = new TreeNode(module, m_curNode);
		m_curNode = m_curNode.m_fwNode;
		return true;
	}
	
	public boolean AddBranch(Module module)
	{
		if (m_curNode.m_branchNode != null) return false;
		m_curNode.m_branchNode = new TreeNode(module, m_curNode);
		m_curNode = m_curNode.m_branchNode;
		return true;
	}
	
	public TreeNode GoNextNode()
	{
		m_curNode = m_curNode.m_fwNode;
		return m_curNode;
	}
	
	public TreeNode GoBranchNode()
	{
		m_curNode = m_curNode.m_branchNode;
		return m_curNode;
	}
	
	public TreeNode GoBack()
	{
		m_curNode = m_curNode.m_Parent;
		return m_curNode;
	}
	
	public TreeNode GoRoot()
	{
		m_curNode = m_Root;
		return m_curNode;
	}

}
