package com.example.mngtasks1;

import android.content.Intent;
import android.view.View;

/**
 * Created by sters on 12.02.18.
 */

public class ActivityD extends MainActivity {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, ActivityD.class));
    }
}
