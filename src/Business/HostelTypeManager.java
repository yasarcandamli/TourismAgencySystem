package Business;

import Core.Helper;
import Dao.HostelTypeDao;
import Entity.HostelType;

import java.util.ArrayList;

public class HostelTypeManager {
    private final HostelTypeDao hostelTypeDao;

    public HostelTypeManager() {
        this.hostelTypeDao = new HostelTypeDao();
    }

    public ArrayList<HostelType> findAll() {
        return hostelTypeDao.findAll();
    }

    public boolean add(HostelType hostelType) {
        if (hostelType.getHotelId() != 0) {
            Helper.showMessage("error");
        }
        return this.hostelTypeDao.add(hostelType);
    }

    public boolean update(HostelType hostelType) {
        if (this.getById(hostelType.getHostelTypeId()) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hostelTypeDao.update(hostelType);
    }

    public boolean delete(int hotelId) {
        if (this.getById(hotelId) == null) {
            Helper.showMessage("notFound");
            return false;
        }
        return this.hostelTypeDao.delete(hotelId);
    }

    public HostelType getById(int selectHostelTypeId) {
        return this.hostelTypeDao.getById(selectHostelTypeId);
    }

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