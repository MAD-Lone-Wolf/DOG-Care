package com.example.madproject;

public class Model {

    String id, dogname, dogbreed, dob, description;

    public Model() {
    }

        public Model(String id, String dogname, String dogbreed, String dob, String description){
        this.id=id;
        this.dogname=dogname;
        this.dogbreed=dogbreed;
        this.dob=dob;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDogname() {
        return dogname;
    }

    public void setDogname(String dogname) {
        this.dogname = dogname;
    }

    public String getDogbreed() {
        return dogbreed;
    }

    public void setDogbreed(String dogbreed) {
        this.dogbreed = dogbreed;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
