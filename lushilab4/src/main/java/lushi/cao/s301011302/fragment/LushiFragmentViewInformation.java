package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.tabs.TabLayout;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.adapter.SectionsPagerAdapter;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class LushiFragmentViewInformation extends Fragment {
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView recylcerView;
    CaoPatientAdapter adapter;
    PatientViewModel patientViewModel;
    boolean newPatientSubmitted;
    CoordinatorLayout layout;
    FragmentManager fragmentManager;
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionsMenu mainFab;
    NavController navController;
    String department;
    SectionsPagerAdapter tabAdapter;
    ViewPager viewPager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_patient_list, container, false);
        Context context = getActivity().getApplicationContext();
        tabAdapter = new SectionsPagerAdapter(context, getChildFragmentManager());
        viewPager= root.findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        addPatientFab = (FloatingActionButton) root.findViewById(R.id.lushiAddPatientFab);
        addTestFab = (FloatingActionButton) root.findViewById(R.id.lushiAddTestFab);
        addPatientFab = root.findViewById(R.id.lushiAddPatientFab);
        addTestFab = root.findViewById(R.id.lushiAddTestFab);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainFab = root.findViewById(R.id.lushiMainFab);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        addTestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFab.collapse();
                navController.navigate(R.id.action_viewInformationFragment_to_addTestFragment);
            }
        });

        addPatientFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFab.collapse();
                navController.navigate(R.id.action_viewInformationFragment_to_addPatientFragment);
            }
        });
    }
}