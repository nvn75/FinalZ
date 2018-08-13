package modules;

import finalZ.module.Module;

public class Error implements Module {

	@Override
	public boolean Execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Error occurs !");
		return true;
	}

}
