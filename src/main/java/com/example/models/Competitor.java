/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.models;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mauricio
 */
@Entity
public class Competitor implements Serializable{

    private static final long serialVersionUID = 2L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    private String surname;
    
    private int age;
    
    private String telephone;
    
    private String cellphone;
    
    private String address;
    
    private String city;
    
    private String country;
    
    private boolean winner;
    
    @Column(nullable = false)
    private String password;
    
    //PROPOSITOS DE AUDITORIA

    @Column(name = "created_at", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar createAt;
    
    @Column(name = "update_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar updateAt;
    
    public Competitor(){
        
    }
    
    public Competitor(String nameN, String surnameN, int ageN,String telephoneN, String cellphoneN, String addressN, String  cityN, String countryN,boolean winnerN, String passwordN){
        name=nameN;
        surname=surnameN;
        age=ageN;
        telephone=telephoneN;
        cellphone=cellphoneN;
        address=addressN;
        city=cityN;
        country=countryN;
        winner=winnerN;
        password=passwordN;
    }
    
    public Long getId(){
        return id;
    }
    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    
    @PrePersist
    private void creationTimeStamp(){
        createAt = updateAt = Calendar.getInstance();
    }
    
    @PreUpdate
    private void updateTimeStamp(){
        updateAt = Calendar.getInstance();
    }
   
    private String getPassword(){
        return password;
    }
    
}
