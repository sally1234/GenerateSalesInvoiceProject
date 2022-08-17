/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salesgeneratorprojectv2.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * This class is the utility class for the application
 */
public class UtilClass {

    // editor-fold defaultstate="collapsed" desc="Methods">  
    // Read CSV File and parsing iy to ArrayList of String 
    public ArrayList ReadCSVFile(String filePath) throws FileNotFoundException {
        // TODO code application logic here

        //parsing a CSV file into Scanner class constructor  
        Scanner sc = new Scanner(new File(filePath));
        ArrayList<String> memory = new ArrayList();
        while (sc.hasNext()) //returns a boolean value  
        {
            memory.add(sc.next());
        }
        sc.close();  //closes the scanner  

        return memory;
    }

    // Write Array List of String in CSV File 
    public void WriteCSV(ArrayList<String> arr, String filePath) throws FileNotFoundException {

        // write the file in print writer
        PrintWriter writer = new PrintWriter(new File(filePath));

        // converting the arraylist to lines
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            sb.append(arr.get(i));
            sb.append('\n');
        }

        // write the file on hard disk
        writer.write(sb.toString());
        writer.flush();
        writer.close();
    }

    // Converting String to Date Format dd-MM-yyyy
    public Date StringToDate(String dob) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        //Parsing the given String to Date object
        return formatter.parse(dob);

    }
    
    // Format Date to dd/MM/yyyy
    public String DateToString(Date date) {
        DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
        return formatter1.format(date);
    }
    
    // </editor-fold>
}
