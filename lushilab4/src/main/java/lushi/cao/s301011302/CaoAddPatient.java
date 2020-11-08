package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CaoAddPatient extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_add_patient);

        firstName = findViewById(R.id.lushiPatientFirstET);
        lastName = findViewById(R.id.lushiPatientLastET);
        button = findViewById(R.id.lushiAddPatientBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "FIRST: " + firstName.getText(), Toast.LENGTH_SHORT);
            toast.show();
            }
        });
    }
}