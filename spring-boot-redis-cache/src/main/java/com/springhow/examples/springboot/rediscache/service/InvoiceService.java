package main.java.com.springhow.examples.springboot.rediscache.service;

import java.util.List;

import main.java.com.springhow.examples.springboot.rediscache.entities.Invoice;

public interface InvoiceService {

    public Invoice saveInvoice(Invoice inv);
    public Invoice updateInvoice(Invoice inv, Integer invId);
    public void deleteInvoice(Integer invId);
    public Invoice getOneInvoice(Integer invId);
    public List<Invoice> getAllInvoices();
}