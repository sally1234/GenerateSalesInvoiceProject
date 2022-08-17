
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import pkgMdel.Business.FileOperations;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
 /*package salesgeneratorprojectv2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import pkgMdel.Business.InvoiceHeaderBusiness;
import pkgMdel.Business.InvoiceLineBusiness;
import pkgModel.Entity.InvoiceHeader;
import pkgModel.Entity.InvoiceLine;
import salesgeneratorprojectv2.Utils.CSVUtils;

/**
 *
 * @author lenovo
 */
public class SalesGeneratorProjectV2 {

    /**
     * @param args the command line arguments Test main
     */
    public static void main(String[] args){
        // Print the files in the consol in the required format 
        try {
            FileOperations.printInvConsol(FileOperations.readFile("InvoiceHeader.csv", "InvoiceLines.csv"));
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
}
