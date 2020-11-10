package lushi.cao.s301011302.adapter;

import android.content.Context;
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
import lushi.cao.s301011302.model.Patient;

public class CaoPatientAdapter extends RecyclerView.Adapter<CaoPatientAdapter.ViewHolder> {
    private List<Patient> patients =  new ArrayList<>();
    Context context;

    @Override
    public CaoPatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaoPatientAdapter.ViewHolder holder, int position){
        String name = patients.get(position).getFirstName() + " " + patients.get(position).getLastName();
        holder.fullName.setText(name);
        holder.patientId.setText(String.valueOf(patients.get(position).getPatientID()));
        holder.room.setText(patients.get(position).getRoom());
    }

    @Override
    public int getItemCount(){
        return patients.size();
    }

    public void setPatients(List<Patient> patients){
        this.patients = patients;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fullName;
        public TextView patientId;
        public TextView room;

        public ViewHolder(View itemView){
            super(itemView);
            fullName = itemView.findViewById(R.id.lushiFullNameTV);
            patientId = itemView.findViewById(R.id.lushiPatientIDTV);
            room = itemView.findViewById(R.id.lushiPatientRoomTV);
        }
    }
}
