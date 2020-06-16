package assish.qoutes;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;

    }

    public void setdetails(Context context, String image1){

        ImageView mImagetv = view.findViewById(R.id.rImageView);
        Picasso.get().load(image1).into(mImagetv);

        Animation animation = AnimationUtils.loadAnimation(context,android.R.anim.slide_in_left);
        itemView.startAnimation(animation);

    }
}
