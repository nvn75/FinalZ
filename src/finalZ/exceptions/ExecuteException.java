package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class ExecuteException extends TreeException {

	public ExecuteException()
	{
		super(null);
	}
	
	public ExecuteException(TreeNode curNode)
	{
		super(curNode);
	}
	
}
