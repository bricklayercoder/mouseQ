package com.cagezz;

import com.mouse.DataSanity;
import com.mouse.Mouse;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CageZZ {

    private String  institute;
    private String  PI_Group;
    private String  groupMemberName;
    private long    numberOfCageZZ=0;
    HashMap<String, Mouse> miceRecords=new HashMap<>();
    ArrayList<Mouse> miceList=new ArrayList<>();
    String[][] arrayRepresentationOfMiceData;


    HashMap<String, Cage> cagezzHoused =new HashMap<>();
    String [][] arrayRepresentationCages;

    HashMap<String, DataSanity> dataSanityHashMap=new HashMap<>();


    public String[][] getArrayRepresentationOfMiceData(){
        arrayRepresentationOfMiceData=new String[miceRecords.size()][12];
        int row=0;
        for (Map.Entry<String, Mouse> entry : miceRecords.entrySet()){
            arrayRepresentationOfMiceData[row][0]=entry.getValue().getTagNumber();
            arrayRepresentationOfMiceData[row][1]=entry.getValue().getGender();
            arrayRepresentationOfMiceData[row][2]=entry.getValue().getBirthDate();
            arrayRepresentationOfMiceData[row][3]=entry.getValue().getStrain();
            arrayRepresentationOfMiceData[row][4]=entry.getValue().getPaterNalTagNumber();
            arrayRepresentationOfMiceData[row][5]=entry.getValue().getMaternalTagNumber();
            arrayRepresentationOfMiceData[row][6]=entry.getValue().getCoatColour();
            arrayRepresentationOfMiceData[row][7]=entry.getValue().getWeanDate();
            arrayRepresentationOfMiceData[row][8]=entry.getValue().getGenotype();
            arrayRepresentationOfMiceData[row][9]=entry.getValue().getStatus();
            arrayRepresentationOfMiceData[row][10]=entry.getValue().getCageNumber();
            arrayRepresentationOfMiceData[row][11]=entry.getValue().getNotes();
            row++;
        }
        return arrayRepresentationOfMiceData;
    }

    public String[][] getArrayRepresentationCages(){

        arrayRepresentationCages =new String[cagezzHoused.size()][5];
        int row=0;
        int column=0;
        System.out.println("inside getArrayRepresentation: "+ cagezzHoused.size());
        for(Map.Entry<String, Cage> entry: cagezzHoused.entrySet()){

            arrayRepresentationCages[row][0]=entry.getValue().getCageNumber();
            arrayRepresentationCages[row][1]=entry.getValue().getStrain();
            arrayRepresentationCages[row][2]=entry.getValue().getStatus();
            arrayRepresentationCages[row][3]= String.valueOf(entry.getValue().getMiceInfoContainer().size());
            arrayRepresentationCages[row][4]=entry.getValue().getNotes();
            row++;

        }
        return arrayRepresentationCages;
    }


    public ArrayList<Mouse> getMiceList(){
        for(Map.Entry<String, Mouse> entry : miceRecords.entrySet()){
            Mouse mouse= entry.getValue();
            miceList.add(mouse);
        }
        return miceList;

    }


    public HashMap<String, Cage> getCageZZ(){
 //       loadMiceRecords();
        loadCageRecords();
        return cagezzHoused;
    }


    private void loadCageRecords(){
        /*
        miceRecords is a HashMap of <String tagNumber, Mouse this Mouse>
         */
        for(Map.Entry<String, Mouse> entry : miceRecords.entrySet()){
            Mouse thisMouse=entry.getValue();
            String cageNumber=thisMouse.getCageNumber();
                    /*
                    If the current cages housed does not include this cage of "cageNumber", then add a new cage record.
                     */
                    if(!cagezzHoused.containsKey(cageNumber)) {
                        Cage cage=new Cage();
                        cage.setCageNumber(cageNumber);
                        cage.setStatus(thisMouse.getStatus());
                        cage.getMiceInfoContainer().add(thisMouse);
                        cage.setStrain(thisMouse.getStrain());
                        cage.setNotes(thisMouse.getNotes());
                        cagezzHoused.put(cageNumber, cage);
                    } else {
                        Cage cage=cagezzHoused.get(cageNumber);
                        cage.getMiceInfoContainer().add(thisMouse);

                        cage.setStatus((thisMouse.getStatus()));
                        cage.setStrain(thisMouse.getStrain());
                        cage.setNotes(thisMouse.getNotes());
                    }
        }
    }

    /*
    Load the most recent mice table .txt file and populate the miceRecords HashMap.
    This process can be thought of as constructing virtual mouse records.
     */

    public HashMap<String, Mouse> getMiceRecords(){
 //       loadMiceRecords();
        return miceRecords;
    }
    public void loadMiceRecords(){
        File mouseQDirFile=new File(Utilities.getTablesFolderDir());
        String[] fileDirs=mouseQDirFile.list();
        if(fileDirs==null) {
            return;
        }


        String recentMiceTableDir=Utilities.getTablesFolderDir()+ File.separator + ReadMiceTables.getRecentMiceTableDir()+".txt";
        System.out.println("Inside loadMiceRecord: "+ recentMiceTableDir);
        ArrayList<String> mouseList=null;
        try {
            mouseList=ReadMiceTables.readMiceTable(recentMiceTableDir);
            System.out.println("Inside loadMiceRecord try catch block.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        use the mouse tag-number as key, the 9 column fields as values represented by the string array values.
         */
        String key="";

        for (String str : mouseList){
            String[] fieldsPerLine=str.split(",");
            for(int i=0; i<fieldsPerLine.length; i++) {
                fieldsPerLine[i]=fieldsPerLine[i].trim();
            }
            if( !fieldsPerLine[0].equals("TagNumber") ){
                Mouse mouse=new Mouse(fieldsPerLine[0],
                                      fieldsPerLine[5],
                                      fieldsPerLine[4],
                                      fieldsPerLine[2],
                                      fieldsPerLine[1],
                                      fieldsPerLine[3]);

                mouse.setCoatColour(fieldsPerLine[6]);
                mouse.setWeanDate(fieldsPerLine[7]);
                mouse.setNotes(fieldsPerLine[11]);
                mouse.setCageNumber(fieldsPerLine[10]);
                mouse.setGenotype(fieldsPerLine[8]);
                mouse.setStatus(fieldsPerLine[9]);

                key=fieldsPerLine[0];

                miceRecords.put(key,mouse);
            }

        }
    }

    public boolean addNewMouseRecord(String tagNumber,
                                     String maternalTagNumber,
                                     String paternalTagNumber,
                                     String birthDate,
                                     String gender,
                                     String strain){
        /*
        First check whether tagNumber already exists in the HashMap, miceRecords, if it does,
        then should switch to updating mouse record operation.
         */
        boolean added=false;
        boolean alreadyExist=false;
        for(Map.Entry<String, Mouse>  entry: miceRecords.entrySet()){
            if (entry.getKey().equals(tagNumber)) alreadyExist=true;
        }

        if (alreadyExist==false){
            Mouse mouse=new Mouse(tagNumber, maternalTagNumber, paternalTagNumber, birthDate, gender, strain);
            miceRecords.put(tagNumber, mouse);
            added=true;
        }

        return added;
    }

    /*use the cagesHoused HashMap to
    check dataSanity to see if mice records are properly kept.
     */
    private boolean isCageSanityDataAlreadyInHashMap(String cageNumber, DataSanity dataSanity){
        boolean isAlreadyInHashmap=false;
        DataSanity dataSanityInTheHashMap=dataSanityHashMap.get(cageNumber);

        if ( dataSanity.toString().equals(dataSanityInTheHashMap.toString())){
            isAlreadyInHashmap=true;
        }

        return isAlreadyInHashmap;

    }


    public HashMap<String, DataSanity> getDataSanityHashMap(){
        Cage thisCage;
        ArrayList<Mouse> miceInThisCage;
        DataSanity thisCagesDataSanity;
        System.out.println(cagezzHoused);

        for(Map.Entry<String, Cage> entry : cagezzHoused.entrySet()){
            thisCage=entry.getValue();
            miceInThisCage=thisCage.getMiceInfoContainer();


            if(miceInThisCage.size() > 5) {
                thisCagesDataSanity=DataSanity.OVER_SIZED;
                if(! isCageSanityDataAlreadyInHashMap(thisCage.getCageNumber(), thisCagesDataSanity)){
                dataSanityHashMap.put(thisCage.getCageNumber(), DataSanity.OVER_SIZED);
                }
            }

            for(Mouse mouse : miceInThisCage) {

                if(!Objects.equals(thisCage.getStatus(), mouse.getStatus())){
                    thisCagesDataSanity=DataSanity.INCOMPATIBLE_BREED_STATUS;
                    if (!isCageSanityDataAlreadyInHashMap(thisCage.getCageNumber(), thisCagesDataSanity)){
                    dataSanityHashMap.put(entry.getKey(), DataSanity.INCOMPATIBLE_BREED_STATUS);
                    }
                    break;
                }
            }
        }

        return dataSanityHashMap;
    }



    public long getNumberOfCageZZ() {
        numberOfCageZZ= cagezzHoused.size();
        return numberOfCageZZ;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPI_Group() {
        return PI_Group;
    }

    public void setPI_Group(String PI_Group) {
        this.PI_Group = PI_Group;
    }

    public String getGroupMemberName() {
        return groupMemberName;
    }

    public void setGroupMemberName(String groupMemberName) {
        this.groupMemberName = groupMemberName;
    }


}
