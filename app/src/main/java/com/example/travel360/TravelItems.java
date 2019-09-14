package com.example.travel360;

public class TravelItems {
    String travelItemId;
    String travelItemName;
    String travelItemPrice;
    String travelItemDescription;

    public TravelItems() {
    }

    public TravelItems(String travelItemId, String travelItemName, String travelItemPrice, String travelItemDescription) {
        this.travelItemId = travelItemId;
        this.travelItemName = travelItemName;
        this.travelItemPrice = travelItemPrice;
        this.travelItemDescription = travelItemDescription;
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
}
