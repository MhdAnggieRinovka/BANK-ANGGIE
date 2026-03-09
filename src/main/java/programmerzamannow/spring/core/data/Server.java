package programmerzamannow.spring.core.data;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Slf4j
public class Server {

    @PostConstruct
    void start()
    {
        log.info("Start server");
    }

    @PreDestroy
    void stop()
    {
        log.info("Stop server");
    }
//
//    @Override
//    public void destroy() throws Exception {
//        stop();
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        start();
//    }
}
