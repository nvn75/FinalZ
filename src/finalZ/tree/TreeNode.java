package finalZ.tree;

import java.util.ArrayList;

import finalZ.exceptions.BranchNodeIsMissingException;
import finalZ.flow.AFlow;
import finalZ.flow.TraceInfo;
import finalZ.module.Module;

public class TreeNode {
	
	private Module m_Module;
	
	public TreeNode m_Parent;
	public TreeNode m_fwNode;
	public TreeNode m_branchNode;
	private String m_stId;
	
	public TreeNode(String id, Module module, TreeNode parent)
	{
		m_stId = id;
		m_Parent = parent;
		m_Module = module;
		m_fwNode = null;
		m_branchNode = null;
	}
	
	public String GetId()
	{
		return m_stId;
	}
	
	public String GetName()
	{
		return "(" + m_stId + ")" + m_Module.getClass().getSimpleName();
	}
	
	public String GetModuleName()
	{
		return m_Module.getClass().getSimpleName();
	}
	
	public void Execute(ArrayList<TraceInfo> tracePath) throws Exception
	{
		boolean isForward = m_Module.Execute();
		if (m_fwNode != null )
		{
			if (isForward)
			{
				tracePath.add(new TraceInfo(AFlow.k_iFOWRWARD, GetId(), m_fwNode.GetId()));
				m_fwNode.Execute(tracePath);
			}
			else
			{
				if (m_branchNode == null) throw new BranchNodeIsMissingException();
				tracePath.add(new TraceInfo(AFlow.k_iBRANCH, GetId(), m_branchNode.GetId()));
				m_branchNode.Execute(tracePath);
			}
		}
	}
	
	public void Print(StringBuilder str, int level, boolean isBranch) {
		// TODO Auto-generated method stub
		String indent = Indent(level);
		if (isBranch)
		{
			str.append(indent + "|__" + GetName() + "\n");
		}
		else
		{
			if (m_Parent != null)
				str.append(indent + "|\n");
			else
				str.append(indent + "\n");
			str.append(indent + GetName() + "\n");
		}
		if (isBranch) level = level + 1;
		if (m_branchNode != null) m_branchNode.Print(str, level, true);
		if (m_fwNode != null) m_fwNode.Print(str, level, false);
	}
	
	public String Indent(int level)
	{
		String s = "";
		for (int i = 0; i < level; i++)
			s += "|  ";
		return s;
	}
	
	
}
