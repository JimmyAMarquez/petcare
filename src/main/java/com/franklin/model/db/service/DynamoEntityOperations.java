package com.franklin.model.db.service;

import java.util.List;

public interface DynamoEntityOperations {
    void persistDBObject(Object dbObject);
    <T> List<T> getDBObject(String tableName, String key, String filterValue);
}
