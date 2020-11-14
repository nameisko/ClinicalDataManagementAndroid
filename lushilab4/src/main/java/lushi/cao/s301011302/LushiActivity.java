package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import lushi.cao.s301011302.fragment.HomeFragment;
import lushi.cao.s301011302.fragment.SearchFragment;

public class LushiActivity extends AppCompatActivity {
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionsMenu mainFab;
    NavController navController;
    CoordinatorLayout layout;
    View view;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button button;
//        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callIntent();
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the tools bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.lushiMenuSearchBtn:
                startSearchFragment();
                //Do Whatever you want to do here.
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startSearchFragment(){

        Fragment fragment = null;
        fragment = new SearchFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(android.R.id.content, fragment,"searchFrag").commit();
    }

    public void callIntent(){
        Intent intent;
        intent = new Intent(this, CaoActivityViewInformation.class);
        startActivity(intent);
    }
}