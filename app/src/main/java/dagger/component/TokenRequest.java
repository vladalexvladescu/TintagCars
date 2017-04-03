package dagger.component;

/**
 * Created by Vlad on 4/1/2017.
 */

public class TokenRequest {




    long idfacebookUser;
    String link;
    String picture;
    String firstName;
    String lastName;
    String userId;
    String birthday;
    String gender;
    String userEmail;

    public TokenRequest( ) {

    }

    float latitude, longitude;


    public long getIdFacebookUser() {
        return idfacebookUser;
    }

    public void setIdFacebookUser(long idFacebookUser) {
        this.idfacebookUser = idFacebookUser;
    }

    public String getLink() {
        return link;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserId() {
        return userId;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}
