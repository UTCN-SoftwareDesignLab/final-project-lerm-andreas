package carRentalApp.data.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String identityCardNumber;
    private String email;
    private Date dateOfBirth;
    private String address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public Customer(){}

    public Customer(String name, String identityCardNumber, String email, Date dateOfBirth, String address,Car car) {
        this.name = name;
        this.identityCardNumber = identityCardNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        //this.car = car;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
*/

    public Car getCar() {
        return car;
    }

    public void setCars(Car car) {
        this.car = car;
    }

   /* @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identityCardNumber='" + identityCardNumber + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", car=" + car +
                '}';
    }*/
}
