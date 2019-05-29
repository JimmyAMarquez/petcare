package com.franklin.model.response;

import com.franklin.model.request.AppointmentRequest;
import com.franklin.model.request.HairCutRequest;
import com.franklin.model.request.PurchaseRequest;

import java.util.List;

public class PetCareResponse {

   private AppointmentRequest appoitment;

   private List<HairCutRequest> hairCutRequests;

   private List<PurchaseRequest> purchaseRequests;


   public AppointmentRequest getAppoitment() {
      return appoitment;
   }

   public void setAppoitment(AppointmentRequest appoitment) {
      this.appoitment = appoitment;
   }

   public List<HairCutRequest> getHairCutRequests() {
      return hairCutRequests;
   }

   public void setHairCutRequests(List<HairCutRequest> hairCutRequests) {
      this.hairCutRequests = hairCutRequests;
   }

   public List<PurchaseRequest> getPurchaseRequests() {
      return purchaseRequests;
   }

   public void setPurchaseRequests(List<PurchaseRequest> purchaseRequests) {
      this.purchaseRequests = purchaseRequests;
   }
}
