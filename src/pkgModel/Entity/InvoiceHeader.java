/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgModel.Entity;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * This class represents the Invoice Header data
 */
public class InvoiceHeader {

      
    // <editor-fold defaultstate="collapsed" desc="Constructors">        
   
    // Empty constructor
    public InvoiceHeader() {
        // this.invoiceDate = Date.now();
        this.invoiceNum = 0;
        this.customerName = "";       
    }

    // Set values in constructor
    public InvoiceHeader(int InvNum, Date invDate, String customerName) {
        this.invoiceDate = invDate;
        this.invoiceNum = InvNum;
        this.customerName = customerName;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variables">    
    
    // Invoice Number
    private int invoiceNum;
    // Invoice Date
    private Date invoiceDate;
    // Customer Name
    private String customerName;
    // Invoice Details items
    private ArrayList<InvoiceLine> invoiceLines;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">  
   
    // Set the value of Invoice number
    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    // Set the value of Invoice Date
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    // Set value of customer name 
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Set values of Invoices Details items
    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        if(this.invoiceLines==null)
            this.invoiceLines = new ArrayList();
        this.invoiceLines = invoiceLines;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Getters">  
   
    // Get value of Invoice Number
    public int getInvoiceNum() {
        return invoiceNum;
    }

    // Get the value of Invoice Date
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    // Get the value of Customer Name
    public String getCustomerName() {
        return customerName;
    }

    // Get the value of Invoice Details items
    public ArrayList<InvoiceLine> getInvoiceLines() {
          if(this.invoiceLines==null)
            this.invoiceLines = new ArrayList();
        return invoiceLines;
    }

    // Get the total price of all item Lines in the current invoice
    public double getTotalPrice() {
        double totalPrice = 0;
        if (invoiceLines ==null )
            invoiceLines = new ArrayList<>();
        for (int i = 0; i < invoiceLines.size(); i++) {
            totalPrice += ((InvoiceLine) invoiceLines.get(i)).getTotalPrice();
        }
        return totalPrice;
    }
 
    // Get Item Lines Count in the current invoice
    public int getItemsCount() {
        return invoiceLines.size();
    }   
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">  

    // Add Invoice Line to the current collection
    public void addInvoiceDetail(InvoiceLine line) {
        this.invoiceLines.add(line);
    }

    // Remove specific invoice line from the current collection using line index
    public void removeInvoiceDetail(int index)
    {
        this.invoiceLines.remove(index);
    }
    
    

     // </editor-fold>

}
