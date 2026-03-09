package programmerzamannow.spring.core.client;

import lombok.Data;

@Data
public class PaymentGatewayClient {
    private String endPoint;

    private String privateKey;

    private String publicKey;
}
