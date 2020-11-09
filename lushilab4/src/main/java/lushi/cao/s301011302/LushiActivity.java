package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lushi.cao.s301011302.fragment.LushiPlaceholderFragment;

public class LushiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent();
            }
        });
    }

    public void callIntent(){
        Intent intent;
        intent = new Intent(this, CaoActivityViewInformation.class);
        startActivity(intent);
    }
}