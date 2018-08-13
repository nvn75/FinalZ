package flows;

import finalZ.flow.AFlow;
import finalZ.tree.ModuleTree;
import modules.LoadUserInfo;
import modules.Success;
import modules.SummerEvent;
import modules.Verify;

public class SummerEventFlow extends AFlow {

	@Override
	public ModuleTree Build() {
		// TODO Auto-generated method stub
		AddModule(new LoadUserInfo())
		.AddModule(new Verify())
		.AddModule(new SummerEvent())
		.AddModule(new Success());
		return m_moduleTree;
	}

}
