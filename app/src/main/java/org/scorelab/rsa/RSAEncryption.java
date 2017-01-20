package org.scorelab.rsa;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by kasun on 1/20/17.
 */
public class RSAEncryption {
    private static KeyPair keyPair=null;
    private static Cipher cipher = null;

    public RSAEncryption(int size) throws NoSuchAlgorithmException, NoSuchPaddingException {
        //Generate keys
        if(keyPair==null){
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(size,new SecureRandom());
            keyPair = keyGen.generateKeyPair();
        }
        if(cipher==null) cipher = Cipher.getInstance("RSA/NONE/PKCS1Padding");
     }

    public byte[] encrypt(String msg) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // encryption step
        cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPublic());
        byte[] cipherText = cipher.doFinal(msg.getBytes());
        return cipherText;
    }

    public byte[] decrypt(byte[] cipherText) throws InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException{
        // decryption step
        cipher.init(Cipher.DECRYPT_MODE,keyPair.getPrivate());
        byte[] plainText = cipher.doFinal(cipherText);
        return plainText;
    }


}
