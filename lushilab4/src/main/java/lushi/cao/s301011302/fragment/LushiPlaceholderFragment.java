package lushi.cao.s301011302.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.CaoAddPatient;
import lushi.cao.s301011302.PatientViewModel;
import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.data.PatientDao;
import lushi.cao.s301011302.model.Patient;

import static android.app.Activity.RESULT_OK;

public class LushiPlaceholderFragment extends Fragment {

    SharedPreferences sharedPref;
    private static final String ARG_SECTION_NUMBER = "section_number";
    RecyclerView recylcerView;
    CaoPatientAdapter adapter;
    PatientViewModel patientViewModel;

    public static LushiPlaceholderFragment newInstance(int index) {
        LushiPlaceholderFragment fragment = new LushiPlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        View root = inflater.inflate(R.layout.patient_fragment, container, false);
        recylcerView = root.findViewById(R.id.lushiRecyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CaoPatientAdapter();
        recylcerView.setAdapter(adapter);
        patientViewModel = ViewModelProviders.of(getActivity()).get(PatientViewModel.class);
        switch (getArguments().getInt(ARG_SECTION_NUMBER)){
            case 1:
                patientViewModel.getMyPatients().observe(getActivity(), new Observer<List<Patient>>() {
                    @Override
                    public void onChanged(List<Patient> patients) {
                        //update recycler view
                        adapter.setPatients(patients);
                    }
                });
                break;
            default:
                patientViewModel.getAllPatients().observe(getActivity(), new Observer<List<Patient>>() {
                    @Override
                    public void onChanged(List<Patient> patients) {
                        //update recycler view
                        adapter.setPatients(patients);
                    }
                });
                break;
        }
        return root;
    }
}