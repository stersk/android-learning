package com.example.listwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by sters on 06.03.18.
 */

public class MyRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyFactory(getApplicationContext(), intent);
    }
}
