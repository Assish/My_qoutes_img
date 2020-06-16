package assish.qoutes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GriditemActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    TextView name;
    ImageView image,  back, swipe;
    RelativeLayout relativelayout;
   private  static final String TAG = "Swipe Position";
   private float x1, x2, y1, y2;
   private static int MIN_DISTANCE = 150;
   private GestureDetector gestureDetector;

    ToggleButton toggleButton , download, share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        share = findViewById(R.id.share);
        swipe = findViewById(R.id.swipe);
        image = findViewById(R.id.imageView);
        back = findViewById(R.id.back);
        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        download = findViewById(R.id.download);
        //swipe
        this.gestureDetector = new GestureDetector(this, this);

//main to hear img pass
        final Intent intent = getIntent();

        image.setImageResource(intent.getIntExtra("image", 0));
        Random rand = new Random();
        int randImageName = rand.nextInt(1000);

        final String fileName = java.lang.String.valueOf(randImageName);

        //back..
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //favorite check
        toggleButton = (ToggleButton) findViewById(R.id.myToggleButton);
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fa1));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fa2));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fa1));
                if (isChecked)
                    Toast.makeText(GriditemActivity.this, "Added To Favorites", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GriditemActivity.this, "Removed From Favorites", Toast.LENGTH_SHORT).show();


            }
        });


//share button
        share = (ToggleButton) findViewById(R.id.share);
        share.setChecked(false);
        share.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sh));
        share.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    share.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sh));
                else
                    share.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sh));


            }

        });
//download button change
        download = (ToggleButton) findViewById(R.id.download);
        download.setChecked(false);
        download.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.do1));
        download.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    download.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.do2));
                else
                    download.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.do1));

            }

        });

//download
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = getBitmapFromView(image);
                try {
                    saveScreenshotToPicturesFolder(GriditemActivity.this, bitmap, fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

  //share
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
    }
 //swipe
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;

                case MotionEvent.ACTION_UP:
                    x2 = event.getX();
                    y2 = event.getY();

                    float valueX = x2 - x1;
                    float valueY = y2 - y1;

                    if (Math.abs(valueX) > MIN_DISTANCE)
                    {
                        if (x2 > x1)
                        {
                            Intent f = new Intent(this, FirebaseActivity.class);
                            startActivity(f);
                            Log.d(TAG, " Left Swipe");
                        }

                        else
                        {

                            ViewPager viewPager = findViewById(R.id.viewPager);
                            ImageAdapter adapter1 = new ImageAdapter(this);
                            viewPager.setAdapter(adapter1);


                            swipe.setBackgroundResource(R.drawable.images5);

                        }
                    }
                    else if (Math.abs(valueY) > MIN_DISTANCE)
                    {
                        if (y2 > y1)
                        {
                            //Toast.makeText(this, "Top is swiped", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, " Top Swipe");
                        }
                        else
                        {
                            //Toast.makeText(this, "Bottom is swiped", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, " Bottom Swipe");
                        }

                    }

        }


        return super.onTouchEvent(event);
    }
//download
    public File saveScreenshotToPicturesFolder(Context context, Bitmap image, String filename)
            throws Exception {
        File bitmapFile = getOutputMediaFile(filename);
        if (bitmapFile == null) {
            throw new NullPointerException("Error creating media file, check storage permissions!");
        }
        FileOutputStream fos = new FileOutputStream(bitmapFile);
        image.compress(Bitmap.CompressFormat.PNG, 90, fos);
        fos.close();
        MediaScannerConnection.scanFile(context, new String[]{bitmapFile.getPath()},
                new String[]{"image/jpeg"}, null);
        Toast.makeText(context, "Your picture saved in " + bitmapFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
        return bitmapFile;
    }

//share
    private void shareImage() {
        Bitmap bitmap = getBitmapFromView(image);
        try {
            File file = new File(this.getExternalCacheDir(), "images13.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            Intent intent1 = new Intent(android.content.Intent.ACTION_SEND);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(GriditemActivity.this, BuildConfig.APPLICATION_ID + ".provider", file));
            intent1.setType("image/png/*");
            startActivity(Intent.createChooser(intent1, "Share Image Via"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getBitmapFromView(View view) {

        Bitmap returnBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnBitmap);
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);

        }
        view.draw(canvas);
        return returnBitmap;
    }
//download
    private File getOutputMediaFile(String filename) {
        File mediaStorageDirectory = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        + File.separator);
        if (!mediaStorageDirectory.exists()) {
            if (!mediaStorageDirectory.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        File mediaFile;
        String mImageName = filename + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDirectory.getPath() + File.separator + mImageName);
        return mediaFile;
    }

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
}










