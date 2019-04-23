package ali.naseem.stackme;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import androidx.room.Room;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    private static final Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    private static SharedPreferences preferences;
    private static AppDatabase database;
    private static OverflowService service;
    private static RequestQueue requestQueue;
    private static Gson gson;

    public static void initialize(Context context) {
        preferences = context.getSharedPreferences("stack_me", Context.MODE_PRIVATE);
        database = Room.databaseBuilder(context,
                AppDatabase.class, "stack_me_db").allowMainThreadQueries().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(OverflowService.class);
        requestQueue = Volley.newRequestQueue(context);
        gson = new Gson();
    }

    public void setToken(String token) {
        preferences.edit()
                .putString("token", token)
                .apply();
    }

    public String getToken() {
        return preferences.getString("token", null);
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public OverflowService getService() {
        return service;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
    public Gson getGson(){
        return gson;
    }
}
