package com.example.travel360;

public class TravelItems {
    String travelItemId;
    String travelItemName;
    String travelItemPrice;
    String travelItemDescription;
    String travelItemImage;
    String travelContactNumber;

    public TravelItems() {
    }

    public TravelItems(String travelItemId, String travelItemName, String travelItemPrice, String travelItemDescription, String travelItemImage, String travelContactNumber) {
        this.travelItemId = travelItemId;
        this.travelItemName = travelItemName;
        this.travelItemPrice = travelItemPrice;
        this.travelItemDescription = travelItemDescription;
        this.travelItemImage = travelItemImage;
        this.travelContactNumber = travelContactNumber;
    }

    public String getTravelItemId() {
        return travelItemId;
    }

    public void setTravelItemId(String travelItemId) {
        this.travelItemId = travelItemId;
    }

    public String getTravelItemName() {
        return travelItemName;
    }

    public void setTravelItemName(String travelItemName) {
        this.travelItemName = travelItemName;
    }

    public String getTravelItemPrice() {
        return travelItemPrice;
    }

    public void setTravelItemPrice(String travelItemPrice) {
        this.travelItemPrice = travelItemPrice;
    }

    public String getTravelItemDescription() {
        return travelItemDescription;
    }

    public void setTravelItemDescription(String travelItemDescription) {
        this.travelItemDescription = travelItemDescription;
    }

    public String getTravelItemImage() {
        return travelItemImage;
    }

    public void setTravelItemImage(String travelItemImage) {
        this.travelItemImage = travelItemImage;
    }

    public String getTravelContactNumber() {
        return travelContactNumber;
    }

    public void setTravelContactNumber(String travelContactNumber) {
        this.travelContactNumber = travelContactNumber;
    }
}
