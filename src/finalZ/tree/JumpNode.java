package finalZ.tree;

import finalZ.module.AModule;

public class JumpNode extends TreeNode {

	String jumpDes;
	
	public JumpNode(String id, AModule module, TreeNode parent, String des) {
		super(id, module, parent);
		// TODO Auto-generated constructor stub
		jumpDes = des;
	}
	
	@Override
	public String GetModuleName() {
		// TODO Auto-generated method stub
		return "Jump to [" + jumpDes + "]";
	}

}
