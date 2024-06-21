package Entity;

public class HostelType {
    private int hostelTypeId;
    private int hotelId;
    private HostelTypes hostelType;

    public enum HostelTypes {
        Ultra("Ultra All Inclusive"),
        All("All Inclusive"),
        Breakfast("Room Breakfast"),
        Full("Full Board"),
        Half("Half Board"),
        Only("Bed Only"),
        Full_Credit("Full credit excluding alcohol");

        private final String hostelType;
        HostelTypes(String hostelType){
            this.hostelType = hostelType;
        }

        public String getHostelType(){
            return this.name();
        }
    }

    public HostelType() {
    }

    public HostelType(int hostelTypeId, int hotelId, HostelTypes hostel) {
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

    public HostelTypes getHostelType() {
        return hostelType;
    }

    public void setHostelType(HostelTypes hostelType) {
        this.hostelType = hostelType;
    }
}
