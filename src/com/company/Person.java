package com.company;
import java.util.Date;
import java.util.Scanner;

public class Person {

    private int id;
    private String name;
    private String vorname;
    private Date geburtsdatum;
    private String postleitzahl;
    private String ort;
    private String hobby;
    private String lieblingsgericht;
    private String lieblingsband;

    public Person() {

    }

    Scanner sc = new Scanner(System.in);

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

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    // Getter und setter für neue Einträge

    public void setHobby(String hobby){this.hobby = hobby;}

    public String getHobby(){return hobby;}

    public void setLieblingsgericht(String lieblingsgericht){this.lieblingsgericht = lieblingsgericht; }

    public String getLieblingsgericht(){return lieblingsgericht;}

    public void setLieblingsband(String lieblingsband){this.lieblingsband = lieblingsband;}

    public String getLieblingsband(){return lieblingsband;}

    @Override
    public String toString() {
        return "[[" + this.id + "] ["+ this.name + "] [" + this.vorname + "]" + " [" + this.ort
                + "] [" + this.postleitzahl + "] [" + this.geburtsdatum + "] [" + this.hobby + "] ["
                + this.lieblingsgericht + "] [" + this.lieblingsband + "]]";
    }
}