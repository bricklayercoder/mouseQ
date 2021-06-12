package com.cagezz;


import com.mouse.Mouse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;




/*
        The tables that store mouse records are persistent in the following directory structures:

           user.home/Downloads/mouseQTables/Date1.txt
                                           /date2.txt
                                           /date3.txt
                                           /date..txt
        Each column field is 18 chars or 18 bytes of size

        date is in the format of yyyy-MM-dd hh-mm
*/



public final class Utilities {

    private static String mouseQHome =System.getProperty("user.home")
                                                                + File.separator
                                                                + "Downloads"
                                                                + File.separator
                                                                + "mouseQHome";

    private static String  tablesFolderDir=mouseQHome + File.separator + "mouseTables";


    public static String getMouseQHomeFolder() {
        return mouseQHome;
    }
    public static String getTablesFolderDir()  {return tablesFolderDir;}

    public static Date translateStringToDate(String s){
        Date date=null;
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd hh/mm");
        try {
            date=dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String parsedDateString(){
        Date currentDate=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh/mm");
        String dateString=dateFormat.format(currentDate);
        String parsedDateString=dateString.replace("/", "-");
        return  parsedDateString;
    }

    public static String rightPadWhiteSpaces(String str, int num) {
        return String.format("%1$-" + num + "s", str);

    }
    public static final boolean createMouseQTablesFolder(){
        boolean isCreated=false;
        File thisFile=new File(tablesFolderDir);
        if (thisFile.exists()) return true;
        else {
            Path tablesFolderPath=Paths.get(tablesFolderDir);
            try {
                Files.createDirectories((tablesFolderPath));
                isCreated=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isCreated;

    }

    public static final boolean createMouseQHomeFolder() {

        /*
        create a directory named mouseQTables inside the user's home's Documents directories.
        Invoking this method will create a directory called mouseQTables under user.home/Documents/.

         */
        Boolean created=false;

        File thisFile=new File(mouseQHome);
        if (thisFile.exists()) return true;
        else {
            Path dirPath = Paths.get(mouseQHome);
            try {
                Files.createDirectories(dirPath);
                created=true;
            } catch (IOException e) {
                System.out.println(e);
            }
                return created;
        }
    }


    /*
        In a given date, if the mice table file corresponding to that date exist, return.
        otherwise, create this file and populate it with mice records.
         */
    private static StringBuilder sb=new StringBuilder();
    static boolean created =false;
    static String dir= tablesFolderDir + File.separator + parsedDateString()+".txt";
    static File tableFile=new File(dir);
    private static void appendTableHeader(){

        if (tableFile.exists()) { }
        else {
            try {
                tableFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
                /*
        Write the column headers
         */
        sb.append(rightPadWhiteSpaces("TagNumber,", 18));
        sb.append(rightPadWhiteSpaces("Gender,"   , 18));
        sb.append(rightPadWhiteSpaces("BirthDate,", 18));
        sb.append(rightPadWhiteSpaces("Strain",     18));
        sb.append(rightPadWhiteSpaces("Father,"   , 18));
        sb.append(rightPadWhiteSpaces("Mother,"   , 18));
        sb.append(rightPadWhiteSpaces("CoatColour,",18));
        sb.append(rightPadWhiteSpaces("WeanDate,",  18));
        sb.append(rightPadWhiteSpaces("Genotype," , 18));
        sb.append(rightPadWhiteSpaces("Status,"   , 18));
        sb.append(rightPadWhiteSpaces("CageNumber," , 18));
        sb.append(rightPadWhiteSpaces("Notes,"      , 18));
        //       sb.append(System.getProperty("line.separator"));
        sb.append("\n");

//        return created;
    }

    public static boolean createMiceRecordTableFile(ArrayList<Mouse> mice){

        appendTableHeader();
        /*
        populate the table with mice data
         */
        for(Mouse mouse : mice) {

            sb.append(rightPadWhiteSpaces(mouse.getTagNumber()         +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getGender()            +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getBirthDate()         +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getStrain()            +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getPaterNalTagNumber() +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getMaternalTagNumber() +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getCoatColour()        +",",18));
            sb.append(rightPadWhiteSpaces(mouse.getWeanDate()          +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getGenotype()          +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getStatus()            +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getCageNumber()        +",", 18));
            sb.append(rightPadWhiteSpaces(mouse.getNotes()             +",", 18));
//            sb.append(System.getProperty("line.separator"));
            sb.append("\n");
        }
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(tableFile);
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            bufferedWriter.write(sb.toString());
            bufferedWriter.close();
            fileOutputStream.close();

            created=true;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return created;
    }


}
