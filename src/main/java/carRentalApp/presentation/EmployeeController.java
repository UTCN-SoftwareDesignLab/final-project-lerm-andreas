package carRentalApp.presentation;

import carRentalApp.business.dto.CarDTO;
import carRentalApp.business.service.car.CarService;
import carRentalApp.data.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON;

@Controller
public class EmployeeController {

    @Autowired
    CarService carService;

    @Autowired
    public EmployeeController(final CarService carService){
        this.carService =carService;
    }

    @GetMapping("/employee")
    public String getEmployeePage(Model model) {
        return "employeePage";
    }


    @GetMapping("/changeCarStatus")
    public ModelAndView changeCarStatus(Model model) {
        model.addAttribute("car", new CarDTO());
        List<Car> carDtoList = carService.getAll();

        ModelAndView mav = new ModelAndView("changeCarStatus");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }

    @PostMapping("/changeAvailableStatus")
    public ModelAndView changeAvailableStatus(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("changeCarStatus");
            return mav;
        } else {

            carService.changeAvailableStatus(carDTO);

            List<Car> carDtoList = carService.getAll();

            ModelAndView mav = new ModelAndView("changeCarStatus");

            mav.addObject("carDtoList", carDtoList);

            return mav;
        }
    }


    @PostMapping("/changeReturnedStatus")
    public ModelAndView changeReturnedStatus(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("changeCarStatus");
            return mav;
        } else {

            carService.changeReturnedStatus(carDTO);

            List<Car> carDtoList = carService.getAll();

            ModelAndView mav = new ModelAndView("changeCarStatus");

            mav.addObject("carDtoList", carDtoList);

            return mav;
        }
    }




}
