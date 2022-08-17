/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgMdel.Business;

import java.util.ArrayList;
import pkgModel.Entity.InvoiceLine;

/**
 *
 * This class contains the business function of the Invoice Line Object
 */
public class InvoiceLineBusiness {

    // <editor-fold defaultstate="collapsed" desc="Methods">

    // Convert Arrraylist of strings to invoice lines array list 
    public ArrayList<InvoiceLine> convertToInvoiceLines(ArrayList<String> linesFile) {
        ArrayList<InvoiceLine> lines = new ArrayList();
        for (int i = 0; i < linesFile.size(); i++) {
            String[] str = linesFile.get(i).split(",");
            if (str.length > 0) {
                {
                    lines.add(new InvoiceLine(Integer.parseInt(str[0]), str[1], Double.parseDouble(str[2]), Integer.parseInt(str[3])));
                }
            }
        }
        return lines;
    }

    // Convert Arrraylist of invoice lines to string array list 
    public ArrayList<String> convertToString(ArrayList<InvoiceLine> lines, String delimeter) {
        ArrayList<String> str = new ArrayList();
        InvoiceLine invLines;

        for (int i = 0; i < lines.size(); i++) {
            invLines = lines.get(i);
            String item = invLines.getInvoiceNum() + delimeter + invLines.getItemName() + delimeter + invLines.getItemPrice() + delimeter + invLines.getCount();
            str.add(item);
        }
        return str;
    }

      // </editor-fold>
}
