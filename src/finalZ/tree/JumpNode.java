package finalZ.tree;

import finalZ.module.AModule;
import finalZ.module.ModuleInfo;

public class JumpNode extends TreeNode {

	String jumpDes;
	
	public JumpNode(String id, ModuleInfo moduleInfo, TreeNode parent, String des) {
		super(id, moduleInfo, parent);
		// TODO Auto-generated constructor stub
		jumpDes = des;
	}
	
	@Override
	public String GetModuleName() {
		// TODO Auto-generated method stub
		return "Jump to [" + jumpDes + "]";
	}

}
