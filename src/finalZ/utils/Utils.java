package finalZ.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	
	public static String GenerateNodeId(String path)
	{
		String sha1 = "";

		// With the java libraries
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(path.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
			return sha1;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
