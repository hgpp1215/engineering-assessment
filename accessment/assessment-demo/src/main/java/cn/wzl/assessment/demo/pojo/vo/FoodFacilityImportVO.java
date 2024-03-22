package cn.wzl.assessment.demo.pojo.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
public class FoodFacilityImportVO {

    /**
     * location ID
     */
    @ExcelProperty("locationid")
    private Integer locationId;
    /**
     * applicant
     */
    @ExcelProperty("Applicant")
    private String applicant;
    /**
     * FacilityType
     */
    @ExcelProperty("FacilityType")
    private String facilityType;
    /**
     * cnn
     */
    @ExcelProperty("cnn")
    private String cnn;
    /**
     * LocationDescription
     */
    @ExcelProperty("LocationDescription")
    private String locationDescription;
    /**
     * Address
     */
    @ExcelProperty("Address")
    private String address;
    /**
     * blocklot
     */
    @ExcelProperty("blocklot")
    private String blockLot;
    /**
     * block
     */
    @ExcelProperty("block")
    private String block;
    /**
     * lot
     */
    @ExcelProperty("lot")
    private String lot;
    /**
     * permit
     */
    @ExcelProperty("permit")
    private String permit;
    /**
     * status: -1-EXPIRED, 1-APPROVED, 2-ISSUED, 3-REQUESTED, 4-SUSPEND
     */
    @ExcelProperty("Status")
    private String status;
    /**
     * x
     */
    @ExcelProperty("FoodItems")
    private String foodItems;
    /**
     * x
     */
    @ExcelProperty("X")
    private Integer locationX;
    /**
     * y
     */
    @ExcelProperty("Y")
    private Integer locationY;
    /**
     * latitude
     */
    @ExcelProperty("Latitude")
    private String latitude;
    /**
     * longitude
     */
    @ExcelProperty("Longitude")
    private String longitude;
    /**
     * schedule
     */
    @ExcelProperty("Schedule")
    private String schedule;
    /**
     * dayshours
     */
    @ExcelProperty("dayshours")
    private String dayshours;
    /**
     * NOISent
     */
    @ExcelProperty("NOISent")
    private String noiSent;
    /**
     * Approved
     */
    @ExcelProperty("Approved")
    private String approvedDate;
    /**
     * NOISent
     */
    @ExcelProperty("Received")
    private String received;
    /**
     * ExpirationDate
     */
    @ExcelProperty("ExpirationDate")
    private String expirationDate;
    /**
     * PriorPermit
     */
    @ExcelProperty("PriorPermit")
    private Boolean priorPermit;
    /**
     * Location
     */
    @ExcelProperty("Location")
    private String location;
    /**
     * Fire Prevention Districts
     */
    @ExcelProperty("Fire Prevention Districts")
    private Integer firePreventionDistricts;
    /**
     * Police Districts
     */
    @ExcelProperty("Police Districts")
    private Integer policeDistricts;
    /**
     * Supervisor Districts
     */
    @ExcelProperty("Supervisor Districts")
    private Integer supervisorDistricts;
    /**
     * Zip Codes
     */
    @ExcelProperty("Zip Codes")
    private String zipCodes;
    /**
     * Neighborhoods(old)
     */
    @ExcelProperty("Neighborhoods (old)")
    private String neighborhoods;

    @ExcelProperty("Errors")
    private String errorText;


    /**
     * Fields NOT exists in Excel file or DB table
     */

    @ExcelIgnore
    private List<FoodsVO> foodsVOS;
    @ExcelIgnore
    private List<DayHourVO> dayHoursVOS;
    @ExcelIgnore
    private List<String> errors = new ArrayList<>();
}
