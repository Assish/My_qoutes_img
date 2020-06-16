package assish.qoutes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


public class InstagramActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/__dummy_editz__?igshid=16gwb9vzwsdnq"));
        startActivity(browserIntent);
    }
}