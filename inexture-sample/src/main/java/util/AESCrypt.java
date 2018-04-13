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

// TODO: Auto-generated Javadoc
/**
 * The Class AESCrypt.
 */
public final class AESCrypt {
	
	/** Logger for this class. */

	private static final Logger LOGGER = LogManager.getLogger(AESCrypt.class.getName());

	/** The Constant ALGORITHM. */
	private static final String ALGORITHM = "AES";
	
	/** The Constant KEY. */
	private static final String KEY = "1Hbfh667adfDEJ78";
	
	/**
	 * Instantiates a new AES crypt.
	 */
	private AESCrypt() {}
	
	/**
	 * Encrypt.
	 *
	 * @param value the value
	 * @return the string
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static String encrypt(final String value) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("encrypt(String) - start"); //$NON-NLS-1$
		}

		final Key key = AESCrypt.generateKey();
		final Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		final byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		final String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("encrypt(String) - end"); //$NON-NLS-1$
		}
		return encryptedValue64;

	}

	/**
	 * Decrypt.
	 *
	 * @param value the value
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 */
	public static String decrypt(final String value) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, IllegalBlockSizeException, BadPaddingException  {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("decrypt(String) - start"); //$NON-NLS-1$
		}

		final Key key = AESCrypt.generateKey();
		final Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		final byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		final byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
		final String decryptedValue = new String(decryptedByteValue, "utf-8");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("decrypt(String) - end"); //$NON-NLS-1$
		}
		return decryptedValue;

	}

	/**
	 * Generate key.
	 *
	 * @return the key
	 */
	private static Key generateKey() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("generateKey() - start"); //$NON-NLS-1$
		}

		final Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(Charset.defaultCharset() ), AESCrypt.ALGORITHM);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("generateKey() - end"); //$NON-NLS-1$
		}
		return key;
	}
}
