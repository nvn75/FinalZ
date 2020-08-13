package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.annotation.Execute;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

@Module({
		"NEW_CONNECTION"
})
public class NetWork {
	
	public enum Port
	{
		NEW_CONNECTION,
		DISCONNECTED,
		RECEIVE_MSG,
	}

	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Port.class);
	}

	@Execute
	public String Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		env.SetVar("raw_data", new byte[] {0, 1, 2, 3, 4});
		return "NEW_CONNECTION";
	}

}
