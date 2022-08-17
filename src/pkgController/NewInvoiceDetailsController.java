/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import pkgView.FrmNewInvoiceDetails;

/**
 *
 * This class is the controller of frmNewInvoiceDetails
 */
public class NewInvoiceDetailsController implements ActionListener, KeyListener {

      // <editor-fold defaultstate="collapsed" desc="Variables">
    
     // object from JFrame
    private FrmNewInvoiceDetails frm;

       // </editor-fold>
    
      // <editor-fold defaultstate="collapsed" desc="Constructor">
    
    public NewInvoiceDetailsController(FrmNewInvoiceDetails fram) {
        this.frm = fram;
    }

       // </editor-fold>
    
      // <editor-fold defaultstate="collapsed" desc="Override Events">
    
    @Override
    
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // Fired when click save button
            case "Save":
                save();
                break;
            // Fired when click cancel button
            case "Cancel":
                cancel();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Fired when key type in txtprice, if the user try to write char unless '.' and numbers, ignore his typing
        if (e.getComponent().getName().equals("txtPrice")) {
            char c = e.getKeyChar();
            if (((c < '0') || (c > '9')) && (c != java.awt.event.KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                e.consume();
            }
        }// Fired when key type in txtcount, if the user try to write char unless numbers, ignore his typing 
        else if (e.getComponent().getName().equals("txtCount")) {
            char c = e.getKeyChar();
            if (((c < '0') || (c > '9')) && (c != java.awt.event.KeyEvent.VK_BACK_SPACE) && (c != '.')) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
   // </editor-fold>
    
      // <editor-fold defaultstate="collapsed" desc="Methods">
    
    // Save the new values
    void save() {
        
        // applying the required validation 
        if (frm.txtItemName.getText() == null || frm.txtItemName.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter Item Name!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        }// applying the required validation 
        else if (frm.txtPrice.getText() == null || frm.txtPrice.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter Item Price!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        }   // applying the required validation 
        else if (frm.txtCount.getText() == null || frm.txtCount.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter Item Count!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        }   // applying the count number validation 
        else if (Integer.parseInt(frm.txtCount.getText()) <= 0) {
            JOptionPane.showMessageDialog(null, "Please enter valid Item Count!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
               
                // parse the price to double
                Double.valueOf(frm.txtPrice.getText().trim());

                // fill the new line object by the new data 
                frm.newLine.setCount(Integer.parseInt(frm.txtCount.getText()));             
                frm.newLine.setItemName(frm.txtItemName.getText());
                frm.newLine.setItemPrice(Double.valueOf(frm.txtPrice.getText().trim()));

                // close the form
                frm.dispose();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid Item Price!",
                        "Validation message", JOptionPane.ERROR_MESSAGE);
            }
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
