package com.example.demo.People;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path="api/admin")
public class Admin extends People {

    static ArrayList<People> PendingDrivers=new ArrayList<People>();
    Registration adminRegister=new AdminRegister();
    ArrayList<People> AdminList= adminRegister.getList();
    boolean flag=false;
    public Admin(){
        super("","","","");
    }
    public Admin(String id,String pw,String Email,String num)
    {
        super(id,pw,Email,num);
    }
    @RequestMapping(path="register",method = POST)
    public void register(People admin) throws IOException {
        adminRegister.register(admin);
    }
    @RequestMapping(path="signin",method = POST)
    public boolean signIn(String Id, String pass){
        boolean flag=false;
        for(int i=0;i<AdminList.size();i++){
            if(AdminList.get(i).getUserName().equals(Id) && AdminList.get(i).getPassword().equals(pass)){
                flag=true;
            }
        }
        return flag;
    }
    @RequestMapping(path="addpendingdrivers",method = POST)
    public void addPendingDrivers(People driver)
    {

        PendingDrivers.add(driver);
    }
    @RequestMapping(path="verify",method = POST)
    public boolean verify(int p) throws IOException
    {
        ((Driver)PendingDrivers.get(p-1)).setStatus(true);

        PendingDrivers.remove(p-1);
        return true;
    }
    @RequestMapping(path="listpendingdrivers",method = GET)
    public void ListPendingDrivers()
    {

        if(PendingDrivers.size()==0)
        {

            flag=false;
        }
        else
        {
            flag=true;
            for(int i=0;i<PendingDrivers.size();i++)
            {
                System.out.println(i+1 +"."+"Name :" + PendingDrivers.get(i).getUserName()+"\n"+"Driving License: "+PendingDrivers.get(i).getDrivingLicense()+"\n"+"National Id: "+PendingDrivers.get(i).getNationalID()+"\n"+"Email: "+PendingDrivers.get(i).getEmail()+"\n"+"Phone: "+PendingDrivers.get(i).getNumber());
            }
        }
    }

    public boolean checkPendingList(){
        if(PendingDrivers.size()==0)
        {
            flag=false;
        }
        else
            flag= true;
        return flag;
    }
}