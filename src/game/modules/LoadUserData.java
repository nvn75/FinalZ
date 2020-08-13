package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.annotation.Execute;
import finalZ.annotation.Module;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;
import game.logic.Admin;
import game.logic.Guest;
import game.logic.User;

@Module({
		"ADMIN",
		"GUEST",
		"USER",
		"BANNED_USER"
})
public class LoadUserData {

	@Execute
	public String Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		int deviceId = (int)env.GetVar("deviceId");
		if (true) return null;
		if (deviceId == 1)
		{
			User user = new User();
			env.SetVar("new_comer", user);
			return "USER";
		}
		else if (deviceId == 2)
		{
			Admin user = new Admin();
			env.SetVar("new_comer", user);
			return "ADMIN";
		}
		else if (deviceId == 3)
		{
			User user = new User();
			env.SetVar("new_comer", user);
			return "BANNED_USER";
		}
		else
		{
			Guest guest = new Guest();
			return "GUEST";
		}
	}

}
