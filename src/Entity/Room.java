package Entity;

public class Room {
    private int roomId;
    private int hotelId;
    private int seasonId;
    private int hostelTypeId;
    private String roomType;
    private int bedNumber;
    private int roomArea;
    private int roomNumber;
    private int adultPrice;
    private int childPrice;
    private isThere tv;
    private isThere minibar;
    private isThere gameConsole;
    private isThere safeBox;
    private isThere projection;

    public enum isThere{
        Yes,
        No
    }

    public Room() {
    }

    public Room(int roomId, int hotelId, int seasonId, int hostelTypeId, String roomType, int bedNumber, int roomArea, int roomNumber, int adultPrice, int childPrice, isThere tv, isThere minibar, isThere gameConsole, isThere safeBox, isThere projection) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.seasonId = seasonId;
        this.hostelTypeId = hostelTypeId;
        this.roomType = roomType;
        this.bedNumber = bedNumber;
        this.roomArea = roomArea;
        this.roomNumber = roomNumber;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.tv = tv;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.safeBox = safeBox;
        this.projection = projection;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(int roomArea) {
        this.roomArea = roomArea;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    public isThere getTv() {
        return tv;
    }

    public void setTv(isThere tv) {
        this.tv = tv;
    }

    public isThere getMinibar() {
        return minibar;
    }

    public void setMinibar(isThere minibar) {
        this.minibar = minibar;
    }

    public isThere getGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(isThere gameConsole) {
        this.gameConsole = gameConsole;
    }

    public isThere getSafeBox() {
        return safeBox;
    }

    public void setSafeBox(isThere safeBox) {
        this.safeBox = safeBox;
    }

    public isThere getProjection() {
        return projection;
    }

    public void setProjection(isThere projection) {
        this.projection = projection;
    }
}
