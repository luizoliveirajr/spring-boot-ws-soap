package com.luizmarcelo.customersadministration.endpoint;

import com.luizmarcelo.customersadministration.bean.Customer;
import com.luizmarcelo.customersadministration.customers.*;
import com.luizmarcelo.customersadministration.exceptions.CustomerNotFoundException;
import com.luizmarcelo.customersadministration.helper.Status;
import com.luizmarcelo.customersadministration.service.CustomerDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CustomerDetailEndpoint {

    private final CustomerDetailService customerDetailService;

    public CustomerDetailEndpoint(CustomerDetailService customerDetailService) {
        this.customerDetailService = customerDetailService;
    }

    @PayloadRoot(namespace = "http://luizmarcelo.com/customersadministration/customers", localPart = "GetCustomerDetailRequest")
    @ResponsePayload
    public GetCustomerDetailResponse processaCustomerDetialRequest(@RequestPayload GetCustomerDetailRequest request) throws Exception {
        Customer customer = customerDetailService.findById(request.getId());
        if(customer == null){
            throw new CustomerNotFoundException("Invalid Customer id " + request.getId());
        }
        return convertGetCustomerDetailResponse(customer);
    }

    private GetCustomerDetailResponse convertGetCustomerDetailResponse(Customer customer){
        GetCustomerDetailResponse response = new GetCustomerDetailResponse();
        response.setCustomerDetail(convertToCustomerDetail(customer));
        return response;
    }

    private CustomerDetail convertToCustomerDetail(Customer customer){
        CustomerDetail customerDetail = new CustomerDetail();
        BeanUtils.copyProperties(customer,customerDetail);
        return customerDetail;
    }

    @PayloadRoot(namespace = "http://luizmarcelo.com/customersadministration/customers", localPart = "GetAllCustomerDetailRequest")
    @ResponsePayload
    public GetAllCustomerDetailResponse processGetAllCustomerDetailRequest(@RequestPayload GetAllCustomerDetailRequest request){
        List<Customer> customers = customerDetailService.findAll();
        return this.convertToGetAllCustomerResponse(customers);
    }

    private GetAllCustomerDetailResponse convertToGetAllCustomerResponse(List<Customer> customers){
        GetAllCustomerDetailResponse response = new GetAllCustomerDetailResponse();
        customers.forEach(customer -> response.getCustomerDetails().add(convertToCustomerDetail(customer)));
        return response;
    }

    @PayloadRoot(namespace = "http://luizmarcelo.com/customersadministration/customers", localPart = "DeleteCustomerRequest")
    @ResponsePayload
    public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest request){
        DeleteCustomerResponse response = new DeleteCustomerResponse();
        response.setStatus(convertStatusSoap(customerDetailService.deleteById(request.getId())));
        return response;
    }

    private com.luizmarcelo.customersadministration.customers.Status convertStatusSoap(Status statusService){
        if(statusService == Status.FAILURE){
            return com.luizmarcelo.customersadministration.customers.Status.FAILURE;
        }
        return com.luizmarcelo.customersadministration.customers.Status.SUCCESS;
    }
}
