package Entity;

public class Reservation {
    private int reservationId;
    private int roomId;
    private String customerName;
    private String customerIdentityNumber;
    private String customerPhoneNumber;
    private String customerEmail;
    private String checkInDate;
    private String checkOutDate;
    private int adultNumber;
    private int childNumber;
    private String customerNote;
    private int totalPrice;

    public Reservation() {
    }

    public Reservation(int reservationId, int roomId, String customerName, String customerIdentityNumber, String customerPhoneNumber, String customerEmail, String checkInDate, String checkOutDate, int adultNumber, int childNumber, String customerNote, int totalPrice) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.customerName = customerName;
        this.customerIdentityNumber = customerIdentityNumber;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adultNumber = adultNumber;
        this.childNumber = childNumber;
        this.customerNote = customerNote;
        this.totalPrice = totalPrice;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdentityNumber() {
        return customerIdentityNumber;
    }

    public void setCustomerIdentityNumber(String customerIdentityNumber) {
        this.customerIdentityNumber = customerIdentityNumber;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
