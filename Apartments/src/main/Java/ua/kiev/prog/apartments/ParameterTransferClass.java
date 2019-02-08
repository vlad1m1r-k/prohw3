package ua.kiev.prog.apartments;

public class ParameterTransferClass {
    private String city;
    private String area;
    private String street;
    private Integer spaceFrom;
    private Integer spaceTo;
    private Integer roomsFrom;
    private Integer roomsTo;
    private Integer priceFrom;
    private Integer priceTo;

    public ParameterTransferClass() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getSpaceFrom() {
        return spaceFrom;
    }

    public void setSpaceFrom(Integer spaceFrom) {
        this.spaceFrom = spaceFrom;
    }

    public Integer getSpaceTo() {
        return spaceTo;
    }

    public void setSpaceTo(Integer spaceTo) {
        this.spaceTo = spaceTo;
    }

    public Integer getRoomsFrom() {
        return roomsFrom;
    }

    public void setRoomsFrom(Integer roomsFrom) {
        this.roomsFrom = roomsFrom;
    }

    public Integer getRoomsTo() {
        return roomsTo;
    }

    public void setRoomsTo(Integer roomsTo) {
        this.roomsTo = roomsTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public boolean isDataPresent() {
        return city != null || area != null || street != null || spaceFrom != null || spaceTo != null || roomsFrom != null || roomsTo != null || priceFrom != null || priceTo != null;
    }
}
