package finalZ.tree;

import finalZ.exceptions.BranchNodeIsMissingException;
import finalZ.module.Module;

public class TreeNode {
	
	private Module m_Module;
	
	public TreeNode m_Parent;
	public TreeNode m_fwNode;
	public TreeNode m_branchNode;
	
	public TreeNode(Module module, TreeNode parent)
	{
		m_Parent = parent;
		m_Module = module;
		m_fwNode = null;
		m_branchNode = null;
	}
	
	public void Execute() throws Exception
	{
		boolean isForward = m_Module.Execute();
		if (m_fwNode != null )
		{
			if (isForward)
				m_fwNode.Execute();
			else
			{
				if (m_branchNode == null) throw new BranchNodeIsMissingException();
				m_branchNode.Execute();
			}
				
		}
	}
}
