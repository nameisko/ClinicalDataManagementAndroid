package lushi.cao.s301011302.model;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "test", foreignKeys = {@ForeignKey(entity=Patient.class,
                parentColumns ="patient_id", childColumns = "patient_id")})
public class Test {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "test_id")
    private int testID;

    @ColumnInfo(name = "patient_id")
    private int patientID;

    @ColumnInfo(name = "blood_pressure")
    private String bloodPressure;

    @ColumnInfo(name = "respiratory_rate")
    private String respiratoryRate;

    @ColumnInfo(name = "blood_oxygen")
    private String bloodOxygen;

    @ColumnInfo(name = "heart_rate")
    private String heartRate;

    @ColumnInfo(name = "covid")
    private boolean covid;

    @ColumnInfo(name = "date")
    private Date date;

    public Test(){}

    public Test(int pid, String bp, String rr, String bo, String hr, boolean covid, Date date){
        patientID = pid;
        bloodPressure = bp;
        respiratoryRate = rr;
        bloodOxygen = bo;
        heartRate = hr;
        this.covid = covid;
        this.date = date;
    }
    public int getTestID(){return testID;}
    public int getPatientID(){return patientID;}
    public String getBloodPressure(){return bloodPressure;}
    public String getRespiratoryRate(){return respiratoryRate;}
    public boolean getCovid(){return covid;}
    public Date getDate(){return date;}
    public String getBloodOxygen(){return bloodOxygen;}
    public String getHeartRate(){return heartRate;}

    public void setTestID(int id){testID = id;}
    public void setPatientID(int id){patientID = id;}
    public void setBloodPressure(String bp){bloodPressure = bp;}
    public void setRespiratoryRate(String rr){respiratoryRate = rr;}
    public void setCovid(boolean c){covid = c;}
    public void setDate(Date date){this.date = date;}
    public void setBloodOxygen(String bo){bloodOxygen = bo;}
    public void setHeartRate(String hr){heartRate = hr;}

}
