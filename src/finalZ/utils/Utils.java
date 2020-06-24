package finalZ.utils;

public class Utils {
	
	public static String GenerateChildId(String parentId, int port, boolean isNew)
	{
		if (isNew) return parentId + ".1";
		else
		{
			int id = Integer.parseInt(parentId.substring(parentId.lastIndexOf(".") + 1));
			return parentId.substring(0, parentId.lastIndexOf(".") + 1) + (id + 1);
		}
	}

}
