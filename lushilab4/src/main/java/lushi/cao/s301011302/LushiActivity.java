package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import lushi.cao.s301011302.fragment.LushiFragmentSearch;

import static androidx.navigation.ui.NavigationUI.onNavDestinationSelected;

public class LushiActivity extends AppCompatActivity {
    FragmentManager fm;
    Fragment currentFragment;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentFragment = getSupportFragmentManager().findFragmentById(R.id.lushi_nav_host_fragment);
        navController = Navigation.findNavController(this, R.id.lushi_nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
        fm = getSupportFragmentManager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the tools bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.searchFragment:
                return onNavDestinationSelected(item, navController);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exitApp();
    }

    public void exitApp() {
        DialogInterface.OnClickListener dlgListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        // This above line close correctly
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String exitMsg = "Are you sure you want to exit the app?";
        builder.setMessage(exitMsg)
                .setIcon(R.drawable.exitapp)
                .setTitle("Exit")
                .setPositiveButton("Yes", dlgListener)
                .setNegativeButton("No", dlgListener).show();
    }

}