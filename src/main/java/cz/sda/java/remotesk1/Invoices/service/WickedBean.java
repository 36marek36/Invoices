package cz.sda.java.remotesk1.Invoices.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
//@Service
public class WickedBean implements InfoService{

    @Override
    public String getInfo() {
        return "Wicked Bean";
    }
}
