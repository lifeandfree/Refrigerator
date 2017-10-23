package ru.innopolis.refrigerator.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import org.apache.tomcat.util.codec.binary.Base64;

public class PasswordEncoder {

	private static final Logger logger = LogManager.getLogger(PasswordEncoder.class.getName());

	public static final String SALT = "1\u00052\u000B34567890abcdejkzyx";

	/**
	 * Кодирование пароля
	 * Алгоритм: 1. Пароль кодируется с помощью base64 2. Длина значения
	 * константы SALT выравнивается по длине полученного закодированного пароля.
	 * Если длина значения SALT меньше требуемой длины, то SALT добавляется к
	 * самому себе необходимое число раз; 3. Производится операция XOR между
	 * закодированным паролем и выровненным по длине SALT; 4. Полученное
	 * значение вновь кодируется по base64.
	 *
	 * @param password
	 *            Кодируемый пароль
	 * @return {base64_encoded string}
	 */
	public static String passwordEncode(String password)
	{
		String salt, password64, encodedPassword64 = "", password64New = "";

		int passLength;

		if (PasswordEncoder.SALT != null)
		{
			salt = PasswordEncoder.SALT;
			password64 = ru.innopolis.refrigerator.service.utils.enconding.Base64.encode(password);
			passLength = password64.length();
			while (salt.length() < passLength)
			{
				salt = salt.concat(salt);
			}
			salt = salt.substring(0, passLength);

			for (int i = 0; i < passLength; i++)
			{
				int c = password64.charAt(i);
				int s = salt.charAt(i);
				int cs = c ^ s;
				String symbol = String.valueOf(Character.toChars(cs));
				password64New = password64New.concat(symbol);
			}
			encodedPassword64 = ru.innopolis.refrigerator.service.utils.enconding.Base64.encode(password64New);
		}
		return encodedPassword64;
	}
//	public static String md5(String password) {
//		String result = null;
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] array = password.getBytes();
//			byte[] arraySecond = md.digest(array);
//			result = new String(arraySecond);
//		} catch (NoSuchAlgorithmException e) {
//			logger.error(e.getMessage(), e.toString(), e.getStackTrace());
//		}
//		return result;
//	}
//
//	public static String encode(String password) {
//		String result = md5(password) + "sadasfdsa";
//		StringBuilder a = new StringBuilder(result).reverse();
//		result = a.toString();
//		result = md5(result);
//		return result.replaceAll("\u0000", "");//TODO PostgreSQL UTF-8
//	}
}
