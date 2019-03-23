package th.ac.kmutt.suthinan.allkmutt;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout registButton, newacisButton, leb2Button, dekButton;
    PackageManager pm;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        registButton = findViewById(R.id.registButton);
        newacisButton = findViewById(R.id.newacisButton);
        leb2Button = findViewById(R.id.leb2Button);
        dekButton = findViewById(R.id.dekButton);

        registButton.setOnClickListener(this);
        newacisButton.setOnClickListener(this);
        leb2Button.setOnClickListener(this);
        dekButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == registButton) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "http://regis.kmutt.ac.th/service");
            startActivity(intent);
            finish();
        }
        if (v == newacisButton) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://sinfo.kmutt.ac.th/stdmobile");
            startActivity(intent);
            finish();
        }
        if (v == leb2Button) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://leb2.kmutt.ac.th");
            startActivity(intent);
            finish();
        }
        if (v == dekButton) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://m.facebook.com/WERKMUTT");
            startActivity(intent);
            finish();
        }
    }
}
