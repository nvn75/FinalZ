package finalZ.tree;

import java.util.ArrayList;
import java.util.HashMap;

import finalZ.Log;
import finalZ.FlowEnv;
import finalZ.Port;
import finalZ.exceptions.ExecuteException;
import finalZ.exceptions.OutputPortNotFoundException;
import finalZ.exceptions.NodeIsMissingException;
import finalZ.module.AModule;
import finalZ.trace.TraceInfo;

public class TreeNode {
	
	private AModule m_Module;
	
	public TreeNode m_Parent;
	public HashMap<Integer, TreeNode> m_Childs;
	private String m_stId;
	
	public TreeNode(String id, AModule module, TreeNode parent)
	{
		m_stId = id;
		m_Parent = parent;
		m_Module = module;
		m_Childs = new HashMap<>();
	}
	
	public String GetId()
	{
		return m_stId;
	}
	
	public Port GetPort(int port)
	{
		return m_Module.GetIF().GetPort(port);
	}
	
	public String GetName()
	{
		return GetModuleName() + " (id=" + m_stId + ")";
	}
	
	public String GetModuleName()
	{
		return m_Module.toString();
	}
	
	public TreeNode Find(String id)
	{
		for (TreeNode child: m_Childs.values())
		{
			if (id.contains(child.m_stId))
				return child.Find(id);
		}
		return null;
	}
	
	public void Execute(FlowEnv env, ArrayList<String> tracePath) throws ExecuteException
	{
		tracePath.add(GetId());
		int portId = m_Module.Execute(env);
		if (m_Module.GetIF() == null) 
		{
			Log.Debug("Finish");
			return;
		}
		Port port = m_Module.GetIF().GetPort(portId);
		if (port == null) throw new OutputPortNotFoundException(this);
		TreeNode nextNode = m_Childs.get(port.id);
		if (nextNode == null) throw new NodeIsMissingException(this, port.id);
		Log.Debug(GetModuleName() + "@[" + port.desc + "] Execute " + nextNode.GetModuleName());
		if (nextNode instanceof JumpNode)
		{
			
		}
		else
			nextNode.Execute(env, tracePath);
	}
	
	public void Print(StringBuilder str, Port parentPort, int level, boolean isFullTree, int indentFlag, ArrayList<String> space) {
		// TODO Auto-generated method stub
		String indent = Indent(indentFlag, level, space);
		if (m_Parent == null)
			str.append(indent + GetName() + "\n");
		else
			str.append(indent + "|__[" + parentPort.desc + "|" + parentPort.id + "]" + "<--" + GetName() + "\n");
		
		level = level + 1;
		String s = "";
		String temp = parentPort == null ? "" : "[" + parentPort.desc + "|" + parentPort.id + "]<--";
		for (int k = 0; k < temp.length(); k++)
			s += " ";
		space.add(s);
		if (m_Module != null && m_Module.GetIF() != null)
		{
			for (int i = 0; i < m_Module.GetIF().GetPortNum(); i++)
			{
				Port port = m_Module.GetIF().GetPort(i);
				boolean hasAnotherChild = isFullTree && i < m_Module.GetIF().GetPortNum() - 1;
				for (int j = i + 1; j < m_Module.GetIF().GetPortNum(); j++)
				{
					Port p = m_Module.GetIF().GetPort(j);
					if (m_Childs.containsKey(p.id))
					{
						hasAnotherChild = true;
						break;
					}
				}
				if (hasAnotherChild)
					indentFlag = (indentFlag) + (1 << level);
				indent = Indent(indentFlag, level, space);
				if (m_Childs.containsKey(port.id))
				{
					m_Childs.get(port.id).Print(str, port, level, isFullTree, indentFlag, space);
				}
				else if (isFullTree)
				{
					str.append(indent + "|__[" + port.desc + "|" + port.id + "] x\n");
				}
			}
		}
	}
	
	public void PrintTrace(StringBuilder str, Port parentPort, int level, boolean isFullTree, int indentFlag, ArrayList<String> space, ArrayList<String> tracePath) {
		// TODO Auto-generated method stub
		String indent = Indent(indentFlag, level, space);
		if (m_Parent == null)
			str.append(indent + GetName() + "\n");
		else
		{
			String currTraceNode = tracePath.get(level + 1);
			String name = GetId();
			boolean isInTrace = false;
			if (currTraceNode.compareTo(name) >= 0) isInTrace = true;
			if (isInTrace)
				str.append(indent + ":..[" + parentPort.desc + "|" + parentPort.id + "]" + "<--" + GetName() + "\n");
			else
				str.append(indent + "|__[" + parentPort.desc + "|" + parentPort.id + "]" + "<--" + GetName() + "\n");
		}
		
		level = level + 1;
		String s = "";
		String temp = parentPort == null ? "" : "[" + parentPort.desc + "|" + parentPort.id + "]<--";
		for (int k = 0; k < temp.length(); k++)
			s += " ";
		space.add(s);
		if (m_Module != null && m_Module.GetIF() != null)
		{
			for (int i = 0; i < m_Module.GetIF().GetPortNum(); i++)
			{
				Port port = m_Module.GetIF().GetPort(i);
				boolean hasAnotherChild = isFullTree && i < m_Module.GetIF().GetPortNum() - 1;
				for (int j = i + 1; j < m_Module.GetIF().GetPortNum(); j++)
				{
					Port p = m_Module.GetIF().GetPort(j);
					if (m_Childs.containsKey(p.id))
					{
						hasAnotherChild = true;
						break;
					}
				}
				if (hasAnotherChild)
					indentFlag = (indentFlag) + (1 << level);
				indent = Indent(indentFlag, level, space);
				if (m_Childs.containsKey(port.id))
				{
					m_Childs.get(port.id).PrintTrace(str, port, level, isFullTree, indentFlag, space, tracePath);
				}
				else if (isFullTree)
				{
					str.append(indent + "|__[" + port.desc + "|" + port.id + "] x\n");
				}
			}
		}
	}
	
	public String Indent(int indentFlag, int level, ArrayList<String> space)
	{
		String s = "";
		for (int i = 0; i < level; i++)
		{
			s += (((indentFlag >> i) & 1) == 1 ? "|  " :  "   ") + space.get(i + 1);
		}
		return s;
	}
	
	public boolean IsInRoute(String nodeId, ArrayList<String> tracePath)
	{
		//[1.1, 1.1.1, 1.1.1.1, 1.1.1.1.1, 1.1.1.1.1.3]
		//1.1.1.1.1
		return false;
	}
	
	
}
