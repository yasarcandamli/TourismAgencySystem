package Entity;

public class Room {
    private int roomId;
    private int hotelId;
    private int seasonId;
    private int hostelTypeId;
    private int bedNumber;
    private String roomType;
    private int remainingRoomNumber;
    private int adultPrice;
    private int childPrice;
    private String roomFeatures;

    public Room() {
    }

    public Room(int roomId, int hotelId, int seasonId, int hostelTypeId, int bedNumber, String roomType, int remainingRoomNumber, int adultPrice, int childPrice, String roomFeatures) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.hostelTypeId = hostelTypeId;
        this.bedNumber = bedNumber;
        this.roomType = roomType;
        this.remainingRoomNumber = remainingRoomNumber;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.roomFeatures = roomFeatures;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getHostelTypeId() {
        return hostelTypeId;
    }

    public void setHostelTypeId(int hostelTypeId) {
        this.hostelTypeId = hostelTypeId;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRemainingRoomNumber() {
        return remainingRoomNumber;
    }

    public void setRemainingRoomNumber(int remainingRoomNumber) {
        this.remainingRoomNumber = remainingRoomNumber;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public String getRoomFeatures() {
        return roomFeatures;
    }

    public void setRoomFeatures(String roomFeatures) {
        this.roomFeatures = roomFeatures;
    }
}
