package com.example.Util;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.hibernate.*;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.*;
import org.hibernate.cfg.Configuration;


@Component
public class HibernateUtil {

    public static Session getSession() {
        
        Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(com.example.Models.AccountDTO.class);
            SessionFactory sessionFactory = configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build());
                    
                   return sessionFactory.openSession();
        
    }

}