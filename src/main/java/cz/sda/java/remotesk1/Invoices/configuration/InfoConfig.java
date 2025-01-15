package cz.sda.java.remotesk1.Invoices.configuration;

import cz.sda.java.remotesk1.Invoices.service.InfoService;
import cz.sda.java.remotesk1.Invoices.service.InfoServiceBean;
import cz.sda.java.remotesk1.Invoices.service.WickedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InfoConfig {

    @Bean
    InfoService infoService(@Autowired ApplicationConfiguration applicationConfiguration) {
        return new InfoServiceBean(applicationConfiguration);
    }

    @Bean
    InfoService wickedBean() {
        return new WickedBean();
    }
}
