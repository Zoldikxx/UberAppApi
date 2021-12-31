package com.example.demo.People;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
@RestController
@RequestMapping(path="api/customer")

public class Customer extends People {

    static Registration customerRegister=new CustomerRegister();
    ArrayList<People> customerList= customerRegister.getList();
    static   People customer =new Customer();
    Ride ride=new Ride();
    Notifications notification=new Notifications();
    public Customer(){

        super("","","","");
    }

    public Customer(String id,String pw, String Email,String num) {

        super(id, pw, Email, num);
    }
    @RequestMapping(path="Register",method = POST)
    public void register(String id,String pw, String Email,String num) throws IOException {
        Customer customer=new Customer();
        customerRegister.register(customer);
    }
    @Override
    @RequestMapping(path="signIn",method = POST)
    public boolean signIn(String Id, String Pass) {
        boolean flag=false;
        for(int i=0;i<customerList.size();i++){

            if(customerList.get(i).getUserName().equals(Id) && customerList.get(i).getPassword().equals(Pass) ) {

                flag = true;
            }
        }
        return flag;
    }
    @RequestMapping(path="request",method = POST)
    public void requestRide(Customer customer, String source, String Destination){
        Ride ride=new Ride(customer,source,Destination);
        ride.request(ride);
    }
    public boolean checkNotification(){
        return notification.checkUserNotification();
    }
    public void listNotification() {
        notification.listUserNotification();
    }
    public boolean checkHistory(Customer customer){return ride.checkHistory(customer); }

    @RequestMapping(path="history",method = GET)
    public void History(Customer customer){
        ride.listHistory(customer);
    }
    @RequestMapping(path="rate",method = POST)
    public void rateRide(int index,float rating){
        ride.rating(index,rating);
    }
    @RequestMapping(path="acceptoffer",method = POST)
    public void acceptOffer(int index, Customer customer1){
        ride.acceptOffer(index,customer1);
    }
    @RequestMapping(path="listoffer",method = GET)
    public void listOffer(Customer customer){
        ride.listOffer(customer);
    }
    public boolean checkOffers(Customer customer){return ride.checkforOffer(customer);}

}

