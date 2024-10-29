package org.escuelaing.eci.config;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyPairConfig {
    private static final String dir = System.getProperty("user.dir") + "/src/main/resources";

    public void KeyGenerator() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        try (FileOutputStream fos = new FileOutputStream("public.key")) {
            fos.write(publicKey.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream fos = new FileOutputStream("private.key")) {
            fos.write(privateKey.getEncoded());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static RSAPrivateKey getPrivate() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        File privateKeyFile = new File(dir + "/private.key");
        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) kf.generatePrivate(spec);
    }

    public static RSAPublicKey getPublic() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        File publicKeyFile = new File(dir +"/public.key");
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        keyFactory.generatePublic(publicKeySpec);

        return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
    }

}
