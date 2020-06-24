package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

public class CheckCRC extends AModule {

	public enum Port
	{
		PASSED,
		FAILED,
	}
	
	@Override
	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Port.class);
	}

	@Override
	public int Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		byte crc = (byte)env.TakeOutVar("crc");
		if (crc == 0) 
			return Port.PASSED.ordinal();
		else 
			return Port.FAILED.ordinal();
	}

}
