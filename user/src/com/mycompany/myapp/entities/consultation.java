/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author USER
 */
public class consultation {
    int id;
    Date date;
    Date heuredebut;
    Date heurefin;

    public consultation() {
    }

    public consultation(int id) {
        this.id = id;
    }
    

    public consultation(Date date, Date heuredebut, Date heurefin) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }

    public consultation(int id, Date date, Date heuredebut, Date heurefin) {
        this.id = id;
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Date getHeuredebut() {
        return heuredebut;
    }

    public Date getHeurefin() {
        return heurefin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHeuredebut(Date heuredebut) {
        this.heuredebut = heuredebut;
    }

    public void setHeurefin(Date heurefin) {
        this.heurefin = heurefin;
    }

}
