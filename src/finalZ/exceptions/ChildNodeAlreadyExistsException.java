package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class ChildNodeAlreadyExistsException extends TreeException {

	int port;
	
	public ChildNodeAlreadyExistsException(TreeNode curNode, int port) {
		super(curNode);
		// TODO Auto-generated constructor stub
		this.port = port;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage() + " [" + curNode.GetPort(port).desc + "]";
	}

}
