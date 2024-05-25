package dev.kaio.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class GenerateRSAKeys {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();


            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            String privateKeyContent = "-----BEGIN PRIVATE KEY-----\n"
                    + Base64.getMimeEncoder().encodeToString(pkcs8EncodedKeySpec.getEncoded())
                    + "\n-----END PRIVATE KEY-----";
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/META-INF/resources/privateKey.pem")) {
                fos.write(privateKeyContent.getBytes());
            }


            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            String publicKeyContent = "-----BEGIN PUBLIC KEY-----\n"
                    + Base64.getMimeEncoder().encodeToString(x509EncodedKeySpec.getEncoded())
                    + "\n-----END PUBLIC KEY-----";
            try (FileOutputStream fos = new FileOutputStream("src/main/resources/META-INF/resources/publicKey.pem")) {
                fos.write(publicKeyContent.getBytes());
            }

            System.out.println("Chaves RSA geradas com sucesso.");
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
