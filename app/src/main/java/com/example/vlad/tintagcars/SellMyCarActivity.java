package com.example.vlad.tintagcars;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.ArrayList;
import dagger.component.PostS;

public class SellMyCarActivity extends AppCompatActivity {

    private ImageView search;//search button reference
    private ImageView sellCar;//Sell car button referencereference
    private ImageView myProfile;//Profile button reference
    private  ImageView services;//services button reference
    private  ImageView more;//more button reference
    private Button getUrlFromWeb;
    private Button preview;
    public static ArrayList<PostS> postsArray;
    public static PostsAdapter postsAdapter;

    private EditText url;
    private EditText carType;
    private EditText engineSize;
    private EditText kM;
    private EditText year;
    private EditText companyName;
    private EditText phone;
    private EditText location;
    private EditText price;
    private EditText yourTradeIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_my_car);//sets the view from the layout in the activity

        getSupportActionBar().hide();


        url= (EditText) findViewById(R.id.urlEditText);
        carType= (EditText) findViewById(R.id.carTypeEditText);
        engineSize= (EditText) findViewById(R.id.engineSize);
        kM= (EditText) findViewById(R.id.kmEditText7);
        year= (EditText) findViewById(R.id.yearEditText7);
        companyName= (EditText) findViewById(R.id.companyNameEditText);
        phone= (EditText) findViewById(R.id.phoneEditText);
        location= (EditText) findViewById(R.id.locationEditText);
        price= (EditText) findViewById(R.id.priceEditText);
        yourTradeIn= (EditText) findViewById(R.id.tradeInEditText);

        preview = (Button) findViewById(R.id.prewiev);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pIntent = new Intent(SellMyCarActivity.this, PreviewActivity.class);
                pIntent.putExtra("url",url.getText().toString());
                pIntent.putExtra("carType",carType.getText().toString());
                pIntent.putExtra("engineSize",engineSize.getText().toString());
                pIntent.putExtra("kM",kM.getText().toString());
                pIntent.putExtra("year",year.getText().toString());
                pIntent.putExtra("companyName",companyName.getText().toString());
                pIntent.putExtra("phone",phone.getText().toString());
                pIntent.putExtra("location",location.getText().toString());
                pIntent.putExtra("price",price.getText().toString());
                pIntent.putExtra("yourTradeIn",yourTradeIn.getText().toString());
                startActivity(pIntent);

            }
        });

        getUrlFromWeb = (Button) findViewById(R.id.getUrlFromWeb);
        getUrlFromWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

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
                            Intent pIntent = new Intent(SellMyCarActivity.this, SellMyCarActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.profile_image_button){
                            Intent pIntent = new Intent(SellMyCarActivity.this, MyProfileActivity.class);
                            // Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.services_image_button){
                            Intent pIntent = new Intent(SellMyCarActivity.this, ServicesActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            finish();
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.more_image_button){
                            Intent pIntent = new Intent(SellMyCarActivity.this, MoreActivity.class);
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

