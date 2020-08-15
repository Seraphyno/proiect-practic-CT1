package com.sda.proiect.database.dao;

import com.sda.proiect.database.configuration.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;

public class InvoiceDaoTest {

    SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
    InvoiceDao target = new InvoiceDao(sessionFactory);

    @Test
    public void t1_createInvoice() {
        // Given - creez un input pt salvare, consider ca nu exista nimic in db

        // When - apelez metoda createInvoice() din dao - care ar tb sa salveze in db

        // Then - apelez cu dao un getAll din db si ma asigur ca s-a salvat inputul de la apelul anterior
    }

    @Test
    public void t2_getInvoices() {

    }

    @After
    public void resetDb() {
        // curat tot continutul din db, dupa fiecare test
    }
}