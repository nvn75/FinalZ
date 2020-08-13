package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.annotation.Execute;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

@Module({
		"NEW_MSG"
})
public class DecodeMessage {

	public enum Port
	{
		NEW_MSG,
	}

	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Port.class);
	}

	@Execute
	public String Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		byte[] rawData = (byte[])env.GetVar("raw_data");
		env.SetVar("crc", rawData[0]);
		env.SetVar("deviceId", (int)rawData[1]);
		env.SetVar("cmd", rawData[2]);
		env.SetVar("param", rawData[3]);
		return "NEW_MSG";
	}

}
