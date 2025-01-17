package Business;

import Core.Helper;
import Dao.HostelTypeDao;
import Entity.HostelType;
import Entity.Hotel;

import java.util.ArrayList;

public class HostelTypeManager {
    private final HostelTypeDao hostelTypeDao;

    public HostelTypeManager() {
        this.hostelTypeDao = new HostelTypeDao();
    }

    // Fetches all hostel types
    public ArrayList<HostelType> findAll() {
        return hostelTypeDao.findAll();
    }

    // Gets hostel types according to hotel ID
    public ArrayList<HostelType> findAllForTable(int hotelId) {
        return hostelTypeDao.findAllForTable(hotelId);
    }

    // Adds new hostel type
    public boolean add(Hotel hotel, String hostelType) {
        if (this.getByName(hostelType, hotel.getHotelId()) != null){
            Helper.showMessage("Already Exist!");
            return false;
        }
        return this.hostelTypeDao.add(hotel, hostelType);
    }

    // Updates the hostel type
//    public boolean update(HostelType hostelType) {
//        if (this.getById(hostelType.getHostelTypeId()) == null) {
//            Helper.showMessage("notFound");
//            return false;
//        }
//        return this.hostelTypeDao.update(hostelType);
//    }

    // Deletes the hostel type
    public boolean delete(int hostelTypeId) {
        if (this.getById(hostelTypeId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hostelTypeDao.delete(hostelTypeId);
    }

    public boolean deleteByHotelId(int hotelId) {
        return this.hostelTypeDao.deleteByHotelId(hotelId);
    }

    // Gets the type of hostel with ID
    public HostelType getById(int selectHostelTypeId) {
        return this.hostelTypeDao.getById(selectHostelTypeId);
    }

    // Gets the type of hostel with name
    public HostelType getByName(String selectHostelTypeName, int selectHotelId) {
        return this.hostelTypeDao.getByName(selectHostelTypeName, selectHotelId);
    }

    // Gets the board type data for the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<HostelType> hostelTypeList) { //Create objects as many as the number of columns
        ArrayList<Object[]> hostelTypeObjectList = new ArrayList<>();

        for (HostelType object : hostelTypeList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = object.getHostelTypeId();
            rowObject[i++] = object.getHotelId();
            rowObject[i++] = object.getHostelType();
            hostelTypeObjectList.add(rowObject);
        }
        return hostelTypeObjectList;
    }

//    public ArrayList<HostelType> filterForTable(HostelType.HostelTypes hostelTypes) {
//        String select = "SELECT * FROM public.hostel_type";
//        ArrayList<String> whereList = new ArrayList<>();
//
//        if (hostelTypes != null) {
//            whereList.add("hostel_type = '" + hostelTypes.toString() + "'");
//        }
//        String whereString = String.join(" AND ", whereList);
//        String query = select;
//        if (!whereString.isEmpty()) {
//            query += " WHERE " + whereString;
//        }
//        return this.hostelTypeDao.selectByQuery(query);
//    }
}
