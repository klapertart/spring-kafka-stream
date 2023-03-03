package klapertart.lab.kafkastream.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kurakuraninja
 * @since 20/02/23
 */

@Component
@ConfigurationProperties("ssl")
@Getter
@Setter
@Data
public class SslProperties {
    private boolean enabled;
    private JaasProperties jaas;
    private KeyProperties key;
    private TruststoreProperties truststore;
    private KeystoreProperties keystore;

    @Getter
    @Setter
    @Data
    public static class JaasProperties{
        private String username;
        private String password;
    }

    @Getter
    @Setter
    @Data
    public static class KeyProperties{
        private String password;
    }

    @Getter
    @Setter
    @Data
    public static class TruststoreProperties{
        private String location;
        private String password;
    }

    @Getter
    @Setter
    @Data
    public static class KeystoreProperties{
        private String location;
        private String password;
    }

}
