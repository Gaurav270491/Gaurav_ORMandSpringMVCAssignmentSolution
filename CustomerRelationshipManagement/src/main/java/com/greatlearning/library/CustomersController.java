package com.greatlearning.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	private CustomerService customerService;



	// add mapping for "/list"

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {

		
		List<Customer> theCustomers = customerService.findAll();

		// add to the spring model
		theModel.addAttribute("Customers", theCustomers);

		return "list-Customers";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			Model theModel) {

		
		Customer theCustomer = customerService.findById(theId);


		
		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "Customer-form";			
	}


	@PostMapping("/save")
	public String saveCustomer(@RequestParam("id") int id,
			@RequestParam("fname") String fname,@RequestParam("lname") String lname,@RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if(id!=0)
		{
			theCustomer=customerService.findById(id);
			theCustomer.setFname(fname);
			theCustomer.setLname(lname);
			theCustomer.setEmail(email);
		}
		else
			theCustomer=new Customer(fname, lname, email);
		// save the Book
		customerService.save(theCustomer);


		// use a redirect to prevent duplicate submissions
		return "redirect:/customers/list";

	}


	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// delete the Book
		customerService.deleteById(theId);

		
		return "redirect:/customers/list";

	}


	
}


















