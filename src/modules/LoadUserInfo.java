package modules;

import finalZ.ModuleEnv;
import finalZ.module.Module;
import game.logic.Player;

public class LoadUserInfo implements Module {

	@Override
	public boolean Execute() throws Exception {
		// TODO Auto-generated method stub
		Player player = (Player)ModuleEnv.Ins.getVar("player");
		if (player.m_iId == 0)
		{
			player.m_iLevel = 100;
			player.m_iToken = 10000;
			player.m_iDiamond = 50;
			return true;
		}
		return false;
	}

}
