package com.example.asmandroid.model;

public class SinhVien {
    private String idSV;
    private String name;
    private String sdt;

    public SinhVien() {
    }

    public SinhVien(String idSV, String name, String sdt) {
        this.idSV = idSV;
        this.name = name;
        this.sdt = sdt;
    }

    public String getIdSV() {
        return idSV;
    }

    public void setIdSV(String idSV) {
        this.idSV = idSV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
