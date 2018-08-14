import finalZ.ModuleEnv;
import flows.SummerEventFlow;
import game.logic.Player;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Player player = new Player();
		player.m_iId = 10;
		ModuleEnv.Ins.setVar("player", player);
		ModuleEnv.Ins.ExecuteFlow(new SummerEventFlow());
	}

}
