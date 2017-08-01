package com.dev.pyar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("id")
@Expose
private String id;
@SerializedName("username")
@Expose
private String username;
@SerializedName("profile_picture")
@Expose
private String profilePicture;
@SerializedName("full_name")
@Expose
private String fullName;
@SerializedName("bio")
@Expose
private String bio;
@SerializedName("website")
@Expose
private String website;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getProfilePicture() {
return profilePicture;
}

public void setProfilePicture(String profilePicture) {
this.profilePicture = profilePicture;
}

public String getFullName() {
return fullName;
}

public void setFullName(String fullName) {
this.fullName = fullName;
}

public String getBio() {
return bio;
}

public void setBio(String bio) {
this.bio = bio;
}

public String getWebsite() {
return website;
}

public void setWebsite(String website) {
this.website = website;
}

}