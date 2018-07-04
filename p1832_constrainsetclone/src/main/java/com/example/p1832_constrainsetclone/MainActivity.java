package com.example.p1832_constrainsetclone;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final ConstraintSet set = new ConstraintSet();
    private final ConstraintSet set2 = new ConstraintSet();
    private ConstraintLayout constraintLayout;
    private ConstraintSet currentSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout) findViewById(R.id.container);

        // copy constraints settings from current ConstraintLayout to set
        set.clone(constraintLayout);

        // copy constraints settings from activity_main2 to set2
        set2.clone(MainActivity.this, R.layout.activity_sample);

        currentSet = set;

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // change current set
                currentSet = (currentSet == set ? set2 : set);

                // enable animation
                TransitionManager.beginDelayedTransition(constraintLayout);

                // apply settings to current ConstraintLayout
                currentSet.applyTo(constraintLayout);
            }
        });

    }
}
