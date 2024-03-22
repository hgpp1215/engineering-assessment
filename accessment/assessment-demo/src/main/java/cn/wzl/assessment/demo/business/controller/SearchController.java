package cn.wzl.assessment.demo.business.controller;

import cn.wzl.assessment.demo.business.service.FacilityService;
import cn.wzl.assessment.demo.pojo.vo.FacilityVO;
import cn.wzl.assessment.demo.pojo.vo.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/facility")
public class SearchController {

    private final FacilityService facilityService;

    @GetMapping("/available")
    public Result<List<FacilityVO>> availableFacility(@RequestParam(value = "locationId", required = false) Integer locationId) {
        return Result.success(facilityService.getAvailableFacility(locationId));
    }
}
