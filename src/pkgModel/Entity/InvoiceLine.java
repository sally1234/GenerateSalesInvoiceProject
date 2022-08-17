/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgModel.Entity;

/**
 *
 * This Class represents Invoice Details Items
 */
public class InvoiceLine {
  
    // <editor-fold defaultstate="collapsed" desc="Constructors">

    // Empty constructor    
    public InvoiceLine() {
        this.itemName = "";
        this.itemPrice = 0;
        this.count = 0;
    }
 
    // Set values in constructor
    public InvoiceLine(int invoiceNumber, String itemName, double itemPrice, int count) {
        this.invoiceNum = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variable">    
  
    // Int Invoice Number 
    int invoiceNum;
    // Invoice Detail Item Name
    String itemName;
    // Invoice Detail Item Price 
    double itemPrice;
    // Invoice Detail Item Count 
    int count;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">  
    
// Set the valur of the invoice Number 
    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    // Set the value of Item Name
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    // Set value of Item Price
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    // Set value of Item Count 
    public void setCount(int count) {
        this.count = count;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">  
  
    // Get the value of Invoice Number 
    public int getInvoiceNum() {
        return invoiceNum;
    }

    // Get the value of Item Name
    public String getItemName() {
        return itemName;
    }

    // Get the value of Item Price
    public double getItemPrice() {
        return itemPrice;
    }

    // Get value of Items Count 
    public int getCount() {
        return count;
    }

    // Calculate the Total Price of the Line
    public double getTotalPrice() {
        return count * itemPrice;
    }

    // </editor-fold>
}
