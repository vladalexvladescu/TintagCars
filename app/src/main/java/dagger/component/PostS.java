package dagger.component;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Vlad on 1/7/2017.
 */

public class PostS {
    @SerializedName("idfacebookuser")
    @Expose



    private long idfacebookUser;

    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("email")
    @Expose
    private String userEmail;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("userid")
    @Expose
    private String userid;


    @SerializedName("latitude")
    @Expose
    private float  latitude;

    @SerializedName("longitude")
    @Expose
    private float longitude;

    public long getUserIdDatabase() {
        return idfacebookUser;
    }
    public void setUserIdDatabase(long userIdDatabase) {
        this.idfacebookUser = userIdDatabase;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }


    public String getUserPhoto() {
        return picture;
    }

    public String getUserFirstName() {
        return firstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserLastName() {
        return lastName;
    }

    public String getUserGender() {
        return gender;
    }

    public String getUserBday() {
        return birthday;
    }

    public String getUserLink() {
        return link;
    }

    public String getUserID() {
        return userid;
    }


    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhoto(String userPhoto) {
        this.picture = userPhoto;
    }

    public void setUserFirstName(String userFirstName) {
        this.firstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.lastName = userLastName;
    }

    public void setUserGender(String userGender) {
        this.gender = userGender;
    }

    public void setUserBday(String userBday) {
        this.birthday = userBday;
    }

    public void setUserLink(String userLink) {
        this.link = userLink;
    }

    public void setUserID(String userID) {
        this.userid = userID;
    }


    public static PostS fromJson(JSONObject jsonObject) {
        PostS b = new PostS();
        // Deserialize json into object fields

        try {
            b.idfacebookUser = jsonObject.getInt("idfacebookUser");
            b.link= jsonObject.getString("link");
            b.picture = jsonObject.getString("picture");
            b.firstName = jsonObject.getString("firstName");
            b.lastName = jsonObject.getString("lastName");
            b.userid= jsonObject.getString("userId");
            b.birthday = jsonObject.getString("birthday");
            b.gender = jsonObject.getString("gender");
            b.userEmail= jsonObject.getString("userEmail");


            b.latitude = (float) jsonObject.getDouble("latitude") ;
            b.longitude = (float) jsonObject.getDouble("longitude");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }
    public static ArrayList<PostS> fromJson(JSONArray jsonArray) {
        JSONObject businessJson;
        ArrayList<PostS> businesses = new ArrayList<PostS>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                businessJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            PostS business = PostS.fromJson(businessJson);
            if (business != null) {
                businesses.add(business);
            }
        }

        return businesses;
    }

}
