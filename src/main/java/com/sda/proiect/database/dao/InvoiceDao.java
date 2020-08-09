package com.sda.proiect.database.dao;

import com.sda.proiect.models.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDao {

    private final SessionFactory sessionFactory;

    public InvoiceDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean createInvoice(Invoice invoice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(invoice);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<Invoice> getInvoices() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Invoice", Invoice.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
