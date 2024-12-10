package cz.sda.java.remotesk1.Invoices.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Primary
//@Service
//@Component
public class InfoServiceBean implements InfoService{

    @Override
    public String getInfo() {
        return "hhh";
    }
}
