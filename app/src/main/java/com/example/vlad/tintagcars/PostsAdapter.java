package com.example.vlad.tintagcars;

/**
 * Created by Vlad on 3/29/2017.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import dagger.component.PostS;

public class PostsAdapter extends BaseAdapter {
    private Button phoneButton;
    private static LayoutInflater inflater = null;
    private  ArrayList<PostS> postsList; //the list of PostS type objects that cammed from the server
    private Context context;//we need the context for every view in this case we passed the MainActivity context for every addPost method call
    private ImageView editComment;//the ImageView that represents the small pencil on the screen to edit comments, when this wiew it;s clicked it changes color

    public PostsAdapter(Activity activity, ArrayList<PostS> list) {
        postsList = list;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PostS post = (PostS) postsList.get(position);
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.post, null);

        phoneButton = (Button)vi.findViewById(R.id.phone);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+post.getUserBday()));//opens a phone call intent and lets the user call the post number
                context.startActivity(intent);
            }
        });

        TextView t1textCarType = (TextView) vi.findViewById(R.id.textCarType);
        TextView t2textCarDetails = (TextView) vi.findViewById(R.id.textCarDetails);
        TextView t3companyName = (TextView) vi.findViewById(R.id.companyName);
        TextView t4locationText = (TextView) vi.findViewById(R.id.locationText);
        TextView t5phone = (TextView) vi.findViewById(R.id.phone);
        TextView t6price = (TextView) vi.findViewById(R.id.price);
        TextView t7discount = (TextView) vi.findViewById(R.id.discount);
        TextView t8totalPrice = (TextView) vi.findViewById(R.id.totalPrice);



        editComment = (ImageView) vi.findViewById(R.id.editComment);
        editComment.setOnTouchListener(listener) ;
        editComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



        ImageView image = (ImageView) vi.findViewById(R.id.imagePost);


        Glide.with(context)//because for some reason some of the post

                .load(post.getUserPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()

                .priority(Priority.IMMEDIATE)

                .into(image);
        Log.d("changedpost.getUserID()","intra 1"+post.getUserGender());


        t1textCarType.setText(post.getUserLink());
        t2textCarDetails.setText(post.getUserFirstName());
        t3companyName.setText(post.getUserLastName());
        t4locationText.setText(post.getUserEmail());
        t5phone.setText("Phone: "+post.getUserBday());

        if(! post.getUserGender().equals("null")){//this is a hack for the time when there were posts
            //with a null value on UserGender() and there was no price in the database
            String CurrentString = post.getUserGender();
            String[] separated = CurrentString.split("UntoldFestival");
            int price = Integer.parseInt(separated[0]);
            int discount = Integer.parseInt(separated[1]);
            int total = price-discount;

            t6price.setText("$"+separated[0]);
            t7discount.setText("-$"+separated[1]);
            t8totalPrice.setText("$"+total);
        }else{
            t6price.setText("$50000");
            t7discount.setText("-$2000");
            t8totalPrice.setText("$48000");
        }




        RelativeLayout layout = (RelativeLayout) vi
                .findViewById(R.id.title_Top);
        LinearLayout parent_layout = (LinearLayout) vi
                .findViewById(R.id.posts_layout_parent);

        // if message is mine then align to right


        return vi;
    }
    View.OnTouchListener listener = new View.OnTouchListener() {
        private Rect rect;
        @Override
        public boolean onTouch(View v, MotionEvent event) {


            ImageView image = (ImageView) v;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    image.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                    image.invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    //clear the overlay
                    image.getDrawable().clearColorFilter();
                    image.invalidate();
                    break;
                }
            }

            return true;
        }
    };

    public void add(PostS object, Context context) {
        postsList.add(object);
        this.context=context;
    }
}