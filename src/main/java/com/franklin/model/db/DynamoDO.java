package com.franklin.model.db;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public abstract class DynamoDO {
    private String method = "";
    private String template = "";
    private String type = "";

    @DynamoDBAttribute(attributeName = "methodOfRequest")
    public  String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method = method;
    }

    @DynamoDBAttribute(attributeName = "template")
    public String getTemplate() { return template;}

    public void setTemplate(String template) {
        this.template = template;
    }

    @DynamoDBAttribute(attributeName = "typeOfObject")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
