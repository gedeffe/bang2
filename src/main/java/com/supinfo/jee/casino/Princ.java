package com.supinfo.jee.casino;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.supinfo.jee.casino"})
public class Princ {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Princ.class);
        PartyManager partyManager = applicationContext.getBean(PartyManager.class);

        System.out.println(partyManager.newParty());
    }
}
