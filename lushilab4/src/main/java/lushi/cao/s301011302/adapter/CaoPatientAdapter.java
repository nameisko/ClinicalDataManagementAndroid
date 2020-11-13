package lushi.cao.s301011302.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.CaoTest;
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
        holder.patientId.setText("Health ID: #" + String.valueOf(patients.get(position).getPatientID()));
        holder.room.setText("Room: #" + patients.get(position).getRoom());
        holder.dept.setText(patients.get(position).getDepartment());
        holder.age.setText("Age: "+patients.get(position).getAge());
        holder.gender.setText("Gender: "+patients.get(position).getGender());
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(10);
        switch(patients.get(position).getDepartment()){
            case "Blood Lab":
                gd.setColor(Color.parseColor("#f1d1d1"));
                break;
            case "Allergy":
                gd.setColor(Color.parseColor("#bfdcae"));
                break;
            case "Nerosurgery":
                gd.setColor(Color.parseColor("#d9e4dd"));
                break;
            case "Orthopedic":
                gd.setColor(Color.parseColor("#e9e2d0"));
        }
        holder.dept.setBackground(gd);
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefEditor.putInt("patientId", patients.get(position).getPatientID());
                sharedPrefEditor.apply();
                Intent intent = new Intent(context, CaoTest.class);
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
        public TextView age;
        public TextView gender;

        public ViewHolder(View itemView){
            super(itemView);
            fullName = itemView.findViewById(R.id.lushiFullNameTV);
            patientId = itemView.findViewById(R.id.lushiPatientIDTV);
            room = itemView.findViewById(R.id.lushiPatientRoomTV);
            dept = itemView.findViewById(R.id.lushiPatientDeptTV);
            age = itemView.findViewById(R.id.lushiPatientAgeTV);
            gender = itemView.findViewById(R.id.lushiPatientGenderTV2);
        }
    }
}
