package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.PatientAdapter;
import lushi.cao.s301011302.adapter.TestAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;
import lushi.cao.s301011302.viewmodel.PatientViewModel;
import lushi.cao.s301011302.viewmodel.TestViewModel;

public class LushiFragmentTestList extends Fragment {
    SharedPreferences sharedPref;
    PatientViewModel patientViewModel;
    TestViewModel testViewModel;
    TestAdapter testAdapter;
    PatientAdapter patientAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    TextView patientInfo;
    Integer patientID;
    Patient patient;
    String info;
    LinearLayout layout;
    Test deletedRecord = null;

    public LushiFragmentTestList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_test_list, container, false);
        Context context = getActivity().getApplicationContext();
        layout = root.findViewById(R.id.lushiTestLayout);
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        patientID = sharedPref.getInt("patientId", 0);
        recyclerView = root.findViewById(R.id.lushiTestRecyclerView);
        recyclerView2 = root.findViewById(R.id.lushiTestPatientInfoRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView2.setLayoutManager(new LinearLayoutManager(context));
        testAdapter = new TestAdapter();
        patientAdapter = new PatientAdapter();
        patientAdapter.setView(root);
        patientAdapter.setContext(context);
        testAdapter.setContext(context);
        recyclerView.setAdapter(testAdapter);
        recyclerView2.setAdapter(patientAdapter);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getPatientTests(patientID).observe(getActivity(), new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                //update recycler view
                testAdapter.setTests(tests);
            }
        });

        patientViewModel.getSpecificPatient(patientID).observe(getActivity(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patient) {
                //update recycler view
                patientAdapter.setPatients(patient);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deletedRecord = testAdapter.getTestAt(viewHolder.getAdapterPosition());
                testViewModel.delete(testAdapter.getTestAt(viewHolder.getAdapterPosition()));
                //Toast.makeText(getApplicationContext(),"test deleted",Toast.LENGTH_SHORT).show();
                showSnackbar();
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(layout, R.string.record_deleted, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.parseColor("#f5b461"));
        snackbar.show();
    }
}