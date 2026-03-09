package programmerzamannow.spring.core.Factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import programmerzamannow.spring.core.client.PaymentGatewayClient;

@Component
public class PaymentGatewayClientFactoryBean implements FactoryBean<PaymentGatewayClient> {

    @Override
    public PaymentGatewayClient getObject() throws Exception {
        PaymentGatewayClient client = new PaymentGatewayClient();
        client.setEndPoint("https://payment-gateway.com");
        client.setPrivateKey("private");
        client.setPublicKey("public");
        return client;
    }

    @Override
    public Class<?> getObjectType() {
        return PaymentGatewayClient.class;
    }
}
