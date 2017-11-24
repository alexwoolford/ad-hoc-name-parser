package io.woolford.database.entity;


import com.univocity.parsers.annotations.Parsed;

public class InfusionsoftContactRecord {

    @Parsed(index = 0)
    private int id;

    @Parsed(index = 1)
    private String name;

    @Parsed(index = 2)
    private String firstname;

    @Parsed(index = 3)
    private String surname;

    @Parsed(index = 4)
    private String company;

    @Parsed(index = 5)
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InfusionsoftContactRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
