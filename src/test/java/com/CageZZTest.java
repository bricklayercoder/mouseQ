package com;

import com.cagezz.Cage;
import com.cagezz.CageZZ;
import com.cagezz.Utilities;
import com.mouse.DataSanity;
import com.mouse.Mouse;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CageZZTest {
    CageZZ cagezz=new CageZZ();

    @Test
    void loadMiceRecords() {
        cagezz.loadMiceRecords();
        HashMap<String, Mouse> miceRecords=cagezz.getMiceRecords();
        String tag1="a12345";
        String tag2="a12347";
        String gender="MALE";
        String notes="newly added!";
        String status="MAINTAINING";

        assertEquals(tag1, miceRecords.get(tag1).getTagNumber());
        assertEquals(tag2, miceRecords.get(tag2).getTagNumber());
        assertEquals(notes, miceRecords.get(tag1).getNotes());
        assertEquals(status, miceRecords.get(tag1).getStatus());
        assertEquals(miceRecords.get(tag1).getGender(), "MALE");
        assertEquals(miceRecords.get(tag1).getNotes(), notes);
        assertEquals(miceRecords.get(tag2).getStatus(), status);
    }

    @Test
    void getCageZZ() {
        cagezz.loadMiceRecords();
        HashMap<String, Cage> cages=cagezz.getCageZZ();
        String cageNumber="cg1234";
//        assertEquals(cages.size(), 1);
        Cage cage=cages.get(cageNumber);
        assertEquals(cage.getMiceInfoContainer().size(), 3);
        ArrayList<Mouse> miceContainInThisCage=cage.getMiceInfoContainer();
        boolean statusCorrect=true;
        int coutMouse=0;
        for(Mouse m : miceContainInThisCage){
            switch (m.getTagNumber()) {
                case "a12345" :  coutMouse++; break;
                case "a12346" :  coutMouse++; break;
                case "a12347":   coutMouse++; break;
                default: ;

            }

            if(!m.getStatus().equals("MAINTAINING")) statusCorrect=false;


        }
        String addedCageNumber="";
        for (Map.Entry<String, Cage> entry : cages.entrySet()){
            if(!entry.getValue().getCageNumber().equals("cg1234")) addedCageNumber=entry.getValue().getCageNumber();

        }
        Cage thisCage=cages.get(addedCageNumber);
        int numberOfMice=thisCage.getMiceInfoContainer().size();
        assertEquals(49, numberOfMice);



        assertTrue(coutMouse==3 && statusCorrect);
        assertEquals(addedCageNumber, "Not entered");

    }

    @Test
    void addNewMouseRecord() {
        cagezz.loadMiceRecords();

        String paternal="paternal", maternal="maternal", birthDate="2021-03-12", gender="FEMALE",  strain="c57";
        Random random=new Random();
        String tagNumber="TagNumber";
        boolean added= false;
           tagNumber=tagNumber + random.nextInt(50);
           added=cagezz.addNewMouseRecord(tagNumber,maternal,paternal,birthDate,gender, strain);
           assertTrue(added);

       boolean  physicalWriteSucess= Utilities.createMiceRecordTableFile(cagezz.getMiceList());
        assertTrue(physicalWriteSucess);
    }


    @Test
    void getCageDataSanities() {
        cagezz.loadMiceRecords();
        HashMap<String, DataSanity[]> dataSanitiesHashMap=cagezz.getCageDataSanities(cagezz.getCageZZ(cagezz.getMiceList()));
        for (Map.Entry<String, DataSanity[]> mapEntry: dataSanitiesHashMap.entrySet()){
            DataSanity[] dataSanities=mapEntry.getValue();
            System.out.println(dataSanities[0].toString());
            System.out.println(dataSanities[1].toString());

            if (mapEntry.getKey().toLowerCase().equals("new123")){
                assertEquals(DataSanity.INCOMPATIBLE_BREED_STATUS, dataSanities[1]);
            }
        }
    }
}