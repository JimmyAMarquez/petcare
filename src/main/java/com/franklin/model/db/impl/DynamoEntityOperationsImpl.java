package com.franklin.model.db.impl;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.franklin.model.db.service.DynamoEntityOperations;
import com.franklin.model.util.DynamoTable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.Item;


public class DynamoEntityOperationsImpl implements DynamoEntityOperations {

    private DynamoDB dynamoDB;
    private AmazonDynamoDBClient client;

    private Regions REGION = Regions.US_EAST_1;

    @Override
    public void persistDBObject(Object dbObject) throws ConditionalCheckFailedException {
        this.initDynamoDbClient();
        List<Method> baseClass =  Arrays.asList(dbObject.getClass().getMethods());
        Item dbItem = new Item();

        String dbTableNameTemplate = dbObject.getClass().getName();
        String dbTableName = "";
        for(DynamoTable.DynameTableName dtn : DynamoTable.DynameTableName.values()){
            if(dbTableNameTemplate.contains(dtn.toString())){
                dbTableName= dtn.toString();
                break;
            }
        }

        TableDescription tableInfo =
                this.client.describeTable(dbTableName).getTable();

        StringBuilder primaryKey= new StringBuilder();
        StringBuilder primaryKeyValue = new StringBuilder();
        StringBuilder sortKey= new StringBuilder();
        StringBuilder sortKeyValue = new StringBuilder();
        if(!tableInfo.getKeySchema().isEmpty()) {
            primaryKey.append(tableInfo.getKeySchema().get(0).getAttributeName());
            if(tableInfo.getKeySchema().size() > 1) {
                sortKey.append(tableInfo.getKeySchema().get(1).getAttributeName());
            }
        }

        baseClass.forEach(m ->{
            if(DynamoTable.isMemberClassGetter(m.getName(),m.toString())){
                String columnValue = DynamoTable.getColumnValue(m,dbObject);
                if(!columnValue.isEmpty()){
                String methodName = DynamoTable.getColumnName(m.getName());
                if(methodName.equals(primaryKey.toString())){
                    primaryKeyValue.append(columnValue);
                }
                if(methodName.equals(sortKey.toString())){
                    sortKeyValue.append(columnValue);
                }

                dbItem.withString(methodName,columnValue);
                }
            }
        });


        QuerySpec spec = new QuerySpec()
                .withKeyConditionExpression(primaryKey.toString() + " = :v_id")
                .withValueMap(new ValueMap()
                        .withString(":v_id", primaryKeyValue.toString()));

        if(this.dynamoDB.getTable(dbTableName).query(spec).iterator().hasNext()){

            StringBuilder attributes;
            attributes = new StringBuilder();
            attributes.append("set");
            ValueMap valueMap = new ValueMap();

            Iterator<Map.Entry<String,Object>> iterator = dbItem.attributes().iterator();

            while (iterator.hasNext()){
                Map.Entry<String,Object> s = iterator.next();
                if(!(s.getKey().equals(primaryKey.toString()) || s.getKey().equals(sortKey.toString()))) {
                    attributes.append(" " + s.getKey() + " = " + ":_" + s.getKey());
                    valueMap.withString(":_" + s.getKey(), s.getValue().toString());
                    if (iterator.hasNext()) {
                        attributes.append(",");
                    }
                }
            }

            UpdateItemSpec updateItemSpec;

            updateItemSpec = new
                    UpdateItemSpec()
                    .withUpdateExpression(attributes.toString())
                    .withValueMap(valueMap);

            if(!sortKey.toString().isEmpty()){
                 updateItemSpec.withPrimaryKey(primaryKey.toString()
                         ,primaryKeyValue.toString(),sortKey.toString(),sortKeyValue.toString());
            }else{
                updateItemSpec.withPrimaryKey(primaryKey.toString(),primaryKeyValue.toString());
            }

            this.dynamoDB.
                    getTable(dbTableName)
                    .updateItem(updateItemSpec);
        }else{
            this.dynamoDB.
                    getTable(dbTableName)
                    .putItem(new PutItemSpec().withItem(dbItem));
        }

    }

    public <T> List<T> getDBObject(String tableName,String key,String filterValue) {
        this.initDynamoDbClient();

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":keyVal", new AttributeValue().withS(filterValue));

        DynamoDBScanExpression scanExpression;

        if(filterValue.isEmpty()){
            scanExpression = new DynamoDBScanExpression();
        }else {
            scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("contains(" + key + ",:keyVal)")
                    .withExpressionAttributeValues(eav);
        }
        List<T> scanResult = null;

        for(DynamoTable.DynameTableName dtn : DynamoTable.DynameTableName.values()){
            if(tableName.equals(dtn.toString())){
                scanResult = DynamoTable.scannerMapperCall(mapper,dtn.toString(),scanExpression);
                break;
            }
        }

        return scanResult;
    }

    public DynamoEntityOperationsImpl(){

    }

    private void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        this.client = client;
        this.dynamoDB = new DynamoDB(client);
    }
}
