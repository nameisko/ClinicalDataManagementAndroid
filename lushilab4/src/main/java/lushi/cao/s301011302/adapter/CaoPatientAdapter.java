package lushi.cao.s301011302.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.fragment.LushiPlaceholderFragment;
import lushi.cao.s301011302.fragment.LushiTestFragment;
import lushi.cao.s301011302.model.Patient;

public class CaoPatientAdapter extends RecyclerView.Adapter<CaoPatientAdapter.ViewHolder> {
    private List<Patient> patients =  new ArrayList<>();
    Patient patient;
    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;

    @Override
    public CaoPatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list,parent,false);
        return new ViewHolder(view);
    }

    public void setContext(Context c){
        context = c;
    }
    @Override
    public void onBindViewHolder(CaoPatientAdapter.ViewHolder holder, int position){
        String name = patients.get(position).getFirstName() + " " + patients.get(position).getLastName();
        holder.fullName.setText(name);
        holder.patientId.setText(String.valueOf(patients.get(position).getPatientID()));
        holder.room.setText(patients.get(position).getRoom());
        holder.dept.setText(patients.get(position).getDepartment());
        switch(patients.get(position).getDepartment()){
            case "Blood Lab":
                holder.dept.setBackgroundColor(Color.parseColor("#f7dad9"));
                break;
            case "Allergy":
                holder.dept.setBackgroundColor(Color.parseColor("#cee397"));
                break;
            case "Nerosurgery":
                holder.dept.setBackgroundColor(Color.parseColor("#d9e4dd"));
                break;
            case "Orthopedic":
                holder.dept.setBackgroundColor(Color.parseColor("#c3aed6"));
        }
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefEditor.putInt("patientId", patients.get(position).getPatientID());
                sharedPrefEditor.apply();
                Intent intent = new Intent(context, LushiTestFragment.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return patients.size();
    }

    public void setPatients(List<Patient> patients){
        this.patients = patients;
        notifyDataSetChanged();
    }

    public void setPatient(Patient patient){
        this.patient = patient;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fullName;
        public TextView patientId;
        public TextView room;
        public TextView dept;

        public ViewHolder(View itemView){
            super(itemView);
            fullName = itemView.findViewById(R.id.lushiFullNameTV);
            patientId = itemView.findViewById(R.id.lushiPatientIDTV);
            room = itemView.findViewById(R.id.lushiPatientRoomTV);
            dept = itemView.findViewById(R.id.lushiPatientDeptTV);
        }
    }
}
