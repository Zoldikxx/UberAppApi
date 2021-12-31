package com.example.demo.People;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

public class CustomerRegister implements Registration {
    static ArrayList<People> customerList= new ArrayList<People>();
    public void register(People customer) {
        customerList.add(customer);
    }

    @Override
    public ArrayList<People> getList() {
        return customerList;
    }
}
