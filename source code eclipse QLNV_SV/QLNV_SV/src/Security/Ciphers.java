package Security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Ciphers {
	private static String password = "N18DCAT100";

    public static String sha1(String input) throws NoSuchAlgorithmException {
        String sha1 = null;
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            byte[] array = msdDigest.digest();
            sha1 = byte2hex(array);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            //Logger.getLogger(Encriptacion.class.getName()).log(Level.SEVERE, null, e);
        }
        return sha1;
    }
    
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }
    
    public static SecretKey getKeyFromPassword(String password)
        throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), password.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }
    
    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        //new SecureRandom().nextBytes(iv);
        //iv = "Example String".getBytes();
        return new IvParameterSpec(iv);
    }

    public static String encryptAES(String input) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
    	IvParameterSpec iv = generateIv();
        SecretKey key = getKeyFromPassword(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        String result = byte2hex(cipherText);
        return result;
    }
    
    
    public static String decryptAES(byte[] cipherText) throws NoSuchPaddingException, NoSuchAlgorithmException,
        InvalidAlgorithmParameterException, InvalidKeyException,
        BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
    	
    	IvParameterSpec iv = generateIv();
        SecretKey key = getKeyFromPassword(password);
    	
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }
    
    
 // Convert Byte Arrary to Hex String
    public static String byte2hex(byte[] b)
    {
       String hs = "";
       String stmp = "";
       for (int n = 0; n < b.length; n++){
          stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
          if (stmp.length() == 1){
             hs = hs + "0" + stmp;
          } else{
             hs = hs + stmp;
          }
          
          if (n < b.length - 1){
             hs = hs + "";
          }
       }
       return "0x" + hs.toUpperCase();
    }
    
    // Convert Hex String to Byte Array
    public static byte[] hex2Byte(String str)
    {
       byte[] bytes = new byte[str.length() / 2];
       for (int i = 0; i < bytes.length; i++)
       {
          bytes[i] = (byte) Integer
                .parseInt(str.substring(2 * i, 2 * i + 2), 16);
       }
       return bytes;
    }
    
}
