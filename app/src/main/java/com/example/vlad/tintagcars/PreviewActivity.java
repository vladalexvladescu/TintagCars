package com.example.vlad.tintagcars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.component.PostS;
import dagger.component.RestApi;
import dagger.component.TokenRequest;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PreviewActivity extends AppCompatActivity {
    private Button postPreviewButton;
    PostS post;
    TokenRequest request;
    @Inject
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);


        getSupportActionBar().hide();

        ((App) getApplication()).getNetComponent().inject(this);//dagger2 inject

        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            post = new PostS();
            //request = new TokenRequest();
            post.setUserPhoto(extras.getString("url"));//the url for the photo
           // request.setPicture(extras.getString("url"));
            post.setUserLink(extras.getString("carType"));//the car type
          //  request.setLink(extras.getString("carType"));
            post.setUserFirstName(extras.getString("year")+" | Clark"+" | "+extras.getString("kM")+" | "+extras.getString("engineSize")+" | ");//the car details
          //  request.setFirstName(extras.getString("year")+" | Clark"+" | "+extras.getString("kM")+" | "+extras.getString("engineSize")+" | ");
            post.setUserLastName(extras.getString("companyName"));//the company name
          //  request.setLastName(extras.getString("companyName"));
            post.setUserBday(extras.getString("phone"));//the phone
          //  request.setBirthday(extras.getString("phone"));
            post.setUserEmail(extras.getString("location"));//location
        //    request.setUserEmail(extras.getString("location"));
            post.setUserGender(extras.getString("price")+"UntoldFestival"+extras.getString("yourTradeIn"));
          //  request.setIdFacebookUser(6666);
            //in the setID I put the price and the trade in because I was running out
            // of arguments in the server type object , when the response comes back from the server
            // I vill separate this strings
            post.setLatitude(1);//I will use this element just to know wich items on the response array
        //
            //are post from this apps the other ones will be items from my app (users) so we will ignore them
            //by checking this atribute,I doubt that anyone will use my app (TerraConnnect) having this latitude anytime soon :)))))
            post.setLongitude(1);
            post.setUserID(extras.getString("phone"));//we gonna identify the user by the phone number
            post.setUserIdDatabase(1);
           // post.setUserIdDatabase(1);
           // post.setUserID("666");
         //   request.setLongitude(1);
            TextView t1textCarType = (TextView) findViewById(R.id.textCarType);
            TextView t2textCarDetails = (TextView) findViewById(R.id.textCarDetails);
            TextView t3companyName = (TextView) findViewById(R.id.companyName);
            TextView t4locationText = (TextView) findViewById(R.id.locationText);
            TextView t5phone = (TextView) findViewById(R.id.phone);
            TextView t6price = (TextView) findViewById(R.id.price);
            TextView t7discount = (TextView) findViewById(R.id.discount);
            TextView t8totalPrice = (TextView) findViewById(R.id.totalPriceT);



            ImageView image = (ImageView) findViewById(R.id.imagePost);


            Glide.with(this)//Had to set syze dynamicaly because for some reason some of the post
                    //images got shrinked
                    //here we load the links for every post image
                    //the link it's provided by the
                    .load(post.getUserPhoto())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()

                    .priority(Priority.IMMEDIATE)

                    .into(image);

            String CurrentString = post.getUserGender();
            String[] separated = CurrentString.split("UntoldFestival");

            t1textCarType.setText(post.getUserLink());
            t2textCarDetails.setText(post.getUserFirstName());
            t3companyName.setText(post.getUserLastName());
            t4locationText.setText(post.getUserEmail());
            t5phone.setText("Phone: "+post.getUserBday());//?
            t6price.setText(separated[0]);
            t7discount.setText(separated[1]);
            int price = Integer.parseInt(separated[0]);
            int discount = Integer.parseInt(separated[1]);
            int total = price-discount;
            t8totalPrice.setText(String.valueOf(total));//i need to validate all the input otherwise the
            //app crashes if the user types malicious input

            postPreviewButton = (Button) findViewById(R.id.postPreviewButton);
            postPreviewButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    //  }


                    Call<ResponseBody> service = retrofit.create(RestApi.class).updateUser(post);
                    service.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> service, Response<ResponseBody> response) {
                            int statusCode = response.code();

                            Context context = getApplicationContext();
                            CharSequence text = "Post added with success!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                            Intent pIntent = new Intent(PreviewActivity.this, MainActivity.class);
                            finish();
                            startActivity(pIntent);


                            Log.d("request","response: "+statusCode);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("arrayUsers", "faill");
                            Context context = getApplicationContext();
                            CharSequence text = "Failed to add post!Please check the data you entered!";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        }
                    }); //REQUEST TO PUT USER IN DATABASE/

                }
            });


        }



    }
}
