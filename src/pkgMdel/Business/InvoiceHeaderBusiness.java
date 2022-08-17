/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgMdel.Business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import pkgModel.Entity.InvoiceHeader;
import pkgModel.Entity.InvoiceLine;
import salesgeneratorprojectv2.Utils.UtilClass;

/**
 *
 * This class contains the business function of the Invoice Header Object
 */
public class InvoiceHeaderBusiness {

    // <editor-fold defaultstate="collapsed" desc="Methods">

    // Convert Arrraylist of strings to invoice header array list 
    public ArrayList<InvoiceHeader> convertToInvoiceHeader(ArrayList<String> invHeader, ArrayList<InvoiceLine> lines) throws ParseException {

        ArrayList<InvoiceHeader> headers = new ArrayList();
        UtilClass util = new UtilClass();
        for (int i = 0; i < invHeader.size(); i++) {
            String[] str = invHeader.get(i).split(",");
            if (str.length > 0) {
                {
                    headers.add(new InvoiceHeader(Integer.parseInt(str[0]), util.StringToDate(str[1]), str[2]));
                    headers.get(i).setInvoiceLines(
                            lines.stream()
                                    .filter(p -> p.getInvoiceNum() == Integer.parseInt(str[0]))
                                    .collect(Collectors.toCollection(ArrayList::new)));
                }
            }
        }

        return headers;
    }

     // Convert Arrraylist of invoice header to string array list 
    public ArrayList<String> convertToString(ArrayList<InvoiceHeader> header, String delimeter) {
        ArrayList<String> str = new ArrayList();
        InvoiceHeader invhHeader;

        for (int i = 0; i < header.size(); i++) {
            invhHeader = header.get(i);
            String item = invhHeader.getInvoiceNum() + delimeter + new SimpleDateFormat("dd-MM-yyyy").format(invhHeader.getInvoiceDate()) + delimeter + invhHeader.getCustomerName();
            str.add(item);
        }
        return str;
    }
    
        // </editor-fold>

}
