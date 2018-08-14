package flows;

import finalZ.flow.AFlow;
import finalZ.tree.ModuleTree;
import modules.LoadUserInfo;
import modules.NewUser;
import modules.Success;
import modules.SummerEvent;
import modules.Verify;
import modules.DeleteUser;
import modules.Error;

public class SummerEventFlow extends AFlow {

	@Override
	public ModuleTree Build() throws Exception {
		// TODO Auto-generated method stub
		return NewTree(new LoadUserInfo())
					.AddBranch(new Error())
						.AddBranch(new DeleteUser()).EndBranch()
					.AddNode(new NewUser())	
					.EndBranch()
				.AddNode(new Verify())
					.AddBranch(new Error()).EndBranch()
				.AddNode(new SummerEvent())
					.AddBranch(new Error()).EndBranch()
				.AddNode(new Success());
	}

}
