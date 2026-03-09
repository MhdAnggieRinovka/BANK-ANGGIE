package programmerzamannow.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import programmerzamannow.spring.core.data.Foo;
import programmerzamannow.spring.core.data.cyclic.MultiFoo;
import programmerzamannow.spring.core.repository.CategoryRepository;
import programmerzamannow.spring.core.repository.CustomerRepository;
import programmerzamannow.spring.core.repository.ProductRepository;
import programmerzamannow.spring.core.service.CategoryService;
import programmerzamannow.spring.core.service.CustomerService;
import programmerzamannow.spring.core.service.ProductService;

public class ComponentTest {
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setup()
    {
        applicationContext = new AnnotationConfigApplicationContext(ComponentConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testService()
    {
        ProductService productService1 = applicationContext.getBean(ProductService.class);
        ProductService productService2 = applicationContext.getBean("productService",ProductService.class);

        Assertions.assertSame(productService1,productService2);

    }

    @Test
    void testConstructorDepedencyInjection()
    {
        ProductService productService = applicationContext.getBean(ProductService.class);
        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);

        Assertions.assertSame(productService.getProductRepository(),productRepository);
    }

    @Test
    void testSetterDepedencyInjection()
    {
       CategoryService categoryService =  applicationContext.getBean(CategoryService.class);

       CategoryRepository categoryRepository = applicationContext.getBean(CategoryRepository.class);

       Assertions.assertSame(categoryService.getCategoryRepository(),categoryRepository);
    }

    @Test
    void testFieldDepedencyInjection()
    {
        CustomerService customerService = applicationContext.getBean(CustomerService.class);

        CustomerRepository normalCustomerRepository = applicationContext.getBean("normalCustomerRepository",CustomerRepository.class);

        CustomerRepository premiumCustomerRepository = applicationContext.getBean("premiumCustomerRepository",CustomerRepository.class);

        Assertions.assertSame(customerService.getNormalCustomerRepository(),normalCustomerRepository);

        Assertions.assertSame(customerService.getPremiumCustomerRepository(),premiumCustomerRepository);
    }

    @Test
    void testObjectProvider()
    {
        MultiFoo multiFoo = applicationContext.getBean(MultiFoo.class);
        Assertions.assertSame(3,multiFoo.getFoos().size());
    }
}
