package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TravelPlan.
 */
@Entity
@Table(name = "travel_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TravelPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "travel_name")
    private String travelName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "airline_ticket_number")
    private String airlineTicketNumber;

    @Column(name = "hotel_booking_number")
    private String hotelBookingNumber;

    @Column(name = "car_booking_number")
    private String carBookingNumber;

    @ManyToOne
    private AirlineCompany airlineCompany;

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private RentalCarCompany rentalCarCompany;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TravelPlan id(Long id) {
        this.id = id;
        return this;
    }

    public String getTravelName() {
        return this.travelName;
    }

    public TravelPlan travelName(String travelName) {
        this.travelName = travelName;
        return this;
    }

    public void setTravelName(String travelName) {
        this.travelName = travelName;
    }

    public String getUserName() {
        return this.userName;
    }

    public TravelPlan userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public TravelPlan userEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public TravelPlan startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public TravelPlan endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAirlineTicketNumber() {
        return this.airlineTicketNumber;
    }

    public TravelPlan airlineTicketNumber(String airlineTicketNumber) {
        this.airlineTicketNumber = airlineTicketNumber;
        return this;
    }

    public void setAirlineTicketNumber(String airlineTicketNumber) {
        this.airlineTicketNumber = airlineTicketNumber;
    }

    public String getHotelBookingNumber() {
        return this.hotelBookingNumber;
    }

    public TravelPlan hotelBookingNumber(String hotelBookingNumber) {
        this.hotelBookingNumber = hotelBookingNumber;
        return this;
    }

    public void setHotelBookingNumber(String hotelBookingNumber) {
        this.hotelBookingNumber = hotelBookingNumber;
    }

    public String getCarBookingNumber() {
        return this.carBookingNumber;
    }

    public TravelPlan carBookingNumber(String carBookingNumber) {
        this.carBookingNumber = carBookingNumber;
        return this;
    }

    public void setCarBookingNumber(String carBookingNumber) {
        this.carBookingNumber = carBookingNumber;
    }

    public AirlineCompany getAirlineCompany() {
        return this.airlineCompany;
    }

    public TravelPlan airlineCompany(AirlineCompany airlineCompany) {
        this.setAirlineCompany(airlineCompany);
        return this;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public Hotel getHotel() {
        return this.hotel;
    }

    public TravelPlan hotel(Hotel hotel) {
        this.setHotel(hotel);
        return this;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RentalCarCompany getRentalCarCompany() {
        return this.rentalCarCompany;
    }

    public TravelPlan rentalCarCompany(RentalCarCompany rentalCarCompany) {
        this.setRentalCarCompany(rentalCarCompany);
        return this;
    }

    public void setRentalCarCompany(RentalCarCompany rentalCarCompany) {
        this.rentalCarCompany = rentalCarCompany;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelPlan)) {
            return false;
        }
        return id != null && id.equals(((TravelPlan) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TravelPlan{" +
            "id=" + getId() +
            ", travelName='" + getTravelName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", userEmail='" + getUserEmail() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", airlineTicketNumber='" + getAirlineTicketNumber() + "'" +
            ", hotelBookingNumber='" + getHotelBookingNumber() + "'" +
            ", carBookingNumber='" + getCarBookingNumber() + "'" +
            "}";
    }
}
