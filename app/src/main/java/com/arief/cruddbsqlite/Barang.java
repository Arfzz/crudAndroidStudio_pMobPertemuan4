package com.arief.cruddbsqlite;

public class Barang {
    private long id;
    private String namaBarang;
    private String merkBarang;
    private String asalNegara;

    public Barang(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setMerkBarang(String merkBarang) {
        this.merkBarang = merkBarang;
    }

    public String getMerkBarang() {
        return merkBarang;
    }

    public void setAsalNegara(String asalNegara) {
        this.asalNegara = asalNegara;
    }

    public String getAsalNegara() {
        return asalNegara;
    }

    public String toString(){
        return ">> " + namaBarang;
    }


}
