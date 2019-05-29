package com.franklin.model.request;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Purchase")
public class PurchaseRequest {

    private String appointmentId;
    private String purchaseId;

    private String food;
    private String shampoo;

    @DynamoDBAttribute(attributeName = "appointmentId")
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @DynamoDBHashKey(attributeName = "purchaseId")
    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    @DynamoDBAttribute(attributeName = "food")
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @DynamoDBAttribute(attributeName = "shampoo")
    public String getShampoo() {
        return shampoo;
    }

    public void setShampoo(String shampoo) {
        this.shampoo = shampoo;
    }
}
