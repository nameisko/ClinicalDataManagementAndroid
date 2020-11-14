package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.getbase.floatingactionbutton.*;
import java.util.List;

import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.adapter.CaoTabAdapter;
import lushi.cao.s301011302.model.Patient;

public class CaoActivityViewInformation extends AppCompatActivity {
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionsMenu mainFab;
    boolean isFABOpen;
    CaoPatientAdapter adapter;
    Patient patient;
    Button searchBtn;
    TextView hihi;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_view_information);
        addPatientFab = (FloatingActionButton) findViewById(R.id.lushiAddPatientFab);
        addTestFab = (FloatingActionButton) findViewById(R.id.lushiAddTestFab);
        CaoTabAdapter tabAdapter = new CaoTabAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        layout = findViewById(R.id.viewInfoLayout);
        addPatientFab = findViewById(R.id.lushiAddPatientFab);
        addTestFab = findViewById(R.id.lushiAddTestFab);
        mainFab = findViewById(R.id.lushiMainFab);
        addTestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFab.collapse();
                Intent intent = new Intent(getApplicationContext(), CaoAddTest.class);
                startActivity(intent);
            }
        });

        addPatientFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFab.collapse();
//                Intent intent = new Intent(getApplicationContext(), CaoAddPatient.class);
//                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(getApplicationContext(), CaoActivitySearch.class);
                startActivity(intent);
                //Do Whatever you want to do here.
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (mainFab.isExpanded()) {
                Rect outRect = new Rect();
                mainFab.getGlobalVisibleRect(outRect);
                if(!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    mainFab.collapse();
                    return false;
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

//    private void showFABMenu() {
//        isFABOpen = true;
//        mainFab.setAlpha(0.50f);
//        addTestFab.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
//        addPatientFab.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
//    }
//
//    private void closeFABMenu() {
//        isFABOpen = false;
//        mainFab.setAlpha(1f);
//        addTestFab.animate().translationY(0);
//        addPatientFab.animate().translationY(0);
//    }
}