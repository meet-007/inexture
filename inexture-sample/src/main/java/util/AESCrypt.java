package util;

import java.nio.charset.Charset;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESCrypt {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(AESCrypt.class.getName());

	private static final String ALGORITHM = "AES";
	private static final String KEY = "1Hbfh667adfDEJ78";

	public static String encrypt(String value) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("encrypt(String) - start"); //$NON-NLS-1$
		}

		Key key = AESCrypt.generateKey();
		Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);

		if (logger.isDebugEnabled()) {
			logger.debug("encrypt(String) - end"); //$NON-NLS-1$
		}
		return encryptedValue64;

	}

	public static String decrypt(String value) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("decrypt(String) - start"); //$NON-NLS-1$
		}

		Key key = AESCrypt.generateKey();
		Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		String decryptedValue = new String(decryptedByteValue, "utf-8");

		if (logger.isDebugEnabled()) {
			logger.debug("decrypt(String) - end"); //$NON-NLS-1$
		}
		return decryptedValue;

	}

	private static Key generateKey() throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("generateKey() - start"); //$NON-NLS-1$
		}

		Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(Charset.defaultCharset() ), AESCrypt.ALGORITHM);

		if (logger.isDebugEnabled()) {
			logger.debug("generateKey() - end"); //$NON-NLS-1$
		}
		return key;
	}
}
