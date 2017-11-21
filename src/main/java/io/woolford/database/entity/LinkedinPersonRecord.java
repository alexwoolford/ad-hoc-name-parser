package io.woolford.database.entity;

public class LinkedinPersonRecord {

    String name;
    String title;
    String url;
    int page;
    String firstname;
    String surname;
    String gender;
    double genderConfidence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getGenderConfidence() {
        return genderConfidence;
    }

    public void setGenderConfidence(double genderConfidence) {
        this.genderConfidence = genderConfidence;
    }

    @Override
    public String toString() {
        return "LinkedinPersonRecord{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", page=" + page +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", genderConfidence=" + genderConfidence +
                '}';
    }

}
