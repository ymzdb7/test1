package com.winhands.base.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
 
public class DES {
    private static byte[] iv = {1,2,3,4,5,6,7,8};
    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
//      IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
      
        return Base64.encode(encryptedData);
    }
    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        byte[] byteMi = new Base64().decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
//      IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi); 
        return new String(decryptedData);
    }
    
    public static void main(final String[] args) {

		/*final String str = "QEfTA5XbNicUIWa3vAu%2F0YvkfDtnlRU0aken5oFnJCY8gPA7nmG%2FnmCKIBuu%0D%0AIZzXNYLx4uvTlp%2FFvdDKiqsBn1VrjGW6jI1Ca%2FEtw6FgEL8%3D";
		System.out.println("raw password is " + str);

		final String enCryptStr = encrypt(str);
		System.out.println("encrypted password is " + enCryptStr);*/

//		String deCryptStr = "";
//		String enCryptStr = "LL0PCfzNBWPdx9kPZ%2BOY6S1HRSEm6SiL2XtR7Kb9RxUhFC7PFHGiM84h9Sae%0D%0AipuN3G4Lcg8JPbv22HNrKNKKfjuMUlPHVO9ToLOghg5ASNVwW8CR9LrVPsZv%0D%0AR0s3l10JAHEAo5rOyJUXpKc5gbqAE2OyuPOkbuqr%2F2hFNBlk%2Bf6eJdLWpjVN%0D%0A75XJwwSQFGbkj%2B6Yq5fWNMfUJwuIaEot12F0oNPe%2FZ8RZOt6PT6EsecvpFLY%0D%0Ao%2Bnz1YHZdUnptW7EvyrYzHYBztJtMkrqgIvV2xGAP762vQCQFkxd4m%2BrTyS6%0D%0Aky5tnEuyzbjNPCDlrLC83jfwKesDPYY2Z3KTyxJzcFFzMvQw8i30NlH%2B17j8%0D%0AxzZGjJGb3t8bESMXV%2F3lRs%2Bo8KxbRhmWRJVynBbK0APx0asP7HOjTGs3pMci%0D%0ANABHxTpu3FSuepUPMQftqFufzKCH%2FDwuE%2FccCxuJPz85dc3jEw%3D%3D";
//		
//		
		try {
			
			String decryptString =  encryptDES("cityCode=000000000304&codes=0609010132%2C0603041539%2C0303024590%2C0303024600%2C0608032099%2C0608031833%2C0608031932%2C0603012566%2C0402050391%2C0501010609%2C0606030886%2C0502080049%2C0610030049%2C0505010943%2C0304021263%2C0609020166%2C0503060127%2C0610030048%2C0608030570%2C0606021048%2C0605020094%2C0603011693%2C0503060105%2C0608031539&sourceId=11", "12345679");
			
			decryptString = decryptDES(decryptString, "12345678");
			System.out.println(decryptString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	}
}