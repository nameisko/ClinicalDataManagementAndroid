package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.PatientAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class LushiFragmentMyPatients extends Fragment {
    SharedPreferences sharedPref;
    RecyclerView recylcerView;
    PatientAdapter adapter;
    PatientViewModel patientViewModel;
    FragmentManager fragmentManager;
    String department;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getActivity().getApplicationContext();
        View root = inflater.inflate(R.layout.fragment_view_information, container, false);
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        department = sharedPref.getString("department", null);
        recylcerView = root.findViewById(R.id.lushiRecyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PatientAdapter();
        recylcerView.setAdapter(adapter);
        adapter.setContext(context);
        adapter.setActivity(getActivity());
        fragmentManager = this.getActivity().getSupportFragmentManager();
        adapter.setFm(fragmentManager);
        adapter.setView(root);
        patientViewModel = ViewModelProviders.of(getActivity()).get(PatientViewModel.class);

        patientViewModel.getMyPatients().observe(getActivity(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update recycler view
                adapter.setPatients(patients);
            }
        });
        return root;
    }
}