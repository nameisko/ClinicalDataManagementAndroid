package lushi.cao.s301011302.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;

public class LushiPatientFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    CaoPatientAdapter adapter;
    RecyclerView recylcerView;

    public static LushiPatientFragment newInstance(int index) {
        LushiPatientFragment fragment = new LushiPatientFragment();
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
        View root = inflater.inflate(R.layout.patient_fragment, container, false);
        
        return root;
    }
}