package com.example.travel360;

public class BudgetManager {
    String budgetTransportation;
    String budgetHotelsandRestaurant;
    String budgetBillsandTickets;
    String budgetFoodandBavarage;
    String budgetOther;
    String budgetUserTelNo;
    String budgetTotal;

    public BudgetManager() {
    }

    public BudgetManager(String budgetTransportation, String budgetHotelsandRestaurant, String budgetBillsandTickets, String budgetFoodandBavarage, String budgetOther, String budgetUserTelNo, String budgetTotal) {
        this.budgetTransportation = budgetTransportation;
        this.budgetHotelsandRestaurant = budgetHotelsandRestaurant;
        this.budgetBillsandTickets = budgetBillsandTickets;
        this.budgetFoodandBavarage = budgetFoodandBavarage;
        this.budgetOther = budgetOther;
        this.budgetUserTelNo = budgetUserTelNo;
        this.budgetTotal = budgetTotal;
    }

    public String getBudgetTransportation() {
        return budgetTransportation;
    }

    public void setBudgetTransportation(String budgetTransportation) {
        this.budgetTransportation = budgetTransportation;
    }

    public String getBudgetHotelsandRestaurant() {
        return budgetHotelsandRestaurant;
    }

    public void setBudgetHotelsandRestaurant(String budgetHotelsandRestaurant) {
        this.budgetHotelsandRestaurant = budgetHotelsandRestaurant;
    }

    public String getBudgetBillsandTickets() {
        return budgetBillsandTickets;
    }

    public void setBudgetBillsandTickets(String budgetBillsandTickets) {
        this.budgetBillsandTickets = budgetBillsandTickets;
    }

    public String getBudgetFoodandBavarage() {
        return budgetFoodandBavarage;
    }

    public void setBudgetFoodandBavarage(String budgetFoodandBavarage) {
        this.budgetFoodandBavarage = budgetFoodandBavarage;
    }

    public String getBudgetOther() {
        return budgetOther;
    }

    public void setBudgetOther(String budgetOther) {
        this.budgetOther = budgetOther;
    }

    public String getBudgetUserTelNo() {
        return budgetUserTelNo;
    }

    public void setBudgetUserTelNo(String budgetUserTelNo) {
        this.budgetUserTelNo = budgetUserTelNo;
    }

    public String getBudgetTotal() {
        return budgetTotal;
    }

    public void setBudgetTotal(String budgetTotal) {
        this.budgetTotal = budgetTotal;
    }
}
