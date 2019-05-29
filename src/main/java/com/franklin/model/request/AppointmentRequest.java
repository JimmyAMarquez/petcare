package com.franklin.model.request;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.franklin.model.db.DynamoDO;

@DynamoDBTable(tableName = "Appointment")
public class AppointmentRequest extends DynamoDO {

    private String appointmentId;
    private String ownerId;

    private String nameOfPet;
    private String dateOfAppointments;
    private String weight;
    private String dateOfBirth;
    private String reminders;

    private String preliminarDiagnostic;
    private String confirmedDiagnostic;
    private String clinicFindings;

    private String treatmentsInClinic;
    private String treatmentsInHouse;
    private String prescription;

    @DynamoDBHashKey(attributeName = "appointmentId")
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @DynamoDBAttribute(attributeName = "ownerId")
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @DynamoDBAttribute(attributeName = "nameOfPet")
    public String getNameOfPet() {
        return nameOfPet;
    }

    public void setNameOfPet(String nameOfPet) {
        this.nameOfPet = nameOfPet;
    }

    @DynamoDBAttribute(attributeName = "dateOfAppointments")
    public String getDateOfAppointments() {
        return dateOfAppointments;
    }

    public void setDateOfAppointments(String dateOfAppointments) {
        this.dateOfAppointments = dateOfAppointments;
    }

    @DynamoDBAttribute(attributeName = "weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @DynamoDBAttribute(attributeName = "dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @DynamoDBAttribute(attributeName = "reminders")
    public String getReminders() {
        return reminders;
    }

    public void setReminders(String reminders) {
        this.reminders = reminders;
    }

    @DynamoDBAttribute(attributeName = "preliminarDiagnostic")
    public String getPreliminarDiagnostic() {
        return preliminarDiagnostic;
    }

    public void setPreliminarDiagnostic(String preliminarDiagnostic) {
        this.preliminarDiagnostic = preliminarDiagnostic;
    }

    @DynamoDBAttribute(attributeName = "confirmedDiagnostic")
    public String getConfirmedDiagnostic() {
        return confirmedDiagnostic;
    }

    public void setConfirmedDiagnostic(String confirmedDiagnostic) {
        this.confirmedDiagnostic = confirmedDiagnostic;
    }

    @DynamoDBAttribute(attributeName = "clinicFindings")
    public String getClinicFindings() {
        return clinicFindings;
    }

    public void setClinicFindings(String clinicFindings) {
        this.clinicFindings = clinicFindings;
    }

    @DynamoDBAttribute(attributeName = "treatmentsInClinic")
    public String getTreatmentsInClinic() {
        return treatmentsInClinic;
    }

    public void setTreatmentsInClinic(String treatmentsInClinic) {
        this.treatmentsInClinic = treatmentsInClinic;
    }

    @DynamoDBAttribute(attributeName = "treatmentsInHouse")
    public String getTreatmentsInHouse() {
        return treatmentsInHouse;
    }

    public void setTreatmentsInHouse(String treatmentsInHouse) {
        this.treatmentsInHouse = treatmentsInHouse;
    }

    @DynamoDBAttribute(attributeName = "prescription")
    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}
