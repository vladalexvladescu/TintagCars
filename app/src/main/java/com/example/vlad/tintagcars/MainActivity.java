package com.example.vlad.tintagcars;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


import javax.inject.Inject;

import dagger.component.PostS;
import dagger.component.RestApi;

import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    private ListView postsListView;
    private ImageView search;//search button reference
    private ImageView sellCar;//Sell car button referencereference
    private ImageView myProfile;//Profile button reference
    private  ImageView services;//services button reference
    private  ImageView more;//more button reference
    public  ArrayList<PostS> postsArray;
    public  ArrayList<PostS> postsArrayNoResult;
    public  ArrayList<PostS> postsArrayAfterSearch;
    public static PostsAdapter postsAdapter;
    private String responseRequest;
    private Context context;
    private EditText inputSearch;
    private TextView terms;

    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//sets the view from the layout in the activity

        ((App) getApplication()).getNetComponent().inject(this);

        context=this;
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputSearch.setVisibility(View.INVISIBLE);
        terms = (TextView) findViewById(R.id.terms);



        Call<ResponseBody> posts1 = retrofit.create(RestApi.class).getAllPosts();


        posts1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    //aici stiu ca nu e ok cum e facuta conversia trebuie mapat JSON-ul direct pe obiect
                    //o sa modific
                    //#reminder
                   String  arrayUsers = response.body().string();

                    JSONArray businesses = null;
                    try {
                        businesses = new JSONArray(arrayUsers);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ArrayList<PostS> serverArrayUsers = PostS.fromJson(businesses);

                    for(PostS index:serverArrayUsers){
                       if(index.getLatitude()==1){//we gonna retrive only the lements from the request that are equal to 1
                            //thats how we know that the elements are posts from this application

                            postsArray.add(index);
                            postsAdapter.add(index,context);
                            postsAdapter.notifyDataSetChanged();
                        }

                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }



                //check to see if the current array it's same size
                //if not send a notification to the user:

                //check to see if the current array it's same size.



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("arrayUsers","faill" );

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
                            inputSearch.setVisibility(View.VISIBLE);
                            terms.setVisibility(View.INVISIBLE);
                            inputSearch.addTextChangedListener(new TextWatcher() {

                                @Override
                                public void onTextChanged(CharSequence cs, int arg1, int arg2, int backSpace) {
                                    Log.d("changed","changed: "+cs+"  name: ");
                                  //  String previousText = String.valueOf(inputSearch.getText());
                                    postsArrayAfterSearch.clear();
                                    postsArrayNoResult = postsArray;
                                    for(PostS index:postsArray){
                                        Log.d("changed","changed: "+cs+"  name: " +index.getUserFirstName());

                                       if( index.getUserFirstName().toLowerCase().contains(cs.toString().toLowerCase())){
                                           Log.d("changed","intra 1");
                                           //postsAdapter = new PostsAdapter((Activity) context, postsArray);
                                         //  postsListView.setAdapter(postsAdapter);
                                          // postsAdapter = new PostsAdapter((Activity) context, postsArray);
                                          // postsListView.setAdapter(postsAdapter);
                                           postsArrayAfterSearch.add(index);

                                         //  postsListView.invalidate();


                                       }



                                    }
                                    if(postsArrayAfterSearch.size()!=0){
                                        postsArrayNoResult.clear();
                                        for(PostS p :postsArrayAfterSearch){
                                            postsAdapter.add(p,context);
                                            postsAdapter.notifyDataSetChanged();
                                            Log.d("changed","intra 2 ");
                                        }

                                    }else{

                                    }
                                }

                                @Override
                                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                                              int arg3) {
                                    // TODO Auto-generated method stub

                                }

                                @Override
                                public void afterTextChanged(Editable arg0) {
                                    // TODO Auto-generated method stub
                                }
                            });

                        }
                        if(menuId== R.id.sell_car_image_button){
                            Intent pIntent = new Intent(MainActivity.this, SellMyCarActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.profile_image_button){
                            Intent pIntent = new Intent(MainActivity.this, MyProfileActivity.class);
                            // Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.services_image_button){
                            Intent pIntent = new Intent(MainActivity.this, ServicesActivity.class);
                            //Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
                            startActivity(pIntent);
                        }
                        if(menuId== R.id.more_image_button){
                            Intent pIntent = new Intent(MainActivity.this, MoreActivity.class);
                            // Bundle pBundle = new Bundle();
                            //pIntent.putExtra("users","MERGE");
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



        postsListView = (ListView) findViewById(R.id.posts_area);//sets the listView of post from the layout
        postsListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);





        postsArray = new ArrayList<PostS>();
        postsArrayAfterSearch = new  ArrayList<PostS>();
        postsArrayNoResult = new  ArrayList<PostS>();
        postsArrayNoResult = postsArray;


        postsAdapter = new PostsAdapter(this, postsArray);
        postsListView.setAdapter(postsAdapter);



    }





    @Override
    public void onBackPressed() {
       // super.onBackPressed();

        if (inputSearch.getVisibility()==View.VISIBLE){
            inputSearch.setVisibility(View.INVISIBLE);
            terms.setVisibility(View.VISIBLE);

        }else{
            System.gc();
            System.exit(0);
            finish();// this line close the  app on backpress
            //needed to close the app on back pressed because I've foud a bug that when returning/
            // /in this activity the images from the post  get skrinked , till the bug it;s solved I'll use this us a hack
        }

    }

}
