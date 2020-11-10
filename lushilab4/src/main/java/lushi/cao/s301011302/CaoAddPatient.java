package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.fragment.LushiPlaceholderFragment;
import lushi.cao.s301011302.model.Patient;

public class CaoAddPatient extends AppCompatActivity {
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharePrefEdit;
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String ROOM = "room";
    PatientViewModel patientViewModel;
    AppDatabase db;
    Context context;
    EditText firstName;
    EditText lastName;
    EditText roomNum;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_add_patient);
        sharedPref = getSharedPreferences("patientInfo", MODE_PRIVATE);
        context = getApplicationContext();
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        button = findViewById(R.id.lushiAddPatientBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = findViewById(R.id.lushiPatientFirstET);
                lastName = findViewById(R.id.lushiPatientLastET);
                roomNum = findViewById(R.id.lushiPatientRoomET);
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String room = roomNum.getText().toString();
                Intent data = new Intent();
                data.putExtra("first",first);
                data.putExtra("last",last);
                data.putExtra("room",room);
                setResult(RESULT_OK, data);
                patientViewModel.insert(new Patient(1, first, last, room));
                Toast.makeText(getApplicationContext(),"inserted " + first,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}