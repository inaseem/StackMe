package ali.naseem.stackme;

import android.app.Application;
import android.content.Context;

public class StackMe extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Utils.initialize(base);
    }
}
