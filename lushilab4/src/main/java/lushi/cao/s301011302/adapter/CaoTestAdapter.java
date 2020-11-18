package lushi.cao.s301011302.adapter;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */
import android.content.Context;
import android.content.res.Resources;
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
    Context context;

    @NonNull
    @Override
    public CaoTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list,parent,false);
        return new ViewHolder(view);
    }

    public void setContext(Context c) {
        context = c;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resources res = context.getResources();
        String covidResult = tests.get(position).getCovid()?res.getString(R.string.positive):res.getString(R.string.negative);
        holder.bp.setText(String.valueOf(tests.get(position).getBloodPressure()));
        holder.respiratory.setText(tests.get(position).getRespiratoryRate());
        holder.oxygen.setText(tests.get(position).getBloodOxygen());
        holder.heartRate.setText(tests.get(position).getHeartRate());
        holder.covid.setText(covidResult);
        holder.date.setText(tests.get(position).getDate().toString());
    }

    public void setTests(List<Test> tests){
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestAt(int index){
        return tests.get(index);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bp;
        public TextView respiratory;
        public TextView oxygen;
        public TextView heartRate;
        public TextView covid;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            bp = itemView.findViewById(R.id.lushiBPTV);
            covid = itemView.findViewById(R.id.lushiCovidTV);
            date = itemView.findViewById(R.id.lushiDateTV);
            respiratory = itemView.findViewById(R.id.lushiRespTV);
            oxygen = itemView.findViewById(R.id.lushiOxygenTV);
            heartRate = itemView.findViewById(R.id.lushiHeartTV);
        }
    }
}
