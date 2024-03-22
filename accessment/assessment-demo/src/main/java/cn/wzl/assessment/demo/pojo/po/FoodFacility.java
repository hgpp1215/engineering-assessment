package cn.wzl.assessment.demo.pojo.po;

import cn.wzl.assessment.demo.pojo.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("food_facility")
public class FoodFacility extends BaseEntity {

    /**
     * location ID
     */
    private Integer locationId;
    /**
     * applicant
     */
    private String applicant;
    /**
     * FacilityType
     */
    private String facilityType;
    /**
     * cnn
     */
    private String cnn;
    /**
     * LocationDescription
     */
    private String locationDescription;
    /**
     * Address
     */
    private String address;
    /**
     * blocklot
     */
    private String blockLot;
    /**
     * block
     */
    private String block;
    /**
     * lot
     */
    private String lot;
    /**
     * permit
     */
    private String permit;
    /**
     * status: -1-EXPIRED, 1-APPROVED, 2-ISSUED, 3-REQUESTED, 4-SUSPEND
     */
    private Integer status;
    /**
     * x
     */
    private String foodItems;
    /**
     * x
     */
    private Integer locationX;
    /**
     * y
     */
    private Integer locationY;
    /**
     * latitude
     */
    private String latitude;
    /**
     * longitude
     */
    private String longitude;
    /**
     * schedule
     */
    private String schedule;
    /**
     * dayshours
     */
    private String dayshours;
    /**
     * NOISent
     */
    private String noiSent;
    /**
     * Approved
     */
    private String approvedDate;
    /**
     * NOISent
     */
    private String received;
    /**
     * ExpirationDate
     */
    private String expirationDate;
    /**
     * PriorPermit
     */
    private Boolean priorPermit;
    /**
     * Location
     */
    private String location;
    /**
     * Fire Prevention Districts
     */
    private Integer firePreventionDistricts;
    /**
     * Police Districts
     */
    private Integer policeDistricts;
    /**
     * Supervisor Districts
     */
    private Integer supervisorDistricts;
    /**
     * Zip Codes
     */
    private String zipCodes;
    /**
     * Neighborhoods(old)
     */
    private String neighborhoods;

}
