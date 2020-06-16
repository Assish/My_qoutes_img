package assish.qoutes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (menuItem.getItemId()) {

            case R.id.nav_home5:
                Intent allcategory = new Intent(SettingsActivity.this, AllCategory.class);
                startActivity(allcategory);
                return true;

            case R.id.nav_home:
                Intent home = new Intent(this,MainActivity.class);
                startActivity(home);
                return true;

            case R.id.nav_home3:
                Intent settings = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(settings);
                return true;

            case R.id.nav_home6:
                Intent love = new Intent(SettingsActivity.this, LoveQuotes.class);
                startActivity(love);
                return true;

            case R.id.nav_home7:
                Intent mother = new Intent(SettingsActivity.this, MotherQuotes.class);
                startActivity(mother);
                return true;

            case R.id.nav_home8:
                Intent attitude = new Intent(SettingsActivity.this, AttitudeQuotes.class);
                startActivity(attitude);
                return true;

            case R.id.nav_home9:
                Intent motivational = new Intent(SettingsActivity.this, MotivationalQuotes.class);
                startActivity(motivational);
                return true;

            case R.id.nav_home10:
                Intent positive = new Intent(SettingsActivity.this, PositiveQuotes.class);
                startActivity(positive);
                return true;

            case R.id.nav_home4:
                Intent instagram = new Intent(SettingsActivity.this,InstagramActivity.class);
                startActivity(instagram);
                return true;

            case R.id.nav_home11:
                Intent inspirational = new Intent(SettingsActivity.this, InspirationalQuotes.class);
                startActivity(inspirational);
                return true;


        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }}








