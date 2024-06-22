package Entity;

public class HostelType {
    private int hostelTypeId;
    private int hotelId;
    private String hostelType;

    public HostelType() {
    }

    public HostelType(int hostelTypeId, int hotelId, String hostel) {
        this.hostelTypeId = hostelTypeId;
        this.hotelId = hotelId;
        this.hostelType = hostel;
    }

    public int getHostelTypeId() {
        return hostelTypeId;
    }

    public void setHostelTypeId(int hostelTypeId) {
        this.hostelTypeId = hostelTypeId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }
}
