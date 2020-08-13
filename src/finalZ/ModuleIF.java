package finalZ;

import java.util.ArrayList;

public class ModuleIF {
	
	private ArrayList<Port> m_lstPort;
	
	public <T extends Enum<T>> ModuleIF(Class<T> ports)
	{
		m_lstPort = new ArrayList<>();
		for (T port : ports.getEnumConstants())
		{
			m_lstPort.add(new Port(port.ordinal(), port.name()));
		}
	}

	public ModuleIF(ArrayList<String> ports)
	{
		m_lstPort = new ArrayList<>();
		for (int i = 0; i < ports.size(); i++)
		{
			m_lstPort.add(new Port(i, ports.get(i)));
		}
	}
	
	public ModuleIF()
	{
		m_lstPort = new ArrayList<>();
	}
	
	public ModuleIF AddPort(int id, String desc)
	{
		m_lstPort.add(new Port(id, desc));
		return this;
	}
	
	public Port GetPort(int port)
	{
		for (Port p : m_lstPort)
			if (p.id == port) return p;
		return null;
	}
	
	public int GetPortNum()
	{
		return m_lstPort.size();
	}
	
}
