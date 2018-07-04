package com.example.p1831_constrainsset;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.container)
    ConstraintLayout constraintLayout;

    @BindDimen(R.dimen.some_margin)
    int someMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void onButtonClick() {
        ConstraintSet set = new ConstraintSet();

        // copy constraints settings from current ConstraintLayout to set
        set.clone(constraintLayout);

        // change constraints settings
        changeConstraints(set);

        // enable animation
        TransitionManager.beginDelayedTransition(constraintLayout);

        // apply constraints settings from set to current ConstraintLayout
        set.applyTo(constraintLayout);
    }

    // for R.layout.activity_main
//    private void changeConstraints(ConstraintSet set) {
//        set.clear(R.id.textView3, ConstraintSet.LEFT);
//        set.clear(R.id.textView3, ConstraintSet.TOP);
//
//        set.connect(R.id.textView3, ConstraintSet.BOTTOM, R.id.textView2, ConstraintSet.TOP, someMargin);
//        set.connect(R.id.textView3, ConstraintSet.LEFT, R.id.textView2, ConstraintSet.LEFT);
//    }

    // for R.layout.activity_main2
//    private void changeConstraints(ConstraintSet set) {
////        1.
////       set.setMargin(R.id.button2, ConstraintSet.START, 0);
////       set.connect(R.id.button2, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
////       set.constrainWidth(R.id.button2, ConstraintSet.MATCH_CONSTRAINT);
//
////        2.
//         set.setMargin(R.id.button2, ConstraintSet.START, 0);
//         set.connect(R.id.button2, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
//         set.setHorizontalBias(R.id.button2, 0.7f);
//    }

//    // for R.layout.activity_main3
//    private void changeConstraints(ConstraintSet set) {
//        set.setMargin(R.id.button2, ConstraintSet.START, 0);
//        set.setMargin(R.id.button3, ConstraintSet.START, 0);
//        set.constrainWidth(R.id.button2, ConstraintSet.MATCH_CONSTRAINT);
//        set.constrainWidth(R.id.button3, ConstraintSet.MATCH_CONSTRAINT);
//        int[] chainViews = {R.id.button2, R.id.button3};
//        float[] chainWeights = {3, 2};
//        set.createHorizontalChain(ConstraintSet.PARENT_ID, ConstraintSet.LEFT,
//                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT,
//                chainViews, chainWeights,
//                ConstraintSet.CHAIN_SPREAD);
//    }

    // for R.layout.activity_main4
    private void changeConstraints(ConstraintSet set) {
        set.create(R.id.guideline_1, ConstraintSet.VERTICAL_GUIDELINE);
        set.setGuidelinePercent(R.id.guideline_1, 0.2f);

        set.connect(R.id.textView, ConstraintSet.LEFT, R.id.guideline_1, ConstraintSet.RIGHT, 10);
        set.connect(R.id.textView2, ConstraintSet.LEFT, R.id.guideline_1, ConstraintSet.RIGHT, 10);
        set.connect(R.id.textView3, ConstraintSet.LEFT, R.id.guideline_1, ConstraintSet.RIGHT, 10);

        set.setMargin(R.id.textView, ConstraintSet.START, 0);
        set.setMargin(R.id.textView2, ConstraintSet.START, 0);
        set.setMargin(R.id.textView3, ConstraintSet.START, 0);
    }
}
