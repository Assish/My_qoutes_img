package assish.qoutes;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, ActivityCompat.OnRequestPermissionsResultCallback,
        NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {
    List<String> fb = new ArrayList<>();

    private static final String TAG = "MainActivity";
    private DatabaseReference reference;
    ImageView imageView, images21, images22, images23;
    final List<String> images1 = new ArrayList<>();


    GridView gridView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference1;


    NavigationView navigationView;


    String[] list = {"Apple", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear", "assish"};

    int[] qoutesImages = { R.drawable.ak1, R.drawable.ak2, R.drawable.ak3, R.drawable.ak4, R.drawable.ak5, R.drawable.ak6, R.drawable.ak7, R.drawable.ak8, R.drawable.ak9, R.drawable.ak10, R.drawable.ak11, R.drawable.ak12, R.drawable.ak13, R.drawable.ak14, R.drawable.ak15, R.drawable.ak16, R.drawable.ak17, R.drawable.ak18, R.drawable.ak19, R.drawable.ak20};
    private static final int REQUEST_WRITE_PERMISSION = 786;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu2);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        reference = FirebaseDatabase.getInstance().getReference();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + map);

//                for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
//                    images.add(childSnapshot.getValue(String.class));
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, list);
        //Getting the instance of AutoCompleteTextView


        firebaseDatabase = FirebaseDatabase.getInstance();



        gridView = findViewById(R.id.gridview);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        imageView =  findViewById(R.id.imageview1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All");

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.authText);
        LinearLayout linearLayout = (LinearLayout) headerView.findViewById(R.id.authLaout);
        //  navUsername.setText("Your Text Here");


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorBlueDark));

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), GriditemActivity.class);
                intent.putExtra("image", qoutesImages[position]);
                startActivity(intent);
            }
        });
        requestPermission();



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch (menuItem.getItemId()) {

            case R.id.nav_home5:
                Intent allcategory = new Intent(MainActivity.this,AllCategory.class);
                startActivity(allcategory);
                return true;

            case R.id.nav_home:
                Intent home = new Intent(this,MainActivity.class);
                startActivity(home);
                return true;

            case R.id.nav_home4:
                Intent instagram = new Intent(this,InstagramActivity.class);
                startActivity(instagram);
                return true;

            case R.id.nav_home3:
                Intent settings= new Intent(this,SettingsActivity.class);
                startActivity(settings);
                return true;

            case R.id.nav_home6:
                Intent love = new Intent(this,LoveQuotes.class);
                startActivity(love);
                return true;

            case R.id.nav_home7:
                Intent mother = new Intent(this,MotherQuotes.class);
                startActivity(mother);
                return true;

            case R.id.nav_home8:
                Intent attitude = new Intent(this,AttitudeQuotes.class);
                startActivity(attitude);
                return true;

            case R.id.nav_home9:
                Intent motivational = new Intent(this,MotivationalQuotes.class);
                startActivity(motivational);
                return true;

            case R.id.nav_home10:
                Intent positive = new Intent(this,PositiveQuotes.class);
                startActivity(positive);
                return true;

            case R.id.nav_home11:
                Intent inspirational = new Intent(this,InspirationalQuotes.class);
                startActivity(inspirational);
                return true;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return qoutesImages.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);
            ImageView image = view1.findViewById(R.id.images);
            image.setImageResource(qoutesImages[i]);
            return view1;

        }


    }



}
