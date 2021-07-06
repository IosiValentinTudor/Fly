package com.example.fly.Model;

public class locatie implements java.io.Serializable{

    String nume;
    double longitudine;
    double latitudine;
    int aterizare;
    int decolare;
    String vant;
    int id;

    public locatie() {
    }

    public locatie(locatie original)
    {
    this.nume=original.nume;
    this.longitudine=original.longitudine;
    this.latitudine=original.latitudine;
    this.aterizare=original.aterizare;
    this.decolare=original.decolare;
    this.vant=original.vant;
    this.id=original.id;

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public int getAterizare() {
        return aterizare;
    }

    public void setAterizare(int aterizare) {
        this.aterizare = aterizare;
    }

    public int getDecolare() {
        return decolare;
    }

    public void setDecolare(int decolare) {
        this.decolare = decolare;
    }

    public String getVant() {
        return vant;
    }

    public void setVant(String vant) {
        this.vant = vant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
