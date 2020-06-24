package finalZ.tree;

import java.util.ArrayList;

import finalZ.FlowEnv;
import finalZ.exceptions.ChildNodeAlreadyExistsException;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;
import finalZ.trace.TraceInfo;
import finalZ.utils.Utils;

public class ModuleTree {
	
	private ModuleForest m_Forest;
	private TreeNode m_Root;
	private TreeNode m_curNode;
	private int m_iId;
	private String m_stName;
	
	public ModuleTree(ModuleForest forest, String name, int id, TreeNode root)
	{
		m_Forest = forest;
		m_stName = name;
		m_iId = id;
		m_Root = root;
		m_curNode = root;
	}
	
	public String GetName()
	{
		return m_stName;
	}
	
	public int GetId()
	{
		return m_iId;
	}
	
	public TreeNode GetRoot()
	{
		return m_Root;
	}
	
	public TreeNode GetCurNode()
	{
		return m_curNode;
	}
	
	public ModuleForest EndTree()
	{
		return m_Forest;
	}
	
	public ModuleTree AddNode(int port, AModule module) throws ChildNodeAlreadyExistsException
	{
		if (!m_curNode.m_Childs.containsKey(port))
		{
			m_curNode.m_Childs.put(port, new TreeNode(m_curNode.GetId() + "." + (port + 1), module, m_curNode));
			m_curNode = m_curNode.m_Childs.get(port);
		}
		else
			throw new ChildNodeAlreadyExistsException(m_curNode, port);
		
		return this;
	}
	
	public ModuleTree AddJumpNode(int port, String des) throws ChildNodeAlreadyExistsException
	{
		if (!m_curNode.m_Childs.containsKey(port))
		{
			m_curNode.m_Childs.put(port, new JumpNode(m_curNode.GetId() + "." + port, null, m_curNode, des));
			m_curNode = m_curNode.m_Childs.get(port);
		}
		else
			throw new ChildNodeAlreadyExistsException(m_curNode, port);
		EndBranch();
		return this;
	}
	
	public ModuleTree EndBranch()
	{
		if (m_curNode == m_Root) return this;
		m_curNode = m_curNode.m_Parent;
		return this;
	}
	
	public TreeNode GoToChild(int port)
	{
		m_curNode = m_curNode.m_Childs.get(port);
		return m_curNode;
	}
	
	public TreeNode GoBack()
	{
		if (m_curNode == m_Root) return m_curNode;
		m_curNode = m_curNode.m_Parent;
		return m_curNode;
	}
	
	public TreeNode GoToRoot()
	{
		m_curNode = m_Root;
		return m_curNode;
	}
	
	public TreeNode FindNode(String id)
	{
		return m_Root.Find(id);
	}
	
	public void Execute(FlowEnv env, ArrayList<String> tracePath) throws ExecuteException
	{
		GoToRoot().Execute(env, tracePath);
	}
	
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append("|=[root]=" + m_stName + " (id=" + m_iId + ")\n");
		int indexFlag = 0;
		ArrayList<String> space = new ArrayList<>();
		m_Root.Print(s, null, -1, true, indexFlag, space);
		return s.toString();
	}
	
	public String PrintTrace(ArrayList<String> tracePath)
	{
		StringBuilder s = new StringBuilder();
		int indexFlag = 0;
		ArrayList<String> space = new ArrayList<>();
		System.out.println("Trace path: " + tracePath);
		m_Root.PrintTrace(s, null, -1, true, indexFlag, space, tracePath);
		return s.toString();
	}
	
	/*
	 * 
	 * 			LoadUserData A0[fw]
	 * 			:__Error
	 * 			:	|__DeleteUser
	 * 			:	|
	 * 			:	NewUser
	 * 			:
	 * 			Verify A1[br] A3[<< 2.1 | br] A5[<< 2.1 | fw]
	 * 			:..Error A2[>> 2] A4[>> 2]
	 * 			:
	 * 			SummerEvent A6[fw]
	 * 			:__Error
	 * 			:
	 * 			Success A7[end]
	 */
	

}
