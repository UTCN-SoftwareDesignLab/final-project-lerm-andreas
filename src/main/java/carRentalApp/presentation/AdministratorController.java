package carRentalApp.presentation;

import carRentalApp.Messages.TLSEmail;
import carRentalApp.business.dto.CarDTO;
import carRentalApp.business.dto.CarRentalDetailsDTO;
import carRentalApp.business.dto.CustomerDTO;
import carRentalApp.business.dto.UserDTO;
import carRentalApp.business.service.GoogleGeocoderService;
import carRentalApp.business.service.car.CarService;
import carRentalApp.business.service.carrentaldetails.CarRentalDetailsService;
import carRentalApp.business.service.customer.CustomerService;
import carRentalApp.business.service.user.UserService;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;
import carRentalApp.data.entity.Customer;
import carRentalApp.data.entity.User;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocoderGeometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AdministratorController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarRentalDetailsService carRentalDetailsService;


    @Autowired
    public AdministratorController(final UserService userService, final CarService carService, CustomerService customerService,CarRentalDetailsService carRentalDetailsService) {
        this.userService = userService;
        this.carService = carService;
        this.customerService = customerService;
        this.carRentalDetailsService = carRentalDetailsService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @GetMapping("/carView")
    public String getCarView(Model model) {
        return "carView";
    }

    @GetMapping("/opacity")
    public String getOpacity(Model model) {
        return "opacity";
    }


    @GetMapping("/map")
    public String getMap(Model model) {
        return "mapp";
    }

    @GetMapping("/administrator")
    public String getAdministratorPage(Model model) {
        return "adminPage";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/createUserForm")
    public ModelAndView createUserForm(Model model) {

        model.addAttribute("user", new UserDTO());

        List<User> userDtoList = userService.getAll();

        ModelAndView mav = new ModelAndView("createUserForm");

        mav.addObject("userDtoList1", userDtoList);
        return mav;

    }

    @PostMapping("/createUserForm")
    public ModelAndView createUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("createUserForm");
            return mav;
        } else {

            User user = userService.createUser(userDTO);

            List<User> userDtoList = userService.getAll();

            ModelAndView mav = new ModelAndView("createUserForm");

            mav.addObject("userDtoList1", userDtoList);

            return mav;
        }
    }

    @GetMapping("/deleteUserForm")
    public ModelAndView deleteUserForm(Model model) {

        model.addAttribute("user", new UserDTO());
        List<User> userDtoList = userService.getAll();

        ModelAndView mav = new ModelAndView("deleteUserForm");



        mav.addObject("userDtoList1", userDtoList);

        return mav;

    }

    @PostMapping("/deleteUserForm")
    public ModelAndView deleteUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("deleteUserForm");
            return mav;
        } else {

            userService.deleteUser(userDTO);

            List<User> userDtoList = userService.getAll();

            ModelAndView mav = new ModelAndView("deleteUserForm");



            mav.addObject("userDtoList1", userDtoList);

            return mav;
        }
    }

    @GetMapping("/getUserForm")
    public ModelAndView getUserForm(Model model) {
        model.addAttribute("user", new UserDTO());
        List<User> userDtoList = userService.getAll();

        Car car = carService.getCarByVin("12346");

        ModelAndView mav = new ModelAndView("getUserForm");

        mav.addObject("car", car);

        mav.addObject("userDtoList1", userDtoList);



        return mav;
    }

    @PostMapping("/getUserForm")
    public ModelAndView getUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("getUserForm");
            return mav;
        } else {

            User user = userService.getUser(userDTO);



            List<User> userDtoList = new ArrayList<>();

            userDtoList.add(user);

            ModelAndView mav = new ModelAndView("getUserForm");

            mav.addObject("userDtoList1", userDtoList);

            return mav;
        }
    }

    @GetMapping("/updateUserForm")
    public ModelAndView updateUserForm(Model model) {
        model.addAttribute("user", new UserDTO());
        List<User> userDtoList = userService.getAll();

        ModelAndView mav = new ModelAndView("updateUserForm");

        mav.addObject("userDtoList1", userDtoList);

        return mav;
    }

    @PostMapping("/updateUserForm")
    public ModelAndView updateUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("updateUserForm");
            return mav;
        } else {

            userService.updateUser(userDTO);

            List<User> userDtoList = userService.getAll();

            ModelAndView mav = new ModelAndView("updateUserForm");

            mav.addObject("userDtoList1", userDtoList);

            return mav;
        }
    }

    @GetMapping("/createCarForm")
    public ModelAndView createCarForm(Model model) {
        model.addAttribute("car", new CarDTO());
        List<Car> carDtoList = carService.getAll();

        ModelAndView mav = new ModelAndView("createCarForm");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }

    @PostMapping("/createCarForm")
    public ModelAndView createCar(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("createCarForm");
            return mav;
        } else {

            carService.createCar(carDTO);

            List<Car> carDtoList = carService.getAll();

            ModelAndView mav = new ModelAndView("createCarForm");

            mav.addObject("carDtoList", carDtoList);

            return mav;
        }
    }

    @GetMapping("/deleteCarForm")
    public ModelAndView deleteCarForm(Model model) {
        model.addAttribute("car", new CarDTO());
        List<Car> carDtoList = carService.getAll();

        ModelAndView mav = new ModelAndView("deleteCarForm");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }

    @PostMapping("/deleteCarForm")
    public ModelAndView deleteCar(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("deleteCarForm");
            return mav;
        } else {

            carService.deleteCar(carDTO);

            List<Car> carDtoList = carService.getAll();

            ModelAndView mav = new ModelAndView("deleteCarForm");

            mav.addObject("carDtoList", carDtoList);

            return mav;
        }
    }

    @GetMapping("/getCarForm")
    public ModelAndView getCarForm(Model model) {
        model.addAttribute("car", new CarDTO());
        List<Car> carDtoList = carService.getAll();

        ModelAndView mav = new ModelAndView("getCarForm");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }

    @PostMapping("/getCarForm")
    public ModelAndView getCar(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("getCarForm");
            return mav;
        } else {

            Car car = carService.getCar(carDTO);

            List<Car> carDtoList = new ArrayList<>();

            carDtoList.add(car);

            ModelAndView mav = new ModelAndView("getCarForm");

            mav.addObject("carDtoList", carDtoList);

            return mav;

        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @GetMapping("/createCustomerForm")
    public ModelAndView createCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        List<Car> carDtoList = carService.getAll();
        List<Customer> customerDtoList = customerService.getAll();

        ModelAndView mav = new ModelAndView("createCustomerForm");

        mav.addObject("carDtoList", carDtoList);

        mav.addObject("customerDtoList", customerDtoList);

        return mav;
    }

    @PostMapping("/createCustomerForm")
    public ModelAndView createCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("createCustomerForm");
            return mav;
        } else {
            customerService.createCustomer(customerDTO);

            List<Customer> customerDtoList = customerService.getAll();
            List<Car> carDtoList = carService.getAll();
            ModelAndView mav = new ModelAndView("createCustomerForm");

            mav.addObject("customerDtoList", customerDtoList);
            mav.addObject("carDtoList", carDtoList);
            return mav;
        }
    }

    @GetMapping("/deleteCustomerForm")
    public ModelAndView deleteCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        List<Customer> customerDtoList = customerService.getAll();

        ModelAndView mav = new ModelAndView("deleteCustomerForm");

        mav.addObject("customerDtoList", customerDtoList);

        return mav;
    }

    @PostMapping("/deleteCustomerForm")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("deleteCustomerForm");
            return mav;
        } else {

            customerService.deleteCustomer(customerDTO);

            List<Customer> customerDtoList = customerService.getAll();

            ModelAndView mav = new ModelAndView("deleteCustomerForm");

            mav.addObject("customerDtoList", customerDtoList);

            return mav;
        }
    }

    @GetMapping("/getCustomerForm")
    public ModelAndView getCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        List<Customer> customerDtoList = customerService.getAll();

        ModelAndView mav = new ModelAndView("getCustomerForm");

        mav.addObject("customerDtoList", customerDtoList);

        return mav;
    }

    @PostMapping("/getCustomerForm")
    public ModelAndView getCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("getCustomerForm");

            return mav;
        } else {

            Customer customer = customerService.getCustomer(customerDTO);
            System.out.println(customer.toString());
            List<Customer> customerDtoList = new ArrayList<>();

            customerDtoList.add(customer);

            ModelAndView mav = new ModelAndView("getCustomerForm");

            mav.addObject("customerDtoList", customerDtoList);

            return mav;
        }
    }

    @GetMapping("/updateCustomerForm")
    public ModelAndView updateCustomerForm(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        List<Car> carDtoList = carService.getAll();
        List<Customer> customerDtoList = customerService.getAll();

        ModelAndView mav = new ModelAndView("updateCustomerForm");

        mav.addObject("carDtoList", carDtoList);

        mav.addObject("customerDtoList", customerDtoList);

        return mav;
    }

    @PostMapping("/updateCustomerForm")
    public ModelAndView updateCustomer(@ModelAttribute("customer") @Valid CustomerDTO customerDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("updateCustomerForm");
            return mav;
        } else {
            customerService.updateCustomer(customerDTO);

            List<Customer> customerDtoList = customerService.getAll();
            List<Car> carDtoList = carService.getAll();
            ModelAndView mav = new ModelAndView("updateCustomerForm");

            mav.addObject("customerDtoList", customerDtoList);
            mav.addObject("carDtoList", carDtoList);
            return mav;
        }
    }

    /*@GetMapping("/carsView")
    public ModelAndView carsView() {

        List<Car> carDtoList = carService.getAll();

        System.out.println(carDtoList.get(0).getImage());

        ModelAndView mav = new ModelAndView("carsView");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }*/
}


