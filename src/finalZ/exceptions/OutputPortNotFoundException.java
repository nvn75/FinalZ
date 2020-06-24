package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class OutputPortNotFoundException extends ExecuteException {

	public OutputPortNotFoundException(TreeNode curNode)
	{
		super(curNode);
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return curNode.GetName();
	}
}
