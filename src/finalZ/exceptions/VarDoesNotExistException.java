package finalZ.exceptions;

import finalZ.tree.TreeNode;

public class VarDoesNotExistException extends ExecuteException {

	String varName;
	
	public VarDoesNotExistException(String varName) {
		// TODO Auto-generated constructor stubs
		this.varName = varName;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return varName;
	}
}
