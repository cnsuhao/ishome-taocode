package org.ishome.jfp.framework.security.value;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.security.crypto.codec.Base64;

/**
 * 加密解密工具类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class PBESecurityHelper {
	private final static int ITERATIONS = 20;

	/**
	 * 加密
	 * 
	 * @param key
	 *            加密密匙
	 * @param plainText
	 *            需加密内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String key, String plainText) throws Exception {
		try {
			byte[] salt = new byte[8];
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			byte[] digest = md.digest();
			for (int i = 0; i < 8; i++) {
				salt[i] = digest[i];
			}
			PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes());
			String saltString = new String(Base64.encode(salt));
			String ciphertextString = new String(Base64.encode(cipherText));
			return saltString + ciphertextString;
		} catch (Exception e) {
			throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param key
	 *            解密密匙
	 * @param encryptTxt
	 *            需解密内容
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String key, String encryptTxt) throws Exception {
		int saltLength = 12;
		try {
			String salt = encryptTxt.substring(0, saltLength);
			String ciphertext = encryptTxt.substring(saltLength, encryptTxt.length());
			byte[] saltarray = Base64.decode(salt.getBytes());
			byte[] ciphertextArray = Base64.decode(ciphertext.getBytes());
			PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(keySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
			byte[] plaintextArray = cipher.doFinal(ciphertextArray);
			return new String(plaintextArray);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String encryptTxt = "";
		String plainTxt = "http://163.com?t今天我们去协和工作oken=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHwhttp://163.com?token=PUJaRS4EaW32rHw";
		try {
			System.out.println(plainTxt);
			encryptTxt = encrypt("mypassword01mypassword01mypassword01mypassword01", plainTxt);
			plainTxt = decrypt("mypassword01mypassword01mypassword01mypassword01", encryptTxt);
			System.out.println(encryptTxt);
			System.out.println(plainTxt);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
