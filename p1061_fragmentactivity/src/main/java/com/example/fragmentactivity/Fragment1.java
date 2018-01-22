package com.example.fragmentactivity;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    final String LOG_TAG = "myLogs";

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment1, container, false);
        Button button = fragment.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "Button click in Fragment1");

                Activity mainActivity = getActivity();
                Button findBtn = mainActivity.findViewById(R.id.btnFind);
                findBtn.setText("Access from Fragment1");
            }
        });

        return fragment;
    }

}
