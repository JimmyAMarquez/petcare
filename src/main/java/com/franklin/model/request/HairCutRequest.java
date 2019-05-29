package com.franklin.model.request;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "HairCut")
public class HairCutRequest {

    private String appointmentId;
    private String hairCutId;

    private String date;
    private String typeOfHairCut;
    private String typeOfKnife;
    private String muzzelType;
    private String calmness;
    private String agresivness;

    private String typeOfBath;

    @DynamoDBAttribute(attributeName = "appointmentId")
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @DynamoDBHashKey(attributeName = "hairCutId")
    public String getHairCutId() {
        return hairCutId;
    }

    public void setHairCutId(String hairCutId) {
        this.hairCutId = hairCutId;
    }

    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "typeOfHairCut")
    public String getTypeOfHairCut() {
        return typeOfHairCut;
    }

    public void setTypeOfHairCut(String typeOfHairCut) {
        this.typeOfHairCut = typeOfHairCut;
    }

    @DynamoDBAttribute(attributeName = "typeOfKnife")
    public String getTypeOfKnife() {
        return typeOfKnife;
    }

    public void setTypeOfKnife(String typeOfKnife) {
        this.typeOfKnife = typeOfKnife;
    }

    @DynamoDBAttribute(attributeName = "muzzelType")
    public String getMuzzelType() {
        return muzzelType;
    }

    public void setMuzzelType(String muzzelType) {
        this.muzzelType = muzzelType;
    }

    @DynamoDBAttribute(attributeName = "calmness")
    public String getCalmness() {
        return calmness;
    }

    public void setCalmness(String calmness) {
        this.calmness = calmness;
    }

    @DynamoDBAttribute(attributeName = "agresivness")
    public String getAgresivness() {
        return agresivness;
    }

    public void setAgresivness(String agresivness) {
        this.agresivness = agresivness;
    }

    @DynamoDBAttribute(attributeName = "typeOfBath")
    public String getTypeOfBath() {
        return typeOfBath;
    }

    public void setTypeOfBath(String typeOfBath) {
        this.typeOfBath = typeOfBath;
    }
}
