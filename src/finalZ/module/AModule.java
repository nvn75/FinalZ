package finalZ.module;

import finalZ.FlowEnv;
import finalZ.ModuleIF;
import finalZ.Port;
import finalZ.exceptions.ExecuteException;

public abstract class AModule {
	
	protected ModuleIF m_ModuleIF;
	
	public AModule()
	{
		m_ModuleIF = BuildIF();
	}
	
	public ModuleIF GetIF()
	{
		return m_ModuleIF;
	}
	
	public ModuleIF BuildIFFromArray(Port[] ports)
	{
		ModuleIF moduleIF = new ModuleIF();
		for (Port port : ports)
			moduleIF.AddPort(port.id, port.desc);
		return moduleIF;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}
	
	public abstract ModuleIF BuildIF();
	public abstract int Execute(FlowEnv env) throws ExecuteException;
}
