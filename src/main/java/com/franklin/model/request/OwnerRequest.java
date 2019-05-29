package com.franklin.model.request;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.franklin.model.db.DynamoDO;

@DynamoDBTable(tableName = "Owner")
public class OwnerRequest extends DynamoDO {
    private String ownerId;

    @DynamoDBHashKey(attributeName = "ownerId")
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
