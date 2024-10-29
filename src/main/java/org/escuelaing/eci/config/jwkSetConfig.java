package org.escuelaing.eci.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.interfaces.RSAPublicKey;


@Configuration
public class jwkSetConfig {
    // private static final String JWK_KID = "gur-id";
    @Bean
    public JWKSet jwkSet() throws Exception {
        RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) KeyPairConfig.getPublic())
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256);
        return new JWKSet(builder.build());
    }
}
