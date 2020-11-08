package lushi.cao.s301011302.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import lushi.cao.s301011302.R;

public class CaoPatientAdapter extends RecyclerView.Adapter<CaoPatientAdapter.ViewHolder> {
    ArrayList<String> users;
    Context context;

    public CaoPatientAdapter(Context c, ArrayList<String> list){
        this.users = list;
        this.context = c;
    }

    @Override
    public CaoPatientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CaoPatientAdapter.ViewHolder holder, int position){
        holder.first.setText(users.get(position));
        holder.last.setText("hi");
    }

    @Override
    public int getItemCount(){
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView first;
        public TextView last;
        public ViewHolder(View itemView){
            super(itemView);
            first = itemView.findViewById(R.id.lushiFirstTv);
            last = itemView.findViewById(R.id.lushiLastTv);
        }
    }
}
