package cz.sda.java.remotesk1.Invoices.service;

import cz.sda.java.remotesk1.Invoices.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Primary
//@Service
//@Component

public class InfoServiceBean implements InfoService{

    private final ApplicationConfiguration applicationConfiguration;

    public InfoServiceBean(ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    public String getInfo() {
        return String.format("version: %s",applicationConfiguration.getVersion()) ;
    }
}
