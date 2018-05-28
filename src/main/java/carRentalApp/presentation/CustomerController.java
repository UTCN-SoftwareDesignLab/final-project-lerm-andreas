package carRentalApp.presentation;


import carRentalApp.business.dto.CarRentalDetailsDTO;
import carRentalApp.business.service.GoogleGeocoderService;
import carRentalApp.business.service.car.CarService;
import carRentalApp.business.service.carrentaldetails.CarRentalDetailsService;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocoderGeometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CarService carService;

    @Autowired
    CarRentalDetailsService carRentalDetailsService;

    @Autowired
    public CustomerController(final CarService carService,final CarRentalDetailsService carRentalDetailsService){
        this.carService = carService;
        this.carRentalDetailsService = carRentalDetailsService;
    }

    @RequestMapping(value="/cars", method = RequestMethod.GET)
    public ModelAndView getCarByVin(@RequestParam("vin") String vin, Model model){

        model.addAttribute("carRentalDetails", new CarRentalDetailsDTO());

        Car car =  carService.getCarByVin(vin);

        ModelAndView mav = new ModelAndView("emailForm");

        mav.addObject("car", car);

        return mav;
    }
    @RequestMapping(value="/emailSuccess", method = RequestMethod.GET)
    public ModelAndView generateEmail(@RequestParam("vin") String vin,@ModelAttribute("carRentalDetails") @Valid CarRentalDetailsDTO carRentalDetailsDTO) throws IOException {
        Car car = carService.getCarByVin(vin);

        carRentalDetailsDTO.setCarBrand(car.getBrand());

        carRentalDetailsDTO.setCarModel(car.getModel());

        CarRentalDetails carRentalDetails = carRentalDetailsService.createCarRentalDetails(carRentalDetailsDTO);

        carRentalDetailsService.generateEmail(carRentalDetailsService.generateEmailContent(carRentalDetailsDTO));

        System.out.println(carRentalDetailsService.generateEmailContent(carRentalDetailsDTO));

        ModelAndView mav = new ModelAndView("emailSuccess");

        mav.addObject("car", car);

        return mav;
    }



    @RequestMapping(value="/maps", method = RequestMethod.GET)
    public ModelAndView getMapMarked(@RequestParam("vin") String vin,@ModelAttribute("carRentalDetails") @Valid CarRentalDetailsDTO carRentalDetailsDTO) throws IOException {

        Car car =  carService.getCarByVin(vin);

        carRentalDetailsDTO.setCarBrand(car.getBrand());

        carRentalDetailsDTO.setCarModel(car.getModel());

        CarRentalDetails carRentalDetails = carRentalDetailsService.createCarRentalDetails(carRentalDetailsDTO);

        Geocoder geocoder = new Geocoder();
        GoogleGeocoderService googleGeocoderService = new GoogleGeocoderService(geocoder);

        GeocoderGeometry startingLocation = googleGeocoderService.locationToCoordinate(carRentalDetails.getStartingLocation());

        GeocoderGeometry finalLocation = googleGeocoderService.locationToCoordinate(carRentalDetails.getFinalLocation());

        ModelAndView mav = new ModelAndView("markedMap");

        mav.addObject("startingLocation", startingLocation);

        mav.addObject("finalLocation", finalLocation);

        return mav;
    }

    @GetMapping("/carsView")
    public ModelAndView carsView() {

        List<Car> carDtoList = carService.getAll();

        System.out.println(carDtoList.get(0).getImage());

        ModelAndView mav = new ModelAndView("carsView");

        mav.addObject("carDtoList", carDtoList);

        return mav;
    }
}
