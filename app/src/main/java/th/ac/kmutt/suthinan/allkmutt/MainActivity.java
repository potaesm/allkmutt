package th.ac.kmutt.suthinan.allkmutt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout registButton, newacisButton, leb2Button, dekButton;

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
        }
        if (v == newacisButton) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://sinfo.kmutt.ac.th/stdmobile/mobilePages/public/login.xhtml");
            startActivity(intent);
        }
        if (v == leb2Button) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://modps70.lib.kmutt.ac.th:50104/login?app_id=1&redirect_uri=https%3A%2F%2Fapp.leb2.kmutt.ac.th%2Flogin");
            startActivity(intent);
        }
        if (v == dekButton) {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            intent.putExtra("URL", "https://m.facebook.com/WERKMUTT");
            startActivity(intent);
        }
    }
}
