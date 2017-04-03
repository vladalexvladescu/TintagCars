package com.example.vlad.tintagcars;

import android.content.Context;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;







import java.util.ArrayList;




import dagger.component.PostS;




public class ServicesActivity extends AppCompatActivity {

    private ImageView search;//search button reference
    private ImageView sellCar;//Sell car button referencereference
    private ImageView myProfile;//Profile button reference
    private  ImageView services;//services button reference
    private  ImageView more;//more button reference
    public static ArrayList<PostS> postsArray;
    public static PostsAdapter postsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);//sets the view from the layout in the activity

        getSupportActionBar().hide();

        View.OnTouchListener listener = new View.OnTouchListener() {//the listener for the buttons on the buttom menu
            //it makes them appear bold when clicked
            //also handels clicks
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {


                ImageView image = (ImageView) v;
                final int menuId = v.getId();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image.getDrawable().setColorFilter(0x77000000,PorterDuff.Mode.SRC_ATOP);
                        image.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:{
                        if(menuId== R.id.search_car_image_button){

                        }
                        if(menuId== R.id.sell_car_image_button){
                            Intent pIntent = new Intent(ServicesActivity.this, SellMyCarActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.profile_image_button){
                            Intent pIntent = new Intent(ServicesActivity.this, MyProfileActivity.class);
                            // Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.services_image_button){
                            Intent pIntent = new Intent(ServicesActivity.this, ServicesActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.more_image_button){
                            Intent pIntent = new Intent(ServicesActivity.this, MoreActivity.class);
                            // Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }



                    }
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
        //injecting the views from layout & setting the listener for everyone
        search = (ImageView) findViewById(R.id.search_car_image_button);
        search.setOnTouchListener(listener);
        sellCar = (ImageView) findViewById(R.id.sell_car_image_button);
        sellCar.setOnTouchListener(listener);
        myProfile = (ImageView) findViewById(R.id.profile_image_button);
        myProfile.setOnTouchListener(listener);
        services = (ImageView) findViewById(R.id.services_image_button);
        services.setOnTouchListener(listener);
        more = (ImageView) findViewById(R.id.more_image_button);
        more.setOnTouchListener(listener);




    }





    @Override
    public void onBackPressed() {
        // super.onBackPressed();


        System.gc();
        System.exit(0);
        finish();// this line close the  app on backpress
        //needed to close the app on back pressed because I've foud a bug that when returning/
        // /in this activity the images from the post  get skrinked , till the bug it;s solved I'll use this us a hack
    }

}

