package com;

import com.cagezz.CageZZ;
import com.cagezz.Gender;
import com.cagezz.Status;
import com.cagezz.Utilities;
import com.mouse.Mouse;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {
    CageZZ cageZZ=new CageZZ();
    ArrayList<Mouse> miceList=cageZZ.getMiceList();



    @Test
    void createMiceRecordTableFile() {
        Mouse mouse1=new Mouse("a12345","a23459", "c12349","2021-04-22",
                Gender.MALE.toString(), "c57");
        mouse1.setGenotype("Balc+/-");
        mouse1.setStatus(Status.MAINTAINING.toString());
        mouse1.setCageNumber("cg1234");
        mouse1.setNotes("newly added!");


        Mouse mouse2=new Mouse("a12346","a23459", "c12349","2021-04-22",
                Gender.MALE.toString(), "c57");
        mouse2.setGenotype("Balc+/-");
        mouse2.setStatus(Status.MAINTAINING.toString());
        mouse2.setCageNumber("cg1234");
        mouse2.setNotes("newly added!");

        Mouse mouse3=new Mouse("a12347","a23459", "c12349","2021-04-22",
                Gender.MALE.toString(), "c57");
        mouse3.setGenotype("Balc+/-");
        mouse3.setStatus(Status.MAINTAINING.toString());
        mouse3.setCageNumber("cg1234");
        mouse3.setNotes("newly added!");


        miceList.add(mouse1);
        miceList.add(mouse2);
        miceList.add(mouse3);
        assertTrue(Utilities.createMouseQHomeFolder());
        assertTrue(Utilities.createMouseQTablesFolder());
        assertTrue(Utilities.createMiceRecordTableFile(miceList));



    }

    @Test
    void translateStringToDate() {
        Date date=Utilities.translateStringToDate("2021/05/19 03/08");
        System.out.println(date);
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH/mm");
        String dateString=dateFormat.format(date);
        assertTrue(dateString.equals("2021/05/19 03/08"));
    }

}