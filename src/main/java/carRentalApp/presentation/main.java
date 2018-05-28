package carRentalApp.presentation;

import carRentalApp.business.service.GoogleGeocoderService;
import carRentalApp.business.service.customer.CustomerService;
import carRentalApp.data.repository.CarRepository;
import carRentalApp.data.repository.CustomerRepository;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocoderGeometry;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;

public class main {


    public static void main(String[] args) throws IOException {
        Geocoder geocoder = new Geocoder();
        GoogleGeocoderService googleGeocoderService = new GoogleGeocoderService(geocoder);

        GeocoderGeometry geocoderGeometry = googleGeocoderService.locationToCoordinate("Paris");

        System.out.println(geocoderGeometry.getLocation().getLat());
        System.out.println(geocoderGeometry.getLocation().getLng());
    }
}
