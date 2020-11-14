package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class ViewInformationFragment extends Fragment {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_cao_patient, container, false);
        Context context = getActivity().getApplicationContext();
        addPatientFab = (FloatingActionButton) root.findViewById(R.id.lushiAddPatientFab);
        addTestFab = (FloatingActionButton) root.findViewById(R.id.lushiAddTestFab);
        addPatientFab = root.findViewById(R.id.lushiAddPatientFab);
        addTestFab = root.findViewById(R.id.lushiAddTestFab);

        mainFab = root.findViewById(R.id.lushiMainFab);
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        recylcerView = root.findViewById(R.id.lushiRecyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CaoPatientAdapter();
        recylcerView.setAdapter(adapter);
        adapter.setContext(context);
        adapter.setActivity(getActivity());
        fragmentManager = this.getActivity().getSupportFragmentManager();
        adapter.setFm(fragmentManager);
        patientViewModel = ViewModelProviders.of(getActivity()).get(PatientViewModel.class);
                patientViewModel.getAllPatients().observe(getActivity(), new Observer<List<Patient>>() {
                    @Override
                    public void onChanged(List<Patient> patients) {
                        //update recycler view
                        adapter.setPatients(patients);
                    }
                });
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
//                Intent intent = new Intent(getApplicationContext(), CaoAddTest.class);
//                startActivity(intent);
            }
        });

        addPatientFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFab.collapse();
                navController.navigate(R.id.action_viewInformationFragment_to_addPatientFragment);
//                Intent intent = new Intent(getApplicationContext(), CaoAddPatient.class);
//                startActivity(intent);
            }
        });
    }
}