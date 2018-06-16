/**
 *@Copyright:Copyright (c) 2008 - 2100
 *@Company:Arvato
 */
package com.winhands.base.pay.util;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import com.winhands.base.util.StringUtil; 


/**
 * @Title:
 * @Description:
 * @Author:WANG224
 * @Since:2015年5月13日
 * @Version:1.1.0
 */
public class DESUtilPlus
{
	private static final String PASSWORD_CRYPT_KEY = "kidswant";

	private static final byte[] KEYS = new byte[]
	{ 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01 };

	public static String encrypt(final String src)
	{
		String result = null;
		byte[] tmpOriginalStr = null;
		try
		{
			if (!StringUtil.isNull(src))
			{
				tmpOriginalStr = src.getBytes("utf-8");
				final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				final DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes("utf-8"));
				final SecretKey secretKey = keyFactory.generateSecret(dks);
				final IvParameterSpec param = new IvParameterSpec(KEYS);
				final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey, param);
				final byte[] tmpEncypt = cipher.doFinal(tmpOriginalStr);
				if (tmpEncypt != null)
				{
					//result = Base64.encodeBytes(tmpEncypt);
					//result = new BASE64Encoder().encode(tmpEncypt);
					result = Base64.encode(tmpEncypt);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static String decrypt(final String encrypStr)
	{
		String result = null;
		byte[] tmpEncrypStr = null;
		try
		{
			if (!StringUtil.isNull(encrypStr))
			{
				tmpEncrypStr = Base64.decode(encrypStr);
				final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				final DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes("utf-8"));
				final SecretKey secretKey = keyFactory.generateSecret(dks);
				final IvParameterSpec param = new IvParameterSpec(KEYS);
				final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, secretKey, param);
				final byte[] tmpDecrypt = cipher.doFinal(tmpEncrypStr);
				if (tmpDecrypt != null)
				{
					result = new String(tmpDecrypt, "utf-8");
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{

		final String str = "1234";
		System.out.println("raw password is " + str);

		final String enCryptStr = encrypt(str);
		System.out.println("encrypted password is " + enCryptStr);

		String deCryptStr = "";
		deCryptStr = decrypt(enCryptStr);
		System.out.println("decrypted password is " + deCryptStr);


	}

}
