package Entity;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String hotelCity;
    private String hotelDistrict;
    private String hotelAddress;
    private String hotelEmail;
    private String hotelPhoneNumber;
    private String hotelStar;
    private String hotelFacility;

    public Hotel() {
    }

    public Hotel(int hotelId, String hotelName, String hotelCity, String hotelDistrict, String hotelAddress, String hotelEmail, String hotelPhoneNumber, String hotelStar, String hotelFacility) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelCity = hotelCity;
        this.hotelDistrict = hotelDistrict;
        this.hotelAddress = hotelAddress;
        this.hotelEmail = hotelEmail;
        this.hotelPhoneNumber = hotelPhoneNumber;
        this.hotelStar = hotelStar;
        this.hotelFacility = hotelFacility;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelDistrict() {
        return hotelDistrict;
    }

    public void setHotelDistrict(String hotelDistrict) {
        this.hotelDistrict = hotelDistrict;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelPhoneNumber() {
        return hotelPhoneNumber;
    }

    public void setHotelPhoneNumber(String hotelPhoneNumber) {
        this.hotelPhoneNumber = hotelPhoneNumber;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelFacility() {
        return hotelFacility;
    }

    public void setHotelFacility(String hotelFacility) {
        this.hotelFacility = hotelFacility;
    }
}
