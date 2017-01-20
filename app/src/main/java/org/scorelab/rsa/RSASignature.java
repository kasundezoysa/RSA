package org.scorelab.rsa;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

/**
 * Created by kasun on 1/20/17.
 */
public class RSASignature {
    private static KeyPair keyPair=null;
    private static Signature signature = null;

    public RSASignature(int size) throws NoSuchAlgorithmException {
        //Generate keys
        if(keyPair==null){
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(size,new SecureRandom());
            keyPair = keyGen.generateKeyPair();
        }
        //Initialize Signature Object
        if(signature==null) signature = Signature.getInstance("SHA256withRSA");
    }


    public byte[] sign(String msg) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException {
        // generate a signature
        signature.initSign(keyPair.getPrivate());
        signature.update(msg.getBytes());
        return signature.sign();
    }

    public boolean verify(String msg,byte[] sig) throws NoSuchAlgorithmException,
            InvalidKeyException, SignatureException{
        // verify a signature
        signature.initVerify(keyPair.getPublic());
        signature.update(msg.getBytes());
        if (signature.verify(sig)) return true;
        else return false;
    }
}