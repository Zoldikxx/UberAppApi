package com.example.demo.People;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

public class DriverRegister implements Registration {
    Admin admin=new Admin();
    static ArrayList<People>driverList=new ArrayList<People>();
    public void register(People driver) {
        addPendingtoAdmin(driver);
        driverList.add(driver);

    }

    @Override
    public ArrayList<People> getList() {
        return driverList;
    }

    public void addPendingtoAdmin(People driver)
    {
        admin.addPendingDrivers(driver);
    }

}
