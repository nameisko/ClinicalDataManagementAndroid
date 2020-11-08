package lushi.cao.s301011302.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import lushi.cao.s301011302.CaoPageViewModel;
import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;

public class LushiPlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private CaoPageViewModel pageViewModel;
    RecyclerView recylcerView;
    CaoPatientAdapter adapter;
    ArrayList<String> users;

    public static LushiPlaceholderFragment newInstance(int index) {
        LushiPlaceholderFragment fragment = new LushiPlaceholderFragment();
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
        View root;
        Context context = getContext();
        switch (getArguments().getInt(ARG_SECTION_NUMBER)){
            case 1:
                root = inflater.inflate(R.layout.patient_fragment, container, false);
                recylcerView = root.findViewById(R.id.lushiRecyclerView);
                users = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    users.add("Lushi #"+i);
                }
                recylcerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new CaoPatientAdapter(context,users);
                recylcerView.setAdapter(adapter);
//                Toast toast = Toast.makeText(getContext(), "fragment1 started: ", Toast.LENGTH_SHORT);
//                toast.show();
                break;
            default:
                root = inflater.inflate(R.layout.patient_fragment, container, false);
                break;
        }
        return root;
    }
}