package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.annotation.Execute;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

@Module({
		"PASSED",
		"FAILED"
})
public class CheckCRC {

	public enum Port
	{
		FAILED,
		PASSED,
	}

	@Execute
	public String Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		byte crc = (byte)env.TakeOutVar("crc");
		if (crc == 0) 
			return "PASSED";
		else 
			return "FAILED";
	}

}
