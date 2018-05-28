package carRentalApp.business.dto;

import javax.validation.constraints.Size;

public class CarDTO {


    @Size(min=5,max=5,message = "The vehicle identification number must consist of exactly 5 characters!")
    private String vin;

    @Size(min=3,message = "The brand must consist of at least 3 characters!")
    private String brand;

    private String model;

    private String available;

    private String returned;

    private String image;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
