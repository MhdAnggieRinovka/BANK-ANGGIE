package programmerzamannow.spring.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmerzamannow.spring.core.data.Connection;
import programmerzamannow.spring.core.data.Server;

public class LifeCycleTest {

    //Application context tidak ada method yang bernama close maka harus panggil configurableApplicationContext
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    void setup()
    {
        applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfiguration.class);

        // Dibawah ini cara lain selain memanggil method close
        applicationContext.registerShutdownHook();
    }

    @AfterEach
    void tearDown(){
//        applicationContext.close();
    }

    @Test
    void testConnection()
    {
        Connection conn = applicationContext.getBean(Connection.class);
    }

    @Test
    void testServer()
    {
        // Ketika server di create, maka akan memanggil method start
        // Ketika server di destroy, maka akan memanggil method stop
       Server server =  applicationContext.getBean(Server.class);
    }
}
