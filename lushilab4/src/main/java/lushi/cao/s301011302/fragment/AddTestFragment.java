package lushi.cao.s301011302.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import lushi.cao.s301011302.R;

public class AddTestFragment extends Fragment {

    NavController navController;
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionsMenu mainFab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_cao_add_test, container, false);
        //navController = Navigation.findNavController(root);
        return root;
    }
}