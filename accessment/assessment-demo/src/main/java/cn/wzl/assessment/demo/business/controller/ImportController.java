package cn.wzl.assessment.demo.business.controller;

import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.IoUtil;
import cn.wzl.assessment.demo.business.service.FacilityService;
import cn.wzl.assessment.demo.infra.listener.FacilityImportListener;
import cn.wzl.assessment.demo.pojo.vo.FoodFacilityImportVO;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author wzl
 * @version 1.0 2024/3/21
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ImportController {

    private final FacilityService facilityService;

    /**
     * Entrance to the import function
     */
    @PostMapping(value = "/data/import")
    public void abstractImport(@RequestPart("file") @RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
        try (FastByteArrayOutputStream os = IoUtil.read(file.getInputStream())) {
            log.info("Data importing...");
            FacilityImportListener listener = new FacilityImportListener(facilityService);
            try (ExcelReader excelReader = EasyExcel.read(IoUtil.toStream(os.toByteArray()), FoodFacilityImportVO.class, listener).excelType(ExcelTypeEnum.CSV).build()) {
                excelReader.readAll();
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setCharacterEncoding("utf-8");
                String fileName = URLEncoder.encode("result", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
                response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
                EasyExcel.write(response.getOutputStream(), FoodFacilityImportVO.class).sheet("result").useDefaultStyle(false).doWrite(listener.getData());
            }
            log.info("Import finished");
        } catch (Exception e) {
            log.error("Import error", e);
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("Import error:" + e.getLocalizedMessage());
        }
    }
}
