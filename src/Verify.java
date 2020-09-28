/**
 * @author Группа 1425ИСО, Уварков Сергей, Акчурин Ким
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/// верификация
public class Verify {

	public static String getHash(String plaintext) {
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			StringBuilder hashText = new StringBuilder(bigInt.toString(16));
			while (hashText.length() < 32) {
				hashText.insert(0, "0");
			}
			return hashText.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}