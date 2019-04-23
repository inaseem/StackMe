package ali.naseem.stackme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MY_REDIRECT_URI = "https://stackexchange.com/oauth/login_success";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com/oauth/dialog?client_id=" + BuildConfig.CLIENT_ID + "&redirect_uri=" + MY_REDIRECT_URI));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        try {
            if (uri != null)
                if (uri.toString().startsWith("https://stackexchange.com/oauth/login_success")) {
                    String pairs[] = uri.toString().split("#")[1].split("&");
                    for (String pair : pairs) {
                        String vals[] = pair.split("=");
                        if (vals[0].equals("access_token")) {
                            Utils.getInstance().setToken(vals[1]);
                        }
                    }
                }
            Log.d("RESPONE", uri.toString());
        } catch (Exception e) {

        }

        if (Utils.getInstance().getToken() != null) {
            Intent intent = new Intent(this, UserInterestActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Not Authenticated", Toast.LENGTH_SHORT).show();
        }
    }
}
