package com.cagezz;

import com.mouse.DataSanity;
import com.mouse.Mouse;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
            arrayRepresentationOfMiceData[row][4]=entry.getValue().getPaternalTagNumber();
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

    public HashMap<String, Cage> getCageZZ(ArrayList<Mouse> miceArrayList){
        HashMap<String, Cage> cagesHashMap=new HashMap<>();
        for (Mouse mouse : miceArrayList){
            if(! cagesHashMap.containsKey(mouse.getCageNumber())){
                Cage cage=new Cage();
                cage.setCageNumber(mouse.getCageNumber());
                cage.setStatus(mouse.getStatus());
                cage.getMiceInfoContainer().add(mouse);
                cage.setStrain(mouse.getStrain());
                cage.setNotes(mouse.getNotes());
                cagesHashMap.put(mouse.getCageNumber(), cage);
            } else {

                Cage cage=cagesHashMap.get(mouse.getCageNumber());
                cage.getMiceInfoContainer().add(mouse);
                cage.setStatus((mouse.getStatus()));
                cage.setStrain(mouse.getStrain());
                cage.setNotes(mouse.getNotes());
            }
        }
        return cagesHashMap;

    }

    public HashMap<String, DataSanity[]> getCageDataSanities(HashMap<String, Cage> cagezMap){
        HashMap<String, DataSanity[]> dataSanitiesHashMap=new HashMap<>();
        for (Map.Entry<String, Cage> mapEntry : cagezMap.entrySet()){
            DataSanity[] dataSanities=new DataSanity[2];
            if (mapEntry.getValue().getMiceInfoContainer().size() > 5){
                DataSanity overSizedDataSanity=DataSanity.OVER_SIZED;
                dataSanities[0]=overSizedDataSanity;
            }
            for (Mouse mouse : mapEntry.getValue().getMiceInfoContainer()){
                if (!mapEntry.getValue().getStatus().equals(mouse.getStatus())){
                    DataSanity incompatibleBreedsStatus=DataSanity.INCOMPATIBLE_BREED_STATUS;
                    dataSanities[1]=incompatibleBreedsStatus;
                    break;
                }
            }
            dataSanitiesHashMap.put(mapEntry.getKey(), dataSanities);

        }
        return dataSanitiesHashMap;

    }


    private void loadCageRecords(){

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
        return miceRecords;
    }


    public void loadMiceRecords(){
        File mouseQDirFile=new File(Utilities.getTablesFolderDir());
        String[] fileDirs=mouseQDirFile.list();

        if(fileDirs==null) {
            return;
        }


        String recentMiceTableDir=Utilities.getTablesFolderDir()+ File.separator + ReadMiceTables.getRecentMiceTableDir()+".txt";
        ArrayList<String> mouseList=null;
        try {
            mouseList=ReadMiceTables.readMiceTable(recentMiceTableDir);
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
