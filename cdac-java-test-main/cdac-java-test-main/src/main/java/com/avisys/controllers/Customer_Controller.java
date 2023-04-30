package com.avisys.cim.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //RESTful controller
@RequestMapping("/api/customers") // specify the base URL
@CrossOrigin(origins = "*")
public class Customer_Controller {

    @Autowired // inject the CustomerRepository instance into this controller.
    private CustomerRepository customerRepository;

    
    //getAllCustomers() method retrieves all customers from the database by calling the findAll() method of the customerRepository.
    //Then it maps the retrieved customers to CustomerResponse objects by calling the mapCustomersToResponses() method and returns the list of CustomerResponse objects as a JSON array.
    @GetMapping("/")
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return mapCustomersToResponses(customers);
    }

    //searchCustomers() method allows clients to search for customers by their first name, last name, or mobile number.
    @GetMapping("/search")
    public List<CustomerResponse> searchCustomers(
    		//@RequestParam annotation is used to specify the query parameters for the search
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "mobileNumber", required = false) String mobileNumber
    ) {
    	//The method calls the search() method of the customerRepository to retrieve the customers that match the search criteria, and then maps the retrieved customers to CustomerResponse objects using the mapCustomersToResponses() method.
        List<Customer> customers = customerRepository.search(firstName, lastName, mobileNumber);
        return mapCustomersToResponses(customers);
    }

    
  // it is a private helper method that maps a list of Customer objects to a list of CustomerResponse objects. 
    //It iterates through the list of Customer objects, creates a new CustomerResponse object for each Customer object, and adds it to a new list.
    //and returns the new list of CustomerResponse objects.
    private List<CustomerResponse> mapCustomersToResponses(List<Customer> customers) {
        List<CustomerResponse> responses = new ArrayList<>();
        for (Customer customer : customers) {
            responses.add(new CustomerResponse(customer.getFirstName(), customer.getLastName(), customer.getMobileNumber()));
        }
        return responses;
    }
    //When a client sends a request to the REST API endpoint associated with this method, Spring Boot will automatically convert the returned CustomerResponse objects to JSON format before sending them back to the client.
}
