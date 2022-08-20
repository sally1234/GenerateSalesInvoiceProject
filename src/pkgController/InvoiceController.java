/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgController;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Comparator.comparing;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import pkgMdel.Business.FileOperations;
import pkgModel.Entity.InvoiceHeader;
import pkgModel.Entity.InvoiceLine;
import pkgView.FrmInvoices;
import pkgView.FrmNewInvoice;
import pkgView.FrmNewInvoiceDetails;
import salesgeneratorprojectv2.Utils.UtilClass;

/**
 *
 * This class is the controller of frmInvoices
 */
public class InvoiceController implements ActionListener, MouseListener {

    // <editor-fold defaultstate="collapsed" desc="Variables">
    // object from JFrame
    private FrmInvoices frm;

    // Current Header invoices array list
    ArrayList<InvoiceHeader> headerInvoicesObj = new ArrayList<>();

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Constructor">
    public InvoiceController(FrmInvoices fram) {
        this.frm = fram;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Override Events">
    // <editor-fold defaultstate="collapsed" desc="ActionListener Events">
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            // Fired when clicking Delete Invoice Line Button in the Jfram
            case "Delete Item":
                deleteInvoiceLine();
                break;
            // Fired when clicking Delete Invoice Button in the Jfram
            case "Delete Invoice":
                deleteInvoice();
                break;
            // Fired when clicking New Invoice Button in the Jfram
            case "Create New Invoice":
                addNewInvoice();
                break;
            // Fired when clicking New Invoice Line Button in the Jfram
            case "Create New Item":
                addInvoiceLine();
                break;
            // Fired when clicking Open Files menu item
            case "Open File":
                openFiles(null, null);
                break;
            // Fired when clicking Save Files menu item
            case "Save File":
                saveFiles();
                break;
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="MouseListener Events">
    @Override
    public void mouseClicked(MouseEvent e) {
        // Fired when select row in tblInvoices to bind the lines and details
        if (e.getComponent().getName().equals("tblInvoices")) {
            invoiceDetailsReset();
            fillInvoiceDetails();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Fired when select row in tblInvoices to update the invoice header data
        if (e.getComponent().getName().equals("tblInvoices")) { 
            updateInvoice();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // </editor-fold>
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Methods">
    // OPen New Invoice form, then add the new item in the invoices object 
    void addNewInvoice() {

        // Object from New Invoice jfram
        FrmNewInvoice newFrm;

        // Get the mreater invoice number in the list to generate new invoice number
        InvoiceHeader max = headerInvoicesObj.stream()
                .max(comparing(InvoiceHeader::getInvoiceNum))
                .orElse(null);

        // set the new invoice ID in the form
        if (max != null && max.getInvoiceNum() > 0) {
            newFrm = new FrmNewInvoice(max.getInvoiceNum() + 1);
        } else {
            newFrm = new FrmNewInvoice(1);
        }

        // When close the new invoice form after clicking save button, take the values and add a new object to the list
        newFrm.dispatchEvent(new WindowEvent(newFrm, WindowEvent.WINDOW_CLOSING));
        newFrm.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Get the values fro the form then close it 
                if (newFrm.newInvoice.getCustomerName().trim().length() > 0) {
                    DefaultTableModel model = (DefaultTableModel) frm.tblInvoices.getModel();
                    addNewRowInvoice(model, newFrm.newInvoice);
                    //add new object in the array list
                    headerInvoicesObj.add(newFrm.newInvoice);
                }
            }
        });
        newFrm.show();
    }

    void updateInvoice()
    {
        if (frm.selectedInvIndex >=0)
        {
            headerInvoicesObj.get(frm.selectedInvIndex).setCustomerName(frm.txtCustomerName.getText());
            headerInvoicesObj.get(frm.selectedInvIndex).setInvoiceDate(frm.clnInvoiceDate.getDate());
        }
    }
    
    // OPen New Invoice Line form, then add the new item in the invoices object 
    void addInvoiceLine() {

        // Object from New Invoice Line jfram
        FrmNewInvoiceDetails frmNew;

        // get the selected invoice header to get the related invoice number 
        if (headerInvoicesObj.get(frm.selectedInvIndex) != null) {
            frmNew = new FrmNewInvoiceDetails(headerInvoicesObj.get(frm.selectedInvIndex).getInvoiceNum());
        } else {
            frmNew = new FrmNewInvoiceDetails();
        }

        // When close the new invoice line form after clicking save button, take the values and add a new object to the related list
        frmNew.dispatchEvent(new WindowEvent(frmNew, WindowEvent.WINDOW_CLOSING));
        frmNew.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                // Get the values fro the form then close it 
                if (frmNew.newLine.getItemName().trim().length() > 0) {
                    DefaultTableModel model = (DefaultTableModel) frm.tblInvoiceDetails.getModel();
                    addNewRowInvoiceDetails(model, frmNew.newLine);
                    //add new object in the array list
                    headerInvoicesObj.get(frm.selectedInvIndex).addInvoiceDetail(frmNew.newLine);
                    // update the calculated items
                    updateCalculations();
                }
            }
        });
        frmNew.show();
    }

    // Reset All Controls
    void resetFormControlles() {
        ((DefaultTableModel) frm.tblInvoices.getModel()).setRowCount(0);
        invoiceDetailsReset();
    }

    // Reset Invoice Details Controls 
    void invoiceDetailsReset() {
        frm.lblInvoiceNumberValue.setText("");
        frm.lblTotalPriceValue.setText("");
        frm.lblNumberOfItemsValue.setText("");
        frm.txtCustomerName.setText("");
        frm.clnInvoiceDate.setDate(new Date());
        ((DefaultTableModel) frm.tblInvoiceDetails.getModel()).setRowCount(0);

    }

    // update the calculated invoice lines values
    void updateCalculations() {

        frm.lblTotalPriceValue.setText(headerInvoicesObj.get(frm.selectedInvIndex).getTotalPrice() + "");
        frm.lblNumberOfItemsValue.setText(headerInvoicesObj.get(frm.selectedInvIndex).getItemsCount() + "");

        DefaultTableModel model = (DefaultTableModel) frm.tblInvoices.getModel();
        int[] rows = frm.tblInvoices.getSelectedRows();
        if (rows.length > 0) {
            model.setValueAt(headerInvoicesObj.get(frm.selectedInvIndex).getTotalPrice(), rows[0], 3);
        }
    }

    // add new invoice line in the tblinvoiceDetails Jtables
    void addNewRowInvoiceDetails(DefaultTableModel model, InvoiceLine line) {
        Object[] rowData = new Object[model.getColumnCount()];
        rowData[0] = line.getItemName();
        rowData[1] = line.getItemPrice();
        rowData[2] = line.getCount();
        rowData[3] = line.getTotalPrice();
        model.addRow(rowData);

    }

    // add new invoice line in the tblinvoiceHeader Jtables
    void addNewRowInvoice(DefaultTableModel model, InvoiceHeader inv) {
        UtilClass util = new UtilClass();
        Object[] rowData = new Object[model.getColumnCount()];
        rowData[0] = inv.getInvoiceNum();
        rowData[1] = util.DateToString(inv.getInvoiceDate());
        rowData[2] = inv.getCustomerName();
        rowData[3] = inv.getTotalPrice();
        model.addRow(rowData);

    }

    // Bind the invoice Details and Lines controls based on the selected invoice 
    void fillInvoiceDetails() {

        // get the selected rows
        frm.selectedInvIndex = frm.tblInvoices.getSelectedRow();
        // if there is at least one selected row
        if (frm.selectedInvIndex > -1) {

            // Bind the invoice details controls from the invice objects
            frm.lblInvoiceNumberValue.setText(headerInvoicesObj.get(frm.selectedInvIndex).getInvoiceNum() + "");
            frm.txtCustomerName.setText(headerInvoicesObj.get(frm.selectedInvIndex).getCustomerName());
            frm.lblTotalPriceValue.setText(headerInvoicesObj.get(frm.selectedInvIndex).getTotalPrice() + "");
            frm.lblNumberOfItemsValue.setText(headerInvoicesObj.get(frm.selectedInvIndex).getItemsCount() + "");
            frm.clnInvoiceDate.setDate(headerInvoicesObj.get(frm.selectedInvIndex).getInvoiceDate());

            // Bind the related invovice lines from the selected invice object
            DefaultTableModel model = (DefaultTableModel) frm.tblInvoiceDetails.getModel();
            for (int i = 0; i < headerInvoicesObj.get(frm.selectedInvIndex).getInvoiceLines().size(); i++) {
                addNewRowInvoiceDetails(model, headerInvoicesObj.get(frm.selectedInvIndex).getInvoiceLines().get(i));
            }
        }
    }

    // Delete the selected invoice from the list 
    void deleteInvoice() {

        // Confirmation message 
        int result = JOptionPane.showConfirmDialog(null, "Are You want to delete the selected invoice?", "Delete Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // if the user confirmed, delete the selected invoice from the object list 
        if (result == JOptionPane.YES_OPTION) {
            {
                // get the selected invoice rows
                DefaultTableModel model = (DefaultTableModel) frm.tblInvoices.getModel();
                int[] rows = frm.tblInvoices.getSelectedRows();
                // for all selected rows, remove the related invoices from table and object list 
                for (int i = 0; i < rows.length; i++) {
                    model.removeRow(rows[i] - i);
                    headerInvoicesObj.remove(rows[i] - i);
                }
                // Reset the invoice details controls
                invoiceDetailsReset();
            }
        }
    }

    // Delete invoice Line 
    void deleteInvoiceLine() {

        // Confirmation message 
        int result = JOptionPane.showConfirmDialog(null, "Are You want to delete the selected item?", "Delete Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        // if the user confirmed, delete the selected invoice lime from the related object list 
        if (result == JOptionPane.YES_OPTION) {
            {
                // get the selected invoice rows
                DefaultTableModel model = (DefaultTableModel) frm.tblInvoiceDetails.getModel();
                int[] rows = frm.tblInvoiceDetails.getSelectedRows();
                // for all selected rows, remove the related invoices from table and object list 
                for (int i = 0; i < rows.length; i++) {
                    model.removeRow(rows[i] - i);
                    headerInvoicesObj.get(frm.selectedInvIndex).removeInvoiceDetail(rows[i] - i);
                }
                // update the calculated items 
                updateCalculations();
            }
        }
    }

    //Save the Files 
    void saveFiles() {

        try {

            // Create new Files for invoices and lines
            File fileInvoiceHeaderToSave;
            File fileInvoiceLinesToSave;

            // open Chooser file twice to get the 2 new files to save 
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save invoice header");
            // Allowing only csv files to select
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
            fileChooser.addChoosableFileFilter(filter);
            fileChooser.setAcceptAllFileFilterUsed(false);

            // get the selected files
            int userSelection = fileChooser.showSaveDialog(frm);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                {
                    // get selected file and set it to the created file
                    fileInvoiceHeaderToSave = fileChooser.getSelectedFile();
                    fileChooser.setDialogTitle("Specify a file to save invoice lines");
                    // open it again to get the lines file 
                    userSelection = fileChooser.showSaveDialog(frm);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        fileInvoiceLinesToSave = fileChooser.getSelectedFile();
                        String invFilePath = fileInvoiceHeaderToSave.getAbsolutePath();
                        String invFileLinePath = fileInvoiceLinesToSave.getAbsolutePath();

                        if (!invFilePath.endsWith(".csv")) {
                            invFilePath += ".csv";
                        }
                        if (!invFileLinePath.endsWith(".csv")) {
                            invFileLinePath += ".csv";
                        }

                        // Save the invoices and lines in the 2 selected files and write it to hard disk
                        FileOperations.writeFile(invFilePath, invFileLinePath, headerInvoicesObj);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // display error message when file not found 
            JOptionPane.showMessageDialog(null, "File Not Found!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (ParseException e) {
            // display error message when Incorrect data 
            JOptionPane.showMessageDialog(null, "Incorrect Date, Kindly recheck the file then try again",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            // display error message when Incorrect file format
            JOptionPane.showMessageDialog(null, "Incorrect File Format",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            // display error message when any other exception happpend 
            JOptionPane.showMessageDialog(null, "Incorrect File",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        }
    }

    //Open the Files
    public void openFiles(String invFilePath, String invDetailsPath) {
        try {
            // Create new Files for invoices and lines
            File invHeaderFile = null;
            File invLinesFile = null;

            // happened when click open file menu item
            if (invDetailsPath == null && invFilePath == null) {

                // open Chooser file twice to get the 2 new files to save 
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setDialogTitle("Specify a file to open invoice header");
                // Allowing only csv files to select
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setAcceptAllFileFilterUsed(false);

                // get the selected files
                int result = fileChooser.showOpenDialog(frm);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // get selected file and set it to the created file
                    invHeaderFile = fileChooser.getSelectedFile();
                    fileChooser.setDialogTitle("Specify a file to open invoice lines");
                    // open it again to get the lines file 
                    result = fileChooser.showOpenDialog(frm);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        invLinesFile = fileChooser.getSelectedFile();
                    }
                }
            } //happened when loaidng the application, read the default files 
            else {
                invHeaderFile = new File(invFilePath);
                invLinesFile = new File(invDetailsPath);
            }

            // if both files loaded correctly
            if (invHeaderFile != null && invLinesFile != null) {
                // reset all controls to fill the new data 
                resetFormControlles();
                // read new files data 
                headerInvoicesObj = FileOperations.readFile(invHeaderFile, invLinesFile);
            }

            // the file read correct
            if (headerInvoicesObj != null) {

                // Fill invoices jtable 
                DefaultTableModel model = (DefaultTableModel) frm.tblInvoices.getModel();
                for (int i = 0; i < headerInvoicesObj.size(); i++) {
                    addNewRowInvoice(model, headerInvoicesObj.get(i));
                }
                // if object has data so fill the related details
                if (!headerInvoicesObj.isEmpty()) {
                    frm.tblInvoices.changeSelection(0, 0, false, false);
                    fillInvoiceDetails();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Path Not Found!",
                        "Validation message", JOptionPane.ERROR_MESSAGE);
            }

        } catch (FileNotFoundException e) {
            // display error message when file not found 
            JOptionPane.showMessageDialog(null, "File Not Found!",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (ParseException e) {
            // display error message when Incorrect data 
            JOptionPane.showMessageDialog(null, "Incorrect Date, Kindly recheck the file then try again",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            // display error message when Incorrect file format
            JOptionPane.showMessageDialog(null, "Incorrect File Format",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            // display error message when any other exception happpend 
            JOptionPane.showMessageDialog(null, "Incorrect File",
                    "Validation message", JOptionPane.ERROR_MESSAGE);
            System.out.print(Arrays.toString(e.getStackTrace()));
        }
    }

    // </editor-fold>
}
