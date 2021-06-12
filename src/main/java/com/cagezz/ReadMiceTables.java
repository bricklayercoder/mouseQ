package com.cagezz;

/*
Methods in this class read or write into the overall mice records table according to user input.
 */


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ReadMiceTables {

    /*
    get the most recent table file's string representation of the file's path. Format is "yyyy-MM-dd hh-mm".
     */
    public static String getRecentMiceTableDir(){
        File mouseQDirFile=new File(Utilities.getTablesFolderDir());
        String[] fileDirs=mouseQDirFile.list();

        Set<String> filteredDirs=null;

        filteredDirs= Stream.of(fileDirs).filter(str -> str.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}.txt$"))
                                            .collect(Collectors.toSet());

        String[] subtractedDir =new String[filteredDirs.size()];
        Date[]   dates=new Date[filteredDirs.size()];
        int i=0;
        for(String dir : filteredDirs){

            subtractedDir[i]=dir.substring(0, 16).replace("-", "/");
            dates[i]=Utilities.translateStringToDate(subtractedDir[i]);
            i++;
        }
        Date recentDate=dates[0];
        for(int q=0; q< filteredDirs.size(); q++){
            if(recentDate.before(dates[q])){
                recentDate=dates[q];
            }
        }
        DateFormat df=new SimpleDateFormat("yyyy/MM/dd hh/mm");

        return df.format(recentDate).replace("/", "-");
    }

    /*
     mice column fields is stored in the ArrayList<String> returned by reMiceTable() call.
     */
    public static ArrayList<String> readMiceTable(String tableFileDir) throws IOException {
            ArrayList<String> arrayListOfString=new ArrayList<>();
            File tableFile=new File(tableFileDir);
            String line;
            FileReader fileReader=new FileReader(tableFile);
            try {
                BufferedReader bufferedReader=new BufferedReader(fileReader);
                while((line=bufferedReader.readLine())!=null){
                    line.trim();
                    arrayListOfString.add(line);
                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }finally {
                fileReader.close();
            }
            return arrayListOfString;
    }









}
