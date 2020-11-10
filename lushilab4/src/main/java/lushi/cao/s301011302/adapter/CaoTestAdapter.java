package lushi.cao.s301011302.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

public class CaoTestAdapter extends RecyclerView.Adapter<CaoTestAdapter.ViewHolder> {
    private List<Test> tests =  new ArrayList<>();
    private Patient patient;

    @NonNull
    @Override
    public CaoTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String covidResult = tests.get(position).getCovid()?"postive":"negative";

        holder.patientID.setText(String.valueOf(tests.get(position).getPatientID()));
        holder.temperature.setText(tests.get(position).getTemperature());
        holder.bp.setText(String.valueOf(tests.get(position).getBloodPressure()));
        holder.covid.setText(covidResult);
        holder.date.setText(tests.get(position).getDate());
    }

    public void setTests(List<Test> tests){
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestAt(int index){
        return tests.get(index);
    }

    public void setTestsAndPatient(List<Test> tests, Patient p){
        this.tests = tests;
        patient = p;
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bp;
        public TextView temperature;
        public TextView covid;
        public TextView date;
        public TextView patientID;

        public ViewHolder(View itemView) {
            super(itemView);
            bp = itemView.findViewById(R.id.lushiBPTV);
            temperature = itemView.findViewById(R.id.lushiTemperatureTV);
            covid = itemView.findViewById(R.id.lushiCovidTV);
            date = itemView.findViewById(R.id.lushiDateTV);
            patientID = itemView.findViewById(R.id.lushiPatientIDTV);
        }
    }
}
