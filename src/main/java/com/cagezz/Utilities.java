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

           user.home/Downloads/mouseQHome/mouseQTables/Date1.txt
                                                      /date2.txt
                                                      /date3.txt
                                                      /date..txt
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
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH/mm");
        try {
            date=dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String parsedDateString(){
        Date currentDate=new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm");
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
        }
        return created;
    }


    public static boolean createMiceRecordTableFile(ArrayList<Mouse> mice){
        StringBuilder sb=new StringBuilder();
        boolean created =false;

        sb.append("TagNumber,");
        sb.append("Gender," );
        sb.append("BirthDate,");
        sb.append("Strain,");
        sb.append("Father,");
        sb.append("Mother,");
        sb.append("CoatColour,");
        sb.append("WeanDate,");
        sb.append("Genotype,");
        sb.append("Status,"  );
        sb.append("CageNumber,");
        sb.append("Notes,");
        sb.append("\n");

        String dir= tablesFolderDir + File.separator + parsedDateString()+".txt";
        File tableFile=new File(dir);

        /*
        populate the table with mice data
         */
        for(Mouse mouse : mice) {

            sb.append(mouse.getTagNumber()         +",");
            sb.append(mouse.getGender()            +",");
            sb.append(mouse.getBirthDate()         +",");
            sb.append(mouse.getStrain()            +",");
            sb.append(mouse.getPaternalTagNumber() +",");
            sb.append(mouse.getMaternalTagNumber() +",");
            sb.append(mouse.getCoatColour()        +",");
            sb.append(mouse.getWeanDate()          +",");
            sb.append(mouse.getGenotype()          +",");
            sb.append(mouse.getStatus()            +",");
            sb.append(mouse.getCageNumber()        +",");
            sb.append(mouse.getNotes()             +",");
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
