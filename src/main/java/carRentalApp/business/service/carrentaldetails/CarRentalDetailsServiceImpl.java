package carRentalApp.business.service.carrentaldetails;

import carRentalApp.Messages.TLSEmail;
import carRentalApp.business.dto.CarRentalDetailsDTO;
import carRentalApp.business.service.GoogleGeocoderService;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;
import carRentalApp.data.entity.Customer;
import carRentalApp.data.entity.User;
import carRentalApp.data.repository.CarRentalDetailsRepository;
import carRentalApp.data.repository.CarRepository;
import carRentalApp.data.repository.CustomerRepository;
import carRentalApp.data.repository.UserRepository;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocoderGeometry;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;

public class CarRentalDetailsServiceImpl implements CarRentalDetailsService {


    private CarRentalDetailsRepository carRentalDetailsRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CarRentalDetailsServiceImpl(final CarRentalDetailsRepository carRentalDetailsRepository,final CarRepository carRepository,final CustomerRepository customerRepository){
        this.carRentalDetailsRepository = carRentalDetailsRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public CarRentalDetails createCarRentalDetails(CarRentalDetailsDTO carRentalDetailsDTO) {

        Car car = carRepository.findByBrandAndAndModel(carRentalDetailsDTO.getCarBrand(),carRentalDetailsDTO.getCarModel());
        Customer customer = customerRepository.findByIdentityCardNumber(carRentalDetailsDTO.getIdentityCardNumber());

        CarRentalDetails carRentalDetails = new CarRentalDetails(car,customer,carRentalDetailsDTO.getStartingLocation(),carRentalDetailsDTO.getFinalLocation());
        return carRentalDetailsRepository.save(carRentalDetails);
    }

    @Override
    public CarRentalDetails findByCar(Car car) {
        return carRentalDetailsRepository.findByCar(car);
    }

    @Override
    public double calculateDistance(String startingLocation, String finalLocation) throws IOException {
        Geocoder geocoder = new Geocoder();
        GoogleGeocoderService googleGeocoderService = new GoogleGeocoderService(geocoder);
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

        GeocoderGeometry iLocation = googleGeocoderService.locationToCoordinate(startingLocation);
        GeocoderGeometry fLocation = googleGeocoderService.locationToCoordinate(finalLocation);

        double initLatitude = iLocation.getLocation().getLat().doubleValue();
        double initialLongitude = iLocation.getLocation().getLng().doubleValue();

        double finalLatitude = fLocation.getLocation().getLat().doubleValue();
        double finalLongitude = fLocation.getLocation().getLng().doubleValue();

        double lat1 = Math.toRadians(initLatitude);
        double lon1 = Math.toRadians(initialLongitude);
        double lat2 = Math.toRadians(finalLatitude);
        double lon2 = Math.toRadians(finalLongitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles*1.85;
        return statuteMiles;

    }

    @Override
    public String generateEmailContent(CarRentalDetailsDTO carRentalDetailsDTO) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear Mr/Mrs " + " " + carRentalDetailsDTO.getName() + ",");
        stringBuilder.append('\n');
        stringBuilder.append("Thank you for choosing our rental company! " + " ");
        stringBuilder.append("Here are your renting details: ");
        stringBuilder.append('\n');
        stringBuilder.append("Personal email: " + " " + carRentalDetailsDTO.getEmail());
        stringBuilder.append('\n');
        stringBuilder.append("Date of birth: " + carRentalDetailsDTO.getDateOfBirth()+ '\n');
        stringBuilder.append('\n');
        stringBuilder.append("Address: "+ carRentalDetailsDTO.getAddress()+ '\n');
        stringBuilder.append('\n');
        stringBuilder.append("You chose to rent an "  + carRentalDetailsDTO.getCarBrand() + ", " + carRentalDetailsDTO.getCarModel());

        //
        stringBuilder.append("Based on your chosen starting point and destination, it seems you are going to travel" +
                calculateDistance(carRentalDetailsDTO.getStartingLocation(),carRentalDetailsDTO.getFinalLocation()) + "kilometers");



        stringBuilder.append("The price for the chosen car is 10$/day" + '\n');
        stringBuilder.append('\n');
        stringBuilder.append("We wish you a great journey!, ");
        stringBuilder.append('\n');
        stringBuilder.append("The car rental team");

        return stringBuilder.toString();
    }

    @Override
    public void generateEmail(String content) {
        TLSEmail tlsEmail = new TLSEmail();
        tlsEmail.sendEmail(content);
    }
}
