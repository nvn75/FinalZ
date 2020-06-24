package game.modules;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.exceptions.ExecuteException;
import finalZ.module.AModule;
import game.logic.Admin;
import game.logic.Guest;
import game.logic.User;

public class LoadUserData extends AModule {

	public enum Port
	{
		ADMIN,
		GUEST,
		USER,
		BANNED_USER,
	}
	
	@Override
	public ModuleIF BuildIF() {
		// TODO Auto-generated method stub
		return new ModuleIF(Port.class);
	}
	
	@Override
	public int Execute(FlowEnv env) throws ExecuteException {
		// TODO Auto-generated method stub
		int deviceId = (int)env.GetVar("deviceId");
		if (deviceId == 1)
		{
			User user = new User();
			env.SetVar("new_comer", user);
			return Port.USER.ordinal();
		}
		else if (deviceId == 2)
		{
			Admin user = new Admin();
			env.SetVar("new_comer", user);
			return Port.ADMIN.ordinal();
		}
		else if (deviceId == 3)
		{
			User user = new User();
			env.SetVar("new_comer", user);
			return Port.BANNED_USER.ordinal();
		}
		else
		{
			Guest guest = new Guest();
			return Port.GUEST.ordinal();
		}
	}

}
