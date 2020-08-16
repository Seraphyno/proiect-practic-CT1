package com.sda.proiect.database.dao;

import com.sda.proiect.models.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceDaoTest {

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Transaction transactionMock;

    InvoiceDao target;

    @Before
    public void setup() {
        when(sessionFactoryMock.openSession()).thenReturn(sessionMock);
        when(sessionMock.beginTransaction()).thenReturn(transactionMock);

        // intorc empty string pt ca ma obliga Mockito sa intorc ceva
        when(sessionMock.save(any(Invoice.class))).thenReturn("");
        // apelez commit in afara whenului pt ca 'intoarce' void
        doNothing().when(transactionMock).commit();

        target = new InvoiceDao(sessionFactoryMock);
    }

    @Test
    public void t1_createInvoicePositive() {
        // Given - creez un input pt salvare, consider ca nu exista nimic in db
        Invoice invoice = new Invoice();

        // When - apelez metoda createInvoice() din dao - care ar tb sa salveze in db
        boolean result = target.createInvoice(invoice);

        // Then - apelez cu dao un getAll din db si ma asigur ca s-a salvat inputul de la apelul anterior
        assertTrue(result);
        verify(transactionMock).commit();
    }

    @Test
    public void t1_createInvoiceNegative() {
        when(sessionMock.save(any(Invoice.class))).thenAnswer(answer -> {
            throw new Exception();
        });
        doNothing().when(transactionMock).rollback();

        // Given - creez un input pt salvare, consider ca nu exista nimic in db
        Invoice invoice = new Invoice();

        // When - apelez metoda createInvoice() din dao - care ar tb sa salveze in db
        boolean result = target.createInvoice(invoice);

        // Then - apelez cu dao un getAll din db si ma asigur ca s-a salvat inputul de la apelul anterior
        assertFalse(result);
        verify(transactionMock).rollback();
    }

    @Test
    public void t2_getInvoices() {
        // TODO - tema
    }
}