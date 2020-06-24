import java.util.ArrayList;

import finalZ.FinalZ;
import game.flows.TapToPlayFow;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> tracePath = FinalZ.ExecuteFlow(new TapToPlayFow());
		
	}

}
