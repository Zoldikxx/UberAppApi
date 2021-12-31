package com.example.demo.People;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

public class AdminRegister implements Registration {
    public ArrayList<People> AdminList=new ArrayList<People>();
    public void register(People admin){
        AdminList.add(admin);
    }
    public ArrayList<People> getList(){
        return AdminList;
    }
}

