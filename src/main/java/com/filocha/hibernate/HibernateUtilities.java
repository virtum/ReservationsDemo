package com.filocha.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateUtilities {

  @Value("${hibernateConfigPath}")
  private String hibernateConfigPath;

  @Bean
  public SessionFactory getSessionFactory() {

    org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure(hibernateConfigPath);

    // need for mapping to work properly
    configuration.addClass(com.filocha.hibernate.UserModel.class);
    configuration.addClass(com.filocha.hibernate.ParkingModel.class);
    configuration.addClass(com.filocha.hibernate.BookModel.class);
    configuration.addClass(com.filocha.hibernate.ParticipantsListModel.class);
    configuration.addClass(com.filocha.hibernate.TrainingModel.class);

    ServiceRegistry serviceRegisty = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
        .build();
    return configuration.buildSessionFactory(serviceRegisty);
  }
}
