package com.example.travel360.model;

public class CartItems {
    String cartItemPrice;
    String cartItemId;
    String cartItemDescription;
    String cartItemName;
    String cartUserTelNo;
    String cartItemBuyDate;
    String cartItemQuantity;

    public CartItems() {
    }

    public CartItems(String cartItemPrice, String cartItemId, String cartItemDescription, String cartItemName, String cartUserTelNo, String cartItemBuyDate, String cartItemQuantity) {
        this.cartItemPrice = cartItemPrice;
        this.cartItemId = cartItemId;
        this.cartItemDescription = cartItemDescription;
        this.cartItemName = cartItemName;
        this.cartUserTelNo = cartUserTelNo;
        this.cartItemBuyDate = cartItemBuyDate;
        this.cartItemQuantity = cartItemQuantity;
    }

    public String getCartItemPrice() {
        return cartItemPrice;
    }

    public void setCartItemPrice(String cartItemPrice) {
        this.cartItemPrice = cartItemPrice;
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCartItemDescription() {
        return cartItemDescription;
    }

    public void setCartItemDescription(String cartItemDescription) {
        this.cartItemDescription = cartItemDescription;
    }

    public String getCartItemName() {
        return cartItemName;
    }

    public void setCartItemName(String cartItemName) {
        this.cartItemName = cartItemName;
    }

    public String getCartUserTelNo() {
        return cartUserTelNo;
    }

    public void setCartUserTelNo(String cartUserTelNo) {
        this.cartUserTelNo = cartUserTelNo;
    }

    public String getCartItemBuyDate() {
        return cartItemBuyDate;
    }

    public void setCartItemBuyDate(String cartItemBuyDate) {
        this.cartItemBuyDate = cartItemBuyDate;
    }

    public String getCartItemQuantity() {
        return cartItemQuantity;
    }

    public void setCartItemQuantity(String cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }
}
