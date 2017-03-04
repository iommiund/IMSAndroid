package com.iaum00557329.androidims.androidims;

/**
 * Created by lommi on 03/03/2017.
 */

public class OrderList {
    private String head;
    private String desc;

    //constructor initializing values
    public OrderList(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    //getters
    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
