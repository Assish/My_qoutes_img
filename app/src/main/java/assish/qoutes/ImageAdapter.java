package assish.qoutes;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import static android.widget.Toast.LENGTH_SHORT;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[]{ R.drawable.ak1, R.drawable.ak2, R.drawable.ak3, R.drawable.ak4, R.drawable.ak5, R.drawable.ak6, R.drawable.ak7, R.drawable.ak8, R.drawable.ak9, R.drawable.ak10, R.drawable.ak11, R.drawable.ak12, R.drawable.ak13, R.drawable.ak14, R.drawable.ak15, R.drawable.ak16, R.drawable.ak17, R.drawable.ak18, R.drawable.ak19, R.drawable.ak20};

    ImageAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Toast.makeText(mContext,"IMG 1", LENGTH_SHORT).show();
                }
                if(position == 1){
                    Toast.makeText(mContext,"IMG 2", LENGTH_SHORT).show();
                }
                if(position == 2){
                    Toast.makeText(mContext,"IMG 3", LENGTH_SHORT).show();
                }
                if(position == 3){
                    Toast.makeText(mContext,"IMG 4", LENGTH_SHORT).show();
                }
                if(position == 4){
                    Toast.makeText(mContext,"IMG 5", LENGTH_SHORT).show();
                }
                if(position == 5){
                    Toast.makeText(mContext,"IMG 6", LENGTH_SHORT).show();
                }
                if(position == 6){
                    Toast.makeText(mContext,"IMG 7", LENGTH_SHORT).show();
                }
                if(position == 7){
                    Toast.makeText(mContext,"IMG 8", LENGTH_SHORT).show();
                }
                if(position == 8){
                    Toast.makeText(mContext,"IMG 9", LENGTH_SHORT).show();
                }
                if(position == 9){
                    Toast.makeText(mContext,"IMG 10", LENGTH_SHORT).show();
                }
                if(position == 10){
                    Toast.makeText(mContext,"IMG 11", LENGTH_SHORT).show();
                }
                if(position == 11){
                    Toast.makeText(mContext,"IMG 12", LENGTH_SHORT).show();
                }
                if(position == 12){
                    Toast.makeText(mContext,"IMG 13", LENGTH_SHORT).show();
                }
                if(position == 13){
                    Toast.makeText(mContext,"IMG 14", LENGTH_SHORT).show();
                }
                if(position == 14){
                    Toast.makeText(mContext,"IMG 15", LENGTH_SHORT).show();
                }
                if(position == 15){
                    Toast.makeText(mContext,"IMG 16", LENGTH_SHORT).show();
                }
                if(position == 16){
                    Toast.makeText(mContext,"IMG 17", LENGTH_SHORT).show();
                }
                if(position == 17){
                    Toast.makeText(mContext,"IMG 18", LENGTH_SHORT).show();
                }
                if(position == 18){
                    Toast.makeText(mContext,"IMG 19", LENGTH_SHORT).show();
                }
                if(position == 19){
                    Toast.makeText(mContext,"IMG 20", LENGTH_SHORT).show();
                }
            }
        });
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);

    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
