package ua.kiev.prog.apartments;

public class Apartment {
    private String city;
    private String area;
    private String street;
    private Integer build;
    private Integer apartmentNumber;
    private Integer space;
    private Integer rooms;
    private Integer price;

    public Apartment(String city, String area, String street, Integer build, Integer apartmentNumber, Integer space, Integer rooms, Integer price) {
        this.city = city;
        this.area = area;
        this.street = street;
        this.build = build;
        this.apartmentNumber = apartmentNumber;
        this.space = space;
        this.rooms = rooms;
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public Integer getBuild() {
        return build;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public Integer getSpace() {
        return space;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Integer getPrice() {
        return price;
    }
}
