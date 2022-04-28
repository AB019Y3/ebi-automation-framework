package africa.absa.ebi.Utilities;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordUtil {
    private String myEncryptionKey = "ThisIsSpartaThisIsSparta";
    byte[] arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
    private SecretKeyFactory skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
    private KeySpec ks = new DESedeKeySpec(arrayBytes);
    private SecretKey key = skf.generateSecret(ks);
    private Cipher cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

    public PasswordUtil()
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException,
            InvalidKeyException, UnsupportedEncodingException {}

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    @Test
    public void enc() {
        System.out.println(encrypt("Thabang@91"));
    }

    @Test
    public void dec() {
        System.out.println(decrypt("AQAAABAAAAAQRvv6ut48QZgHYC47FhOm14Y1oKTsI9r9kEiSnIHQWwU="));
    }
}
