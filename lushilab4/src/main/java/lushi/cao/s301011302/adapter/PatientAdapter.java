package lushi.cao.s301011302.adapter;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.model.Patient;


public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private List<Patient> patients = new ArrayList<>();
    Patient patient;
    Context context;
    Activity activity;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    FragmentManager fm;
    NavController navController;
    View view;

    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list, parent, false);
        return new ViewHolder(view);
    }

    public void setContext(Context c) {
        context = c;
    }

    public void setActivity(Activity a) {
        activity = a;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public void setView(View v) {
        view = v;
    }

    @Override
    public void onBindViewHolder(PatientAdapter.ViewHolder holder, int position) {
        Resources res = context.getResources();
        String nameStr = patients.get(position).getFirstName() + " " + patients.get(position).getLastName();
        String patientIdStr = res.getString(R.string.patient_id) + String.valueOf(patients.get(position).getPatientID());
        String roomStr = res.getString(R.string.room) + patients.get(position).getRoom();
        String ageStr = res.getString(R.string.age) + patients.get(position).getAge();
        String genderStr = res.getString(R.string.gender) + patients.get(position).getGender();
        holder.fullName.setText(nameStr);
        holder.patientId.setText(patientIdStr);
        holder.room.setText(roomStr);
        holder.dept.setText(patients.get(position).getDepartment());
        holder.age.setText(ageStr);
        holder.gender.setText(genderStr);
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(10);
        switch (patients.get(position).getDepartment()) {
            case "Blood Lab":
                gd.setColor(ContextCompat.getColor(context, R.color.light_red));
                break;
            case "Allergy":
                gd.setColor(ContextCompat.getColor(context, R.color.light_blue));
                break;
            case "Nerosurgery":
                gd.setColor(ContextCompat.getColor(context, R.color.light_green));
                break;
            case "Orthopedic":
                gd.setColor(ContextCompat.getColor(context, R.color.light_brown));
        }
        holder.dept.setBackground(gd);
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    sharedPrefEditor.putInt("patientId", patients.get(position).getPatientID());
                    sharedPrefEditor.apply();
                    navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_viewInformationFragment_to_testListFragment);
//                FragmentTransaction fragmentTransaction= fm.beginTransaction();
//                fragmentTransaction.replace(R.id.action_viewInformationFragment_to_testListFragment,fg)
//                        .addToBackStack(null)
//                        .commit();
                } catch (Exception ex) {
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fullName;
        public TextView patientId;
        public TextView room;
        public TextView dept;
        public TextView age;
        public TextView gender;

        public ViewHolder(View itemView) {
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