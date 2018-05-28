package carRentalApp.business.service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;

import java.io.IOException;
import java.util.List;

/**
 * BeanConverter class that allows to transform from a location (String) to a
 * coordinate and vice versa. Uses the Google GeoService, which is responsible
 * of the transformation.
 *
 * @author Gabriel Diaz, 17.02.2015.
 */
public class GoogleGeocoderService {

    private final Geocoder geocoder;

    public GoogleGeocoderService(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public GeocoderGeometry locationToCoordinate(String location) throws IOException {
        GeocoderGeometry coordinate = null;

        if (location != null && !location.isEmpty()) {
            GeocoderRequest request = new GeocoderRequest();
            request.setAddress(location);

            GeocodeResponse response = geocoder.geocode(request);
            if (response.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = response.getResults();

                for (GeocoderResult result : results) {
                    GeocoderGeometry geometry = result.getGeometry();
                    coordinate = geometry;
                    break;
                }
            }
        }

        return coordinate;
    }

    public String coordinateToLocation(GeocoderGeometry coordinate) throws IOException {
        String location = null;

        if (coordinate != null) {
            GeocoderRequest request = new GeocoderRequest();
            request.setLocation(coordinate.getLocation());
            request.setBounds(coordinate.getBounds());

            GeocodeResponse response = geocoder.geocode(request);
            if (response.getStatus() == GeocoderStatus.OK) {
                List<GeocoderResult> results = response.getResults();
                for (GeocoderResult result : results) {
                    location = result.getFormattedAddress();
                    break;
                }
            }
        }

        return location;
    }

}