package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.annotation.Execute;
import finalZ.annotation.Instantiate;
import finalZ.annotation.InstantiationPolicy;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;

@Module({
		"DONE",
		"FAILED"
})
@Instantiate(InstantiationPolicy.MULTIPLE)
public class ResponseError {

	@Execute
	public String SendError(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		return "DONE";
	}

}
