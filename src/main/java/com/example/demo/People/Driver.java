package com.example.demo.People;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path="api/driver")

public class Driver extends People {
    static Registration driverRegister=new DriverRegister();
    ArrayList<People> driverList= driverRegister.getList();
    ArrayList<String> favAreas = new ArrayList<String>();
    Notifications notification=new Notifications();
    private ArrayList<Ride> availableRequests = new ArrayList<Ride>();

    Ride ride=new Ride();
    Driver drive;
    boolean status=false;
    private float Balance;
    String NationalID;
    String DrivingLicense;

    public Driver()
    {
        Balance=0;
        NationalID="";
        DrivingLicense="";
    }
    public Driver(String id,String pw, String Email,String num,String NationalID,String DrivingLicense)
    {
        super(id,pw,Email,num);
        this.NationalID=NationalID;
        this.DrivingLicense=DrivingLicense;
    }
    public boolean checkRequests(){
        return ride.checkRequests();
    }
    public boolean checkFavRequests(Driver driver){
        return ride.checkFavRequests(driver);
    }
    @RequestMapping(path="Register",method = POST)
    public void register(String id,String pw, String Email,String num,String NationalID,String DrivingLicense) throws IOException {
        Driver driver=new Driver();
        driverRegister.register(driver);
    }
    @RequestMapping(path="listallrequests",method = GET)
    public void listAllRequests(){
        ride.listAllRequests();
    }
    @RequestMapping(path="listfavrequests",method = GET)
    public void listRequests(Driver driver){
        ride.listRequests(driver);
    }
    @RequestMapping(path="addoffer",method = POST)
    public void AddOffer(int index, int cost, Driver driver)
    {
        ride.makeOffer(index, cost, driver);
    }
    public boolean checkActiveRides(Driver driver){
        return ride.ActiveRides(driver);
    }
    public void printActiveRides(Driver driver){
        ride.printActiveRides(driver);
    }
    @Override
    @RequestMapping(path="login",method = POST)
    public boolean signIn(String Id,String Pass)
    {
        boolean flag=false;
        for(int i=0;i<driverList.size();i++){
            if(driverList.get(i).getUserName().equals(Id) && driverList.get(i).getPassword().equals(Pass) ){
                flag=true;
            }
        }
        return flag;
    }

    public boolean checkNotification(){
        return notification.checkDriverNotification();
    }
    @RequestMapping(path="notifications",method = GET)
    public void listNotification(){
        notification.listDriverNotification();
    }
    @RequestMapping(path="endride",method = POST)
    public void endRide(Driver driver){
        ride.end(driver);
    }
    @RequestMapping(path="setbalance",method = POST)
    public void setBalance(float balance) {
        Balance = balance;
    }
    @RequestMapping(path="balance",method = GET)
    public float getBalance(){
        return Balance;
    }
    @RequestMapping(path="addfavarea",method = POST)
    public void addFavArea(String area){
        favAreas.add(area);
    }
    @RequestMapping(path="removefavarea",method = POST)
    public boolean removeFavArea(String area){
        if(!favAreas.remove(area)){
            System.out.println("Area not found");
            return false;
        }
        else
            return true;
    }
    public boolean checkFavArea(Driver driver){
        if(driver.getFavArea().size()==0){
            return false;
        }
        else
            return true;
    }
    public boolean getStatus(){return status;}
    public void setStatus(boolean status){this.status=status;}
    @RequestMapping(path="listfavareas",method = GET)
    public ArrayList<String> getFavArea(){
        return favAreas;
    }
    public String getDrivingLicense()
    {
        return DrivingLicense;
    }
    public String getNationalID()
    {
        return NationalID;
    }

}
