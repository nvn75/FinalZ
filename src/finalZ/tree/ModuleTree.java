package finalZ.tree;

import java.util.ArrayList;

import finalZ.exceptions.ChildNodeAlreadyExistsException;
import finalZ.flow.TraceInfo;
import finalZ.module.Module;

public class ModuleTree {
	
	private TreeNode m_Root;
	private TreeNode m_curNode;
	
	public ModuleTree(TreeNode root)
	{
		m_Root = root;
		m_curNode = root;
	}
	
	public TreeNode GetRoot()
	{
		return m_Root;
	}
	
	public TreeNode GetCurNode()
	{
		return m_curNode;
	}
	
	public ModuleTree AddNode(Module module) throws ChildNodeAlreadyExistsException
	{
		if (m_curNode.m_fwNode == null)
		{
			String curId = m_curNode.GetId();
			int id = Integer.parseInt(curId.substring(curId.lastIndexOf(".") + 1));
			String newId = "";
			if (curId.contains("."))
				newId = curId.substring(0, curId.lastIndexOf(".") + 1) + (id + 1);	
			else
				newId = (id + 1) + "";
				
			m_curNode.m_fwNode = new TreeNode(newId, module, m_curNode);
			m_curNode = m_curNode.m_fwNode;
		}
		else
			throw new ChildNodeAlreadyExistsException();
		
		return this;
	}
	
	public ModuleTree AddBranch(Module module)
	{
		if (m_curNode.m_branchNode == null)
		{
			String curId = m_curNode.GetId();
			String newId = curId + ".1";
			m_curNode.m_branchNode = new TreeNode(newId, module, m_curNode);
			m_curNode = m_curNode.m_branchNode;
		}
		return this;
	}
	
	public ModuleTree EndBranch()
	{
		if (m_curNode == m_Root) return this;
		m_curNode = m_curNode.m_Parent;
		while (m_curNode.m_fwNode != null && m_curNode != m_Root)
			m_curNode = m_curNode.m_Parent;
		return this;
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
	
	public void Execute(ArrayList<TraceInfo> tracePath) throws Exception
	{
		GoRoot().Execute(tracePath);
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		m_Root.Print(s, 0, false);
		return s.toString();
	}
	
	/*
	 * 
	 * 			LoadUserData
	 * 			:..Error
	 * 			|	:__DeleteUser
	 * 			|	:
	 * 			|	NewUser
	 * 			|
	 * 			Verify
	 * 			|__Error
	 * 			|
	 * 			SummerEvent
	 * 			|__Error
	 * 			|
	 * 			Success
	 */
	

}
