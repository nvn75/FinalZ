package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class NodeIsMissingException extends ExecuteException 
{
	int port;
	
	public NodeIsMissingException(TreeNode curNode, int port)
	{
		super(curNode);
		this.port = port;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return curNode.GetName() + " [" + curNode.GetPort(port).desc + "]";
	}
}
