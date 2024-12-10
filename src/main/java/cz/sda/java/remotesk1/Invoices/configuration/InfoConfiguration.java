package cz.sda.java.remotesk1.Invoices.configuration;

import cz.sda.java.remotesk1.Invoices.service.InfoService;
import cz.sda.java.remotesk1.Invoices.service.InfoServiceBean;
import cz.sda.java.remotesk1.Invoices.service.WickedBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoConfiguration {

    @Bean
    InfoService infoService(){
        return new InfoServiceBean();
    }

    @Bean
    InfoService wickedBean(){
        return new WickedBean();
    }
}
