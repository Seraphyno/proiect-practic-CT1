package com.sda.proiect;

import com.sda.proiect.database.configuration.HibernateConfiguration;
import com.sda.proiect.services.OrchestratorService;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // deschid o sesiune la inceput cu scopul de a o refolosi pe parcursul rularii
        SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

        OrchestratorService orchestratorService = new OrchestratorService(sessionFactory);
        orchestratorService.runApplication();

        sessionFactory.close();
    }
}
