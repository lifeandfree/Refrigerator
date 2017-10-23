package ru.innopolis.refrigerator.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5
{

	private static final Logger logger = LogManager.getLogger(Md5.class.getName());

	/**
	 * Перевод в 16-чную строку
	 *
	 * @param srcData byte[] Массив байтов
	 * @return String
	 */
	private static String convertToHex(byte[] srcData)
	{
		StringBuilder sbuffer = new StringBuilder();
		for (int i = 0; i < srcData.length; i++)
		{
			int flag = (srcData[i] >>> 4) & 0x0F;
			int twoHalf = 0;
			do
			{
				if ((0 <= flag) && (flag <= 9))
				{
					sbuffer.append((char)('0' + flag));
				}
				else
				{
					sbuffer.append((char)('a' + (flag - 10)));
				}
				flag = srcData[i] & 0x0F;
			}
			while (twoHalf++ < 1);
		}
		return sbuffer.toString();
	}

	/**
	 * Получение md5-хеша из принятой строки
	 *
	 * @param source String Входящая строка
	 * @return String
	 */
	public static String getMD5(String source)
	{
		String res = "";
		try
		{
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(source.getBytes());
			String md5 = convertToHex(md.digest());
			res = md5;
		}
		catch (NoSuchAlgorithmException e)
		{
			logger.error("md5.getMD5 : ", e);
		}

		return res;
	}
}
