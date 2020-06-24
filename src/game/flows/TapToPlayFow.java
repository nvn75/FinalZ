package game.flows;

import finalZ.flow.AFlow;
import finalZ.tree.ModuleForest;
import game.modules.AdminHandler;
import game.modules.BannedUserHandler;
import game.modules.CheckCRC;
import game.modules.DecodeMessage;
import game.modules.GenerateNewUser;
import game.modules.LoadUserData;
import game.modules.Network;
import game.modules.ResponseError;
import game.modules.UserHandler;

public class TapToPlayFow extends AFlow {

	@Override
	public ModuleForest Build() throws Exception {
		// TODO Auto-generated method stub
		return new ModuleForest()
				
			.NewTree("main", new Network())
				.AddNode(Network.Port.NEW_CONNECTION.ordinal(), new DecodeMessage())
					.AddNode(DecodeMessage.Port.NEW_MSG.ordinal(), new CheckCRC())
						.AddNode(CheckCRC.Port.PASSED.ordinal(), new LoadUserData())
							.AddNode(LoadUserData.Port.ADMIN.ordinal(), new AdminHandler()).EndBranch()
							.AddNode(LoadUserData.Port.USER.ordinal(), new UserHandler()).EndBranch()
//								.AddNode(Identify.Ports.GUEST.ordinal(), new UserHandler()).EndBranch()
							.AddNode(LoadUserData.Port.BANNED_USER.ordinal(), new BannedUserHandler()).EndBranch()
							.EndBranch()
//							.AddNode(LoadUserData.Ports.FAILED.ordinal(), new GenerateNewUser())
//								.AddNode(GenerateNewUser.Ports.SUCESSFUL.ordinal(), new Jump("2.1")).EndBranch()
//								.AddNode(GenerateNewUser.Ports.FAILED.ordinal(), new Jump("error")).EndBranch()
//								.EndBranch()
						.AddJumpNode(CheckCRC.Port.FAILED.ordinal(), "error")
						.EndBranch()
					.EndBranch()
			.EndTree()
			
			.NewTree("error", new ResponseError())
			.EndTree();
	}

}
