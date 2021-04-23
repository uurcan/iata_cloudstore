package com.java.iata_firebase;

public class IATAData {
    public String getIDENT() {
        return IDENT;
    }

    public void setIDENT(String IDENT) {
        this.IDENT = IDENT;
    }

    public IATAData(String IDENT) {
        this.IDENT = IDENT;
    }

    private String IDENT;
    private String IATA;
    private String NAME;
    private String ELEVATION_FT;
    private String ISO_COUNTRY;
    private String ISO_REGION;
    private String MUNICIPALITY;
    private String GPS_CODE;
    private String IATA_CODE;
    private String LOCAL_CODE;
    private String COORDINATES;

    public IATAData(String IDENT,String IATA, String NAME, String ELEVATION_FT, String ISO_COUNTRY, String ISO_REGION, String MUNICIPALITY, String GPS_CODE, String IATA_CODE, String LOCAL_CODE, String COORDINATES) {
        this.IDENT = IDENT;
        this.IATA = IATA;
        this.NAME = NAME;
        this.ELEVATION_FT = ELEVATION_FT;
        this.ISO_COUNTRY = ISO_COUNTRY;
        this.ISO_REGION = ISO_REGION;
        this.MUNICIPALITY = MUNICIPALITY;
        this.GPS_CODE = GPS_CODE;
        this.IATA_CODE = IATA_CODE;
        this.LOCAL_CODE = LOCAL_CODE;
        this.COORDINATES = COORDINATES;
    }

    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getELEVATION_FT() {
        return ELEVATION_FT;
    }

    public void setELEVATION_FT(String ELEVATION_FT) {
        this.ELEVATION_FT = ELEVATION_FT;
    }

    public String getISO_COUNTRY() {
        return ISO_COUNTRY;
    }

    public void setISO_COUNTRY(String ISO_COUNTRY) {
        this.ISO_COUNTRY = ISO_COUNTRY;
    }

    public String getISO_REGION() {
        return ISO_REGION;
    }

    public void setISO_REGION(String ISO_REGION) {
        this.ISO_REGION = ISO_REGION;
    }

    public String getMUNICIPALITY() {
        return MUNICIPALITY;
    }

    public void setMUNICIPALITY(String MUNICIPALITY) {
        this.MUNICIPALITY = MUNICIPALITY;
    }

    public String getGPS_CODE() {
        return GPS_CODE;
    }

    public void setGPS_CODE(String GPS_CODE) {
        this.GPS_CODE = GPS_CODE;
    }

    public String getIATA_CODE() {
        return IATA_CODE;
    }

    public void setIATA_CODE(String IATA_CODE) {
        this.IATA_CODE = IATA_CODE;
    }

    public String getLOCAL_CODE() {
        return LOCAL_CODE;
    }

    public void setLOCAL_CODE(String LOCAL_CODE) {
        this.LOCAL_CODE = LOCAL_CODE;
    }

    public String getCOORDINATES() {
        return COORDINATES;
    }

    public void setCOORDINATES(String COORDINATES) {
        this.COORDINATES = COORDINATES;
    }
}
