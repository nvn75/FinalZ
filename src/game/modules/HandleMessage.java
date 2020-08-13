package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

public class HandleMessage extends AModule {

	public enum Ports
	{
		LOGIN,
	}
	
	@Override
	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Ports.class);
	}

	@Override
	public int Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		return -1;
	}

}
