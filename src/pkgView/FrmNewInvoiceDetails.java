/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pkgView;

import pkgController.NewInvoiceDetailsController;
import pkgModel.Entity.InvoiceLine;

/**
 *
 * @author lenovo
 */
public class FrmNewInvoiceDetails extends javax.swing.JFrame {

    public InvoiceLine newLine = new InvoiceLine();

    /**
     * Creates new form frmNewInvoiceDetails
     */
    public FrmNewInvoiceDetails() {
        controller = new NewInvoiceDetailsController(this);
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    public FrmNewInvoiceDetails(int InvoiceNum) {
        controller = new NewInvoiceDetailsController(this);
        initComponents();
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        newLine.setInvoiceNum(InvoiceNum);
        this.lblHeader.setText("New item in invoice number: " + InvoiceNum);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPrice = new javax.swing.JTextField();
        txtPrice.addKeyListener(controller);
        txtItemName = new javax.swing.JTextField();
        txtCount = new javax.swing.JTextField();
        txtCount.addKeyListener(controller);
        btnSave = new javax.swing.JButton();
        btnSave.addActionListener(controller);
        btnCancel = new javax.swing.JButton();
        btnCancel.addActionListener(controller);
        lblItemName = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblCount = new javax.swing.JLabel();
        lblHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Invoice Item");

        txtPrice.setName("txtPrice"); // NOI18N

        txtCount.setName("txtCount"); // NOI18N

        btnSave.setText("Save");
        btnSave.setToolTipText("");

        btnCancel.setText("Cancel");

        lblItemName.setText("Item Name");

        lblPrice.setText("Price:");

        lblCount.setText("Count:");

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHeader.setText("New item in invoice number: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblItemName)
                            .addComponent(lblPrice)
                            .addComponent(lblCount))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(lblHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(lblHeader)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblItemName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCount)
                    .addComponent(txtCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblCount;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblItemName;
    private javax.swing.JLabel lblPrice;
    public javax.swing.JTextField txtCount;
    public javax.swing.JTextField txtItemName;
    public javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

    private NewInvoiceDetailsController controller;
}
