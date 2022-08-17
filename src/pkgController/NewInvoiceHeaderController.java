/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import pkgView.FrmNewInvoice;

/**
 *
 *  This class is the controller of frmNewInvoiceDetails
 */
public class NewInvoiceHeaderController implements ActionListener {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    
     // object from JFrame
    private FrmNewInvoice frm;

       // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public NewInvoiceHeaderController(FrmNewInvoice fram) {
        this.frm = fram;
    }
// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Override Events">
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // Fired when save button clicked
            case "Save":
                save();
                break;
            // Fired when cancel button clicked
            case "Cancel":
                cancel();
                break;
        }
    }
 // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // Save the new invoice in the object 
    void save() {
         // applying the required validation 
        if (frm.txtCustomerName.getText() == null || frm.txtCustomerName.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter Customer Name!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        }  // applying the required validation 
        else if (frm.clnInvoiceDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Please enter Invoice Date!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        } else {
            // fill the new invoice object by the new data 
            frm.newInvoice.setCustomerName(frm.txtCustomerName.getText().trim());
            frm.newInvoice.setInvoiceDate(frm.clnInvoiceDate.getDate());

            frm.dispose();
        }
    }

      // cancel the changes 
    void cancel() {
        
         // display confimraion message then close the form 
        int result = JOptionPane.showConfirmDialog(null, "Are You want to close the form without save?", "Close Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (result == JOptionPane.YES_OPTION) {
            frm.dispose();
        }
    }
  // </editor-fold>
}
