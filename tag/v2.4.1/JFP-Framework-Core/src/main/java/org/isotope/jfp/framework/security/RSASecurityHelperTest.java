package org.isotope.jfp.framework.security;

 
import java.util.Map;

import org.isotope.jfp.framework.security.value.RSASecurityHelper;
 
/** *//** 
*   
*   使用Junit4 来进行单元测试。
* @version 1.0 
* @since 1.0 
*/
public class RSASecurityHelperTest {   
    private String publicKey;   
    private String privateKey;   
    /**
	 * @param args
     * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		RSASecurityHelperTest rt = new RSASecurityHelperTest();
		rt.setUp();
		rt.testPri2Pub();
		rt.testPub2Pri();
	}
    
    public void setUp() throws Exception {   
        Map<String, Object> keyMap = RSASecurityHelper.initKey();   
 
        publicKey = RSASecurityHelper.getPublicKey(keyMap);   
        privateKey = RSASecurityHelper.getPrivateKey(keyMap);   
        System.err.println("公钥: \n\r" + publicKey);   
        System.err.println("私钥： \n\r" + privateKey);   
    }   
 
    
    public void testPub2Pri() throws Exception {   
        System.err.println("公钥加密——私钥解密");   
        String inputStr = "abc";   
        byte[] data = inputStr.getBytes();   
 
        byte[] encodedData = RSASecurityHelper.encryptByPublicKey(data, publicKey);   
 
        byte[] decodedData = RSASecurityHelper.decryptByPrivateKey(encodedData,   
                privateKey);   
 
        String outputStr = new String(decodedData);   
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);   
        
 
    }   
 
    
    public void testPri2Pub() throws Exception {   
        System.err.println("私钥加密——公钥解密");   
        String inputStr = "sign";   
        byte[] data = inputStr.getBytes();   
 
        byte[] encodedData = RSASecurityHelper.encryptByPrivateKey(data, privateKey);   
 
        byte[] decodedData = RSASecurityHelper.decryptByPublicKey(encodedData, publicKey);   
 
        String outputStr = new String(decodedData);   
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);   
 
        System.err.println("私钥签名——公钥验证签名");   
        // 产生签名   这里的encodedData可以与下面的encodedData同时换成new int[]{2,45}
        String sign = RSASecurityHelper.sign(encodedData, privateKey); //数字签名只要公钥人拿到签名的sign对比
        //，自己公钥通过同样的byte[]运算得到签名是否一致。是到致代表这个公钥就是对的，就是为现在发私钥人服务的。
        System.err.println("签名:\r" + sign);   
 
        // 验证签名   
        boolean status = RSASecurityHelper.verify(encodedData, publicKey, sign);   
        System.err.println("状态:\r" + status); 
 
    }   
 
}