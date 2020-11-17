package lushi.cao.s301011302.model;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "patient_id")
    private int patientID;

    @ColumnInfo(name = "doctor_id")
    private int doctorID;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "room")
    private String room;

    @ColumnInfo(name = "age")
    private String age;

    @ColumnInfo(name = "gender")
    private String gender;

    public Patient(){}

    public Patient(int docId, String first, String last, String r, String dept, String g, String a) {
        this.doctorID = docId;
        firstName = first;
        lastName = last;
        room = r;
        gender = g;
        age = a;
        department = dept;

    }

    public Patient(int pId, int docId, String firstName, String lastName, String room, String dept) {
        this.patientID = pId;
        this.doctorID = docId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
        department = dept;
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

    public String getDepartment(){return department;}
    public void setDepartment(String dept){department = dept;}

    public String getAge(){return age;}
    public void setAge(String a){age = a;}

    public String getGender(){return gender;}
    public void setGender(String g){gender = g;}
}
