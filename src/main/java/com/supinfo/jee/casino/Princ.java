package com.supinfo.jee.casino;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Princ {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("services.xml");
        PartyManager partyManager = applicationContext.getBean(PartyManager.class);

        System.out.println(partyManager.newParty());
    }
}
