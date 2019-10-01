package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;


//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService = new CustomerServiceImpl();

    @GetMapping("/")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customers", new Customer());
        return "create";
    }

    @PostMapping("/create")
    String createCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        return "create";
    }
//
//    @PostMapping("/create")
//    public String createCustomer(Customer customer, Model model) {
//
//        int id = (int) (Math.random() * 10000);
//        if (customer.getId() == null) {
//            customer.setId(id);
//            customer.setName(customer.getName());
//            customer.setEmail(customer.getEmail());
//            customer.setAddress(customer.getAddress());
//        }
//        model.addAttribute("message", "New customer was created!");
//
//        return "create";
//    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        customerService.save(customer);
        model.addAttribute("customers", customerService.findAll());
        return "edit";
    }


//    @PostMapping("/edit/{id}")
//    public String updateCustomer(Customer customer, Model model) {
//        customerService.save(customer);
//        model.addAttribute("message", "da sua thanh cong!");
//        return "edit";
//    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCustomer(Model model, @PathVariable int id) {
        customerService.remove(id);
        model.addAttribute("customers", customerService.findAll());
        return "list";
    }

    @GetMapping("/view/{id}")
    public String viewCustomer(@PathVariable int id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "view";
    }
}

//
//    private void viewCustomer(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Customer customer = this.customerService.findById(id);
//        RequestDispatcher dispatcher;
//        if(customer == null){
//            dispatcher = request.getRequestDispatcher("error-404.jsp");
//        } else {
//            request.setAttribute("customer", customer);
//            dispatcher = request.getRequestDispatcher("customer/view.jsp");
//        }
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "create":
//                createCustomer(req, resp);
//                break;
//            case "edit":
//                updateCustomer(req, resp);
//                break;
//            case "delete":
//                deleteCustomer(req, resp);
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            case "create":
//                showCreateForm(req, resp);
//                break;
//            case "edit":
//                showEditForm(req, resp);
//                break;
//            case "delete":
//                showDeleteForm(req, resp);
//                break;
//            case "view":
//                viewCustomer(req, resp);
//                break;
//            default:
//                listCustomers(req, resp);
//                break;
//        }
//    }
//}
