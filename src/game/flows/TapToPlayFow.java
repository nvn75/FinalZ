package game.flows;

import finalZ.flow.AFlow;
import finalZ.tree.ModuleForest;

public class TapToPlayFow extends AFlow {

	@Override
	public ModuleForest Build() throws Exception {
		// TODO Auto-generated method stub
		return new ModuleForest()
				
			.NewTree("main", "game.modules.NetWork")
				.AddNode("NEW_CONNECTION", "game.modules.DecodeMessage")
					.AddNode("NEW_MSG", "game.modules.CheckCRC")
						.AddNode("PASSED", "game.modules.LoadUserData")
							/*.AddNode(LoadUserData.Port.ADMIN.ordinal(), new AdminHandler()).EndBranch()
							.AddNode(LoadUserDataPort.USER.ordinal(), new UserHandler()).EndBranch()
							.AddNode(LoadUserData.Port.BANNED_USER.ordinal(), new BannedUserHandler()).EndBranch()*/
						.EndBranch()
						.AddJumpNode("FAILED", "error")
					.EndBranch()
				.EndBranch()
			.EndTree()
			
			.NewTree("error", "game.modules.ResponseError")
			.EndTree();
	}

}
