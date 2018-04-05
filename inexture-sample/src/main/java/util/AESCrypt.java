package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCrypt {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = LogManager.getLogger(AESCrypt.class.getName());

	private static final String ALGORITHM = "AES";
	private static final String KEY = "1Hbfh667adfDEJ78";

	public static String encrypt(String value) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("encrypt(String) - start"); //$NON-NLS-1$
		}

		Key key = AESCrypt.generateKey();
		Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("encrypt(String) - end"); //$NON-NLS-1$
		}
		return encryptedValue64;

	}

	public static String decrypt(String value) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("decrypt(String) - start"); //$NON-NLS-1$
		}

		Key key = AESCrypt.generateKey();
		Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("decrypt(String) - end"); //$NON-NLS-1$
		}
		return decryptedValue;

	}

	private static Key generateKey() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("generateKey() - start"); //$NON-NLS-1$
		}

		Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(Charset.defaultCharset() ), AESCrypt.ALGORITHM);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("generateKey() - end"); //$NON-NLS-1$
		}
		return key;
	}
}
