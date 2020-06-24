package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

public class Network extends AModule {
	
	public enum Port
	{
		NEW_CONNECTION,
		DISCONNECTED,
		RECEIVE_MSG,
	}

	@Override
	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Port.class);
	}

	@Override
	public int Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		env.SetVar("raw_data", new byte[] {0, 1, 2, 3, 4});
		return Port.NEW_CONNECTION.ordinal();
	}

}
