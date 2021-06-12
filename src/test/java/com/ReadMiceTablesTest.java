package com;

import com.cagezz.ReadMiceTables;
import com.cagezz.Utilities;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class ReadMiceTablesTest {



    @Test
    void testReadMiceTable() {
        String recentTableDir= Utilities.getTablesFolderDir()+File.separator+ ReadMiceTables.getRecentMiceTableDir()+".txt";

        ArrayList<String> arrayList=new ArrayList<>();
        try {
            arrayList=ReadMiceTables.readMiceTable(recentTableDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line1=arrayList.get(1); String line2=arrayList.get(2); String line3=arrayList.get(3);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);


        String[] fields1=line1.split(",");       String[] fields2=line2.split(",");

        String[] flds3=line3.split(",");

        int i1, i2;
        for(i1=0; i1<fields1.length-1; i1++){
            fields1[i1]=fields1[i1].trim();
            System.out.println("field"+ i1 +"is: "+fields1[i1]+" length is: "+fields1[i1].length());
        }
        System.out.println("i1 is: "+i1);


        for(i2=0; i2<fields2.length; i2++){
              fields2[i2]=fields2[i2].trim();
        }

        for(int i=0; i<flds3.length; i++){
            flds3[i] =flds3[i].trim();
        }


//        assertTrue("FEMALE".equals(fields1[1]));

        assertTrue("newly added!".equals(fields2[11]));
        assertTrue("newly added!".equals(flds3[11]));
//        assertTrue("a12347".equals(flds3[0]));

    }


    @Test
    void getRecentMiceTableDir() {
        String recentDir=ReadMiceTables.getRecentMiceTableDir();
        assertEquals(recentDir, "2021-05-23 01-01");
    }
}