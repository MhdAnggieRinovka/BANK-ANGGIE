package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.service.MerchantService;
import programmerzamannow.spring.core.service.MerchantServiceImpl;

public class InheritanceTest {
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setup()
    {
        applicationContext = new AnnotationConfigApplicationContext(InheitanceConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testInheritance()
    {
        MerchantService merchantService = applicationContext.getBean(MerchantService.class);
        MerchantServiceImpl merchantService1 = applicationContext.getBean(MerchantServiceImpl.class);

        Assertions.assertSame(merchantService, merchantService1);
    }
}
