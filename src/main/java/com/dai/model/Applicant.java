package com.dai.model;


public class Applicant {
    private int id;
    private String name;
    private String idCard;
    private String phone;
    private String nativePlace;
    private String QueryPurpose;
    private String QueriedName;
    private String QueriedProfession;
    private String ImagePath;
    private String notes;
    private String Applicants;
    private String Appointment_date;
    private int role;
    private int review;
    private String reviewNote;
    private String reviewDate;

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getQueryPurpose() {
        return QueryPurpose;
    }

    public void setQueryPurpose(String queryPurpose) {
        QueryPurpose = queryPurpose;
    }

    public String getQueriedName() {
        return QueriedName;
    }

    public void setQueriedName(String queriedName) {
        QueriedName = queriedName;
    }

    public String getQueriedProfession() {
        return QueriedProfession;
    }

    public void setQueriedProfession(String queriedProfession) {
        QueriedProfession = queriedProfession;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAppointment_date() {
        return Appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        Appointment_date = appointment_date;
    }

    public String getApplicants() {
        return Applicants;
    }

    public void setApplicants(String applicants) {
        Applicants = applicants;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getReviewNote() {
        return reviewNote;
    }

    public void setReviewNote(String reviewNote) {
        this.reviewNote = reviewNote;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
