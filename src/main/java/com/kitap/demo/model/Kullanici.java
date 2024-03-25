package com.kitap.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="kullanici",schema = "public")
public class Kullanici {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kullanici_id_seq")
    @SequenceGenerator(name = "kullanici_id_seq", sequenceName = "kullanici_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
