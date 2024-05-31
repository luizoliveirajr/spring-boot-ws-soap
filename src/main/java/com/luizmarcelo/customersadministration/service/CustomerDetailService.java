package com.luizmarcelo.customersadministration.service;

import com.luizmarcelo.customersadministration.bean.Customer;
import com.luizmarcelo.customersadministration.helper.Status;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerDetailService {
    public static List<Customer> customers = new ArrayList<>();

    static{
        Customer customer1 = new Customer(1, "Bob Construtor", "999999", "asokdaos@hotmail.com");
        customers.add(customer1);

        Customer customer2 = new Customer(2, "Zeca urubu", "999999", "1111@hotmail.com");
        customers.add(customer2);

        Customer customer3 = new Customer(3, "Shrek", "999999", "asokdaoasdasds@hotmail.com");
        customers.add(customer3);

        Customer customer4 = new Customer(4, "Pica Pau", "999999", "picapau@hotmail.com");
        customers.add(customer4);
    }

    public Customer findById(int id){
        Optional<Customer> customerOptional = customers.stream().filter(customer -> customer.getId() == id).findAny();
        return customerOptional.orElse(null);
    }


    public List<Customer> findAll(){
        return customers;
    }

    public Status deleteById(int id){
        Customer customer = findById(id);
        if(customer != null){
            customers.remove(customer);
            return Status.SUCCESS;
        }
        return Status.FAILURE;
    }
}
