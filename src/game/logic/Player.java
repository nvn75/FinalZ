package game.logic;

public class Player {

	public int m_iId;
	public int m_iToken;
	public int m_iLevel;
	public int m_iDiamond;
	
	public Player()
	{
		Init();
	}
	
	private void Init()
	{
		m_iToken = 0;
		m_iLevel = 0;
		m_iDiamond = 0;
	}
	
}
