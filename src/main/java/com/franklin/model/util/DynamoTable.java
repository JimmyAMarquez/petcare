package com.franklin.model.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.franklin.model.request.AppointmentRequest;
import com.franklin.model.request.HairCutRequest;
import com.franklin.model.request.OwnerRequest;
import com.franklin.model.request.PurchaseRequest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamoTable {

    public enum DynameTableName {
        Appointment("Appointment"),HairCut("HairCut"),Owner("Owner"),Purchase("Purchase");
        private final String name;
        private DynameTableName(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }


    public static <T> List<T> scannerMapperCall(DynamoDBMapper mapper, String tableName
            , DynamoDBScanExpression dynamoDBScanExpression){

        if(tableName.equals("Owner"))
            return (List<T>) mapper.scan(OwnerRequest.class, dynamoDBScanExpression);
        else if(tableName.equals("Appointment"))
            return (List<T>) mapper.scan(AppointmentRequest.class, dynamoDBScanExpression);
        else if(tableName.equals("HairCut"))
            return (List<T>) mapper.scan(HairCutRequest.class, dynamoDBScanExpression);
        else if(tableName.equals("Purchase"))
            return (List<T>) mapper.scan(PurchaseRequest.class, dynamoDBScanExpression);

        return null;
    }


    public static boolean isMemberClassGetter(String methodName, String fullQualifiedField){
        if(fullQualifiedField.contains("com.franklin.model")){
            return methodName.contains("get");
        }
        return false;
    }

    public static String getColumnName(String methodName){
        Pattern p = Pattern.compile("get");
        Matcher m = p.matcher(methodName);
        String dbFieldName = m.replaceAll("");
        String firstChar = dbFieldName.substring(0,1);
        return m.replaceAll("").replaceFirst(firstChar,firstChar.toLowerCase());
    }

    public static String getColumnValue(Method method,Object instance) {
        try {
            String valueResult = (String) method.invoke(instance);
            if(valueResult == null)
                return "";
            return valueResult;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }
}
