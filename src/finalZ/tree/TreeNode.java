package finalZ.tree;

import java.util.ArrayList;
import java.util.HashMap;

import finalZ.Log;
import finalZ.FlowEnv;
import finalZ.Port;
import finalZ.exceptions.ExecuteException;
import finalZ.exceptions.OutputPortNotFoundException;
import finalZ.exceptions.NodeIsMissingException;
import finalZ.exceptions.PortNotExistsException;
import finalZ.module.ModuleInfo;

public class TreeNode {
	
	private ModuleInfo m_ModuleInfo;
	
	public TreeNode m_Parent;
	public HashMap<Integer, TreeNode> m_Childs;
	private String m_stId;
	
	public TreeNode(String id, ModuleInfo moduleInfo, TreeNode parent)
	{
		m_stId = id;
		m_Parent = parent;
		m_ModuleInfo = moduleInfo;
		m_Childs = new HashMap<>();
	}
	
	public String GetId()
	{
		return m_stId;
	}
	
	public Port GetPort(int port)
	{
		return m_ModuleInfo.getIF().GetPort(port);
	}

	public int GetPortId(String portName) throws PortNotExistsException {
		return m_ModuleInfo.getPortId(portName);
	}
	
	public String GetName()
	{
		return GetModuleName() + " (id=" + m_stId + ")";
	}
	
	public String GetModuleName()
	{
		return m_ModuleInfo.toString();
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
	
	public void Execute(FlowEnv env, ArrayList<String> tracePath) throws Exception
	{
		tracePath.add(GetId());
		String portName = m_ModuleInfo.Execute(env);
		if (portName == null)
		{
			Log.Debug("Finish");
			return;
		}
		int portId = m_ModuleInfo.getPortId(portName);
		Port port = m_ModuleInfo.getIF().GetPort(portId);
		if (port == null) throw new OutputPortNotFoundException(this);
		TreeNode nextNode = m_Childs.get(port.id);
		if (nextNode == null) throw new NodeIsMissingException(this, port.id);
		Log.Debug("Execute " + GetModuleName() + " => [" + port.desc + "]" + nextNode.GetModuleName());
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
		if (m_ModuleInfo != null && m_ModuleInfo.getIF() != null)
		{
			if (m_Parent != null) {
				boolean hasAnotherUncle = isFullTree && parentPort.id < m_Parent.m_ModuleInfo.getIF().GetPortNum() - 1;
				for (int j = parentPort.id + 1; j < m_Parent.m_ModuleInfo.getIF().GetPortNum(); j++) {
					Port p = m_Parent.m_ModuleInfo.getIF().GetPort(j);
					if (m_Parent.m_Childs.containsKey(p.id)) {
						hasAnotherUncle = true;
						break;
					}
				}
				if (hasAnotherUncle)
					indentFlag = (indentFlag) + (1 << (level - 1));
			}
			for (int i = 0; i < m_ModuleInfo.getIF().GetPortNum(); i++)
			{
				Port port = m_ModuleInfo.getIF().GetPort(i);
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
		if (m_ModuleInfo != null && m_ModuleInfo.getIF() != null)
		{
			if (m_Parent != null) {
				boolean hasAnotherUncle = isFullTree && parentPort.id < m_Parent.m_ModuleInfo.getIF().GetPortNum() - 1;
				for (int j = parentPort.id + 1; j < m_Parent.m_ModuleInfo.getIF().GetPortNum(); j++) {
					Port p = m_Parent.m_ModuleInfo.getIF().GetPort(j);
					if (m_Parent.m_Childs.containsKey(p.id)) {
						hasAnotherUncle = true;
						break;
					}
				}
				if (hasAnotherUncle)
					indentFlag = (indentFlag) + (1 << (level - 1));
			}
			for (int i = 0; i < m_ModuleInfo.getIF().GetPortNum(); i++)
			{
				Port port = m_ModuleInfo.getIF().GetPort(i);
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
