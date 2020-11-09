package lushi.cao.s301011302.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int patientID;

    @ColumnInfo(name = "doctor_id")
    private int doctorID;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "room")
    private String room;

    public Patient(){}

    public Patient(int docId, String firstName, String lastName, String room) {
        this.doctorID = docId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
    }

    public int getPatientID(){return patientID;}
    public void setPatientID(int id){patientID = id;}

    public int getDoctorID(){return doctorID;}
    public void setDoctorID(int id){doctorID = id;}

    public String getFirstName(){return firstName;}
    public void setFirstName(String first){ firstName = first;}

    public String getLastName(){return lastName;}
    public void setLastName(String last){lastName = last;}

    public String getRoom(){return room;}
    public void setRoom(String r){room = r;}

}
