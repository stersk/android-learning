package com.example.multiplescreen;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements
        TitlesFragment.onItemClickListener {

    private int position = 0;
    private boolean withDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            position = savedInstanceState.getInt("position");

        withDetails = (findViewById(R.id.cont) != null);
        if (withDetails) {
            showDetails(position);
        }
    }

    private void showDetails(int pos) {
        if (withDetails) {
            DetailsFragment details = (DetailsFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.cont);
            if (details == null || details.getPosition() != pos) {
                details = DetailsFragment.newInstance(pos);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.cont, details).commit();
            }
        } else {
            startActivity(new Intent(this, DetailsActivity.class).putExtra("position", position));
        }
    }

    @Override
    public void itemClick(int position) {
        this.position = position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }
}
