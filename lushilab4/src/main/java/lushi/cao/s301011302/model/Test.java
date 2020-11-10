package lushi.cao.s301011302.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "test", foreignKeys = {@ForeignKey(entity=Patient.class,
                parentColumns ="patient_id", childColumns = "patient_id")})
public class Test {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "test_id")
    private int testID;

    @ColumnInfo(name = "patient_id")
    private int patientID;

    @ColumnInfo(name = "blood_pressure")
    private int bloodPressure;

    @ColumnInfo(name = "temperature")
    private String temperature;

    @ColumnInfo(name = "covid")
    private boolean covid;

    @ColumnInfo(name = "date")
    private String date;

    public Test(){}

    public Test(int pid, int bp, String temp, boolean covid, String date){
        patientID = pid;
        bloodPressure = bp;
        temperature = temp;
        this.covid = covid;
        this.date = date;
    }
    public int getTestID(){return testID;}
    public int getPatientID(){return patientID;}
    public int getBloodPressure(){return bloodPressure;}
    public String getTemperature(){return temperature;}
    public boolean getCovid(){return covid;}
    public String getDate(){return date;}

    public void setTestID(int id){testID = id;}
    public void setPatientID(int id){patientID = id;}
    public void setBloodPressure(int bp){bloodPressure = bp;}
    public void setTemperature(String temp){temperature = temp;}
    public void setCovid(boolean c){covid = c;}
    public void setDate(String date){this.date = date;}

}
