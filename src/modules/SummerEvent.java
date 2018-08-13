package modules;

import finalZ.ModuleEnv;
import finalZ.module.Module;
import game.logic.Player;

public class SummerEvent implements Module {

	@Override
	public boolean Execute() throws Exception {
		// TODO Auto-generated method stub
		Player player = (Player)ModuleEnv.Ins.getVar("player");
		if (player.m_iToken > 10)
		{
			player.m_iToken -= 10;
			return true;
		}
		return false;
	}

}
