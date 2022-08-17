/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgMdel.Business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import pkgModel.Entity.InvoiceHeader;
import salesgeneratorprojectv2.Utils.UtilClass;

/**
 *
 * This class contains all File operations Read, Write ,...
 */
public class FileOperations {

     // <editor-fold defaultstate="collapsed" desc="File Methods">

    // This File Read 2 scv files and return array list of invoice headers
    public static ArrayList<InvoiceHeader> readFile(String invPath, String invDetPath) throws FileNotFoundException, ParseException, IOException {

        // object from util class to read csv files
        UtilClass util = new UtilClass();

        // Create 2 AraayList to store the values of 2 files
        ArrayList<String> invHeader = util.ReadCSVFile(invPath);
        ArrayList<String> invLines = util.ReadCSVFile(invDetPath);

        // Create objects from business logic classes to use the related methods
        InvoiceLineBusiness lineBusiness = new InvoiceLineBusiness();
        InvoiceHeaderBusiness invHeaderBusiness = new InvoiceHeaderBusiness();

        // Convert the 2 array lists of string to one array list of headers that contains the related lines in
        return invHeaderBusiness.convertToInvoiceHeader(invHeader, lineBusiness.convertToInvoiceLines(invLines));

    }
    
    //Save the invoice headers and the related lines in the selected 2 files path 
    public static void writeFile(String invHeaderFilePath, String invLineFilePath, ArrayList<InvoiceHeader> invoices) throws FileNotFoundException, ParseException, IOException {

        // Create objects from business logic classes to use the related methods
        InvoiceLineBusiness lineBusiness = new InvoiceLineBusiness();
        InvoiceHeaderBusiness invHeaderBusiness = new InvoiceHeaderBusiness();
        
         // object from util class to read csv files
        UtilClass util = new UtilClass();
        
        // Convert the invoices arraylist to array list of strings with delimter , to save it in the file
        ArrayList<String> strInvoice = invHeaderBusiness.convertToString(invoices, ",");
       
        // create string arraylist to store the invoices lines in
        ArrayList<String> strInvoiceDetails = new ArrayList<>();

       // get al invoice lines from the invoices and convert arraylist to array list of strings with delimter , to save it in the file
         for (int i = 0; i < invoices.size(); i++) {
            strInvoiceDetails.addAll(lineBusiness.convertToString(invoices.get(i).getInvoiceLines(), ","));

            // Save invoice headers file
            util.WriteCSV(strInvoice, invHeaderFilePath);
            
            // Save invoice Lines file
            util.WriteCSV(strInvoiceDetails, invLineFilePath);
        }
    }

    // Read both header and line files and return the result in headers arraylist
    public static ArrayList<InvoiceHeader> readFile(File invHeaderFile, File invLinesFile) throws FileNotFoundException, ParseException, IOException {
        if (invHeaderFile.exists() && invLinesFile.exists()) {
            return readFile(invHeaderFile.getAbsolutePath(), invLinesFile.getAbsolutePath());
        }
        return null;
    }

    // Print the invoices in console in the requested format
    public static void printInvConsol(ArrayList<InvoiceHeader> headers) {

        InvoiceHeader curr;
        UtilClass util = new UtilClass();
        for (int i = 0; i < headers.size(); i++) {
            curr = headers.get(i);
            System.out.println(curr.getInvoiceNum());
            System.out.println("{");
            System.out.println(util.DateToString(curr.getInvoiceDate()) + ", " + curr.getCustomerName());
            for (int j = 0; j < curr.getInvoiceLines().size(); j++) {
                System.out.println(curr.getInvoiceLines().get(j).getItemName() + ", " + curr.getInvoiceLines().get(j).getItemPrice() + ", " + curr.getInvoiceLines().get(j).getCount());
            }
            System.out.println("}");
        }

    }
     // </editor-fold>
    
}
