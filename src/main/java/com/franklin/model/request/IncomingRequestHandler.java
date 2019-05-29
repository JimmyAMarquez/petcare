package com.franklin.model.request;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.franklin.model.db.impl.DynamoEntityOperationsImpl;
import com.franklin.model.db.service.DynamoEntityOperations;
import com.franklin.model.response.PetCareResponse;
import com.franklin.model.util.MapperHelper;
import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import com.franklin.model.util.MapperType;

public class IncomingRequestHandler implements RequestStreamHandler {

    private DynamoEntityOperations dynamoEntityOperations = new DynamoEntityOperationsImpl();

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        JsonParser parser = new JsonParser();//"httpMethod" -> ""POST""
        JsonObject jsonObject = parser.parse(IOUtils.toString(inputStream, "UTF-8")).getAsJsonObject();
        String method = jsonObject.get("httpMethod").getAsString().toLowerCase();
        String bodyString="";

           if (method.equals("post")) {
               bodyString = MapperHelper.getJsonObjectString(jsonObject,"body");
               JsonObject body = parser.parse(bodyString).getAsJsonObject();
               String objectType = body.get("typeOfObject").getAsString();
               if (objectType.equals(MapperType.Appointment.toString())) {
                   dynamoEntityOperations.persistDBObject(new Gson().fromJson(bodyString, AppointmentRequest.class));
               } else if (objectType.equals(MapperType.HairCut.toString())) {
                   dynamoEntityOperations.persistDBObject(new Gson().fromJson(bodyString, HairCutRequest.class));
               } else if (objectType.equals(MapperType.OwnerRequest.toString())) {
                   dynamoEntityOperations.persistDBObject(new Gson().fromJson(bodyString, OwnerRequest.class));
               } else if (objectType.equals(MapperType.PurchaseRequest.toString())) {
                   dynamoEntityOperations.persistDBObject(new Gson().fromJson(bodyString, PurchaseRequest.class));
               }
           } else if (method.equals("get")) {
               JsonObject queryString = jsonObject.get("queryStringParameters").getAsJsonObject();

               String tableName;
               String key;
               String filter;
               String isFullAppointmentRequest;

               tableName = MapperHelper.isJsonValidMember(queryString, "table-name");
               key = MapperHelper.isJsonValidMember(queryString, "key");
               filter = MapperHelper.isJsonValidMember(queryString, "filter");
               isFullAppointmentRequest = MapperHelper.isJsonValidMember(queryString, "full-Appointment");

               byte[] response = null;
               if (isFullAppointmentRequest.isEmpty()) {
                   response = new Gson().toJson(
                           dynamoEntityOperations.getDBObject(tableName, key, filter)).getBytes(Charset.forName("UTF-8"));
               } else {

                   PetCareResponse petCareResponse = new PetCareResponse();
                   petCareResponse.setAppoitment(
                           (AppointmentRequest) dynamoEntityOperations.getDBObject("Appointment", key, filter)
                                   .get(0));
                   petCareResponse.setHairCutRequests(dynamoEntityOperations.getDBObject("HairCut", key, filter));
                   petCareResponse.setPurchaseRequests(dynamoEntityOperations.getDBObject("Purchase", key, filter));
                   response = new Gson().toJson(petCareResponse).getBytes(Charset.forName("UTF-8"));
               }

               outputStream.write(response);
           }


    }


}
