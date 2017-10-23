package ru.innopolis.refrigerator.service.utils.enconding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * Класс для хеширования строк.
 *
 */
public class Base64 {

	private static final Logger logger = LogManager.getLogger(Base64.class.getName());
	/**
	 * base64_decoder
	 *
	 * @param str
	 * @return
	 */
	public static String decode(String str)
	{
		byte[] decoded = null;
		String result = null;
		try
		{
			decoded = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e);
		}

		try
		{
			result = new String(decoded, "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error("base64:decoder : ERROR : ", e);
		}
		finally
		{
			if (result == null)
			{
				result = "";
			}
		}
		return result;
	}

	/**
	 * base64_encoder
	 *
	 * @param str
	 * @return
	 */
	public static String encode(String str)
	{
		byte[] stringBytes = null;
		String encoded;

		try
		{
			stringBytes = str.getBytes("UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			logger.error(e);
		}
		catch (NullPointerException e)
		{
			logger.error(e);
		}
		encoded = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(stringBytes);
		return encoded;
	}
}
