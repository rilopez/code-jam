package com.nearsoft.codejam.storecredit;

public class InputStoreCredit {
    private int _credit;

    private int _totalItems;
    private String _items;

    public InputStoreCredit() {

    }

    public InputStoreCredit(int credit, int totalItems, String items) {
        _credit = credit;
        _totalItems = totalItems;
        _items = items;
    }

    public void setCredit(int credit) {
        _credit = credit;
    }

    public void setTotalItems(int totalItems) {
        _totalItems = totalItems;
    }

    public void setItems(String items) {
        _items = items;
    }

    public int getCredit() {
        return _credit;
    }

    public int getTotalItems() {
        return _totalItems;
    }

    public String getItems() {
        return _items;
    }

}
