package com.hackathon.modelMCS;

/**
 * Created by HienTQSE60896 on 10/29/2016.
 */
public class PersonGroup {

    String personGroupId;
    String name;
    String userData;

    public PersonGroup() {
    }

    public PersonGroup(String personGroupId, String name, String userData) {
        this.personGroupId = personGroupId;
        this.name = name;
        this.userData = userData;
    }

    public String getPersonGroupId() {
        return personGroupId;
    }

    public void setPersonGroupId(String personGroupId) {
        this.personGroupId = personGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

}
