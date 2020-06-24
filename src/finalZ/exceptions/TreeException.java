package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class TreeException extends Exception {
	
	TreeNode curNode;
	public TreeException(TreeNode curNode)
	{
		this.curNode = curNode;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return curNode.GetName();
	}

}
