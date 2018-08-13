package modules;

import finalZ.ModuleEnv;
import finalZ.module.Module;
import game.logic.Player;

public class Verify implements Module {

	@Override
	public boolean Execute() throws Exception {
		// TODO Auto-generated method stub
		Player player = (Player)ModuleEnv.Ins.getVar("player");
		if (player.m_iLevel > 10) return true;
		else return false;
	}

}
