package treePermit;

import java.util.Arrays;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

    private String brokerUrl = "tcp://localhost:61616"; // Beispiel-URL für ActiveMQ

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setTrustedPackages(Arrays.asList("treePermit.model", "java.util", "java.lang"));  // Setze das vertrauenswürdige Paket
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(connectionFactory());
    }
}
