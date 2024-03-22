package cn.wzl.assessment.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.wzl.assessment.demo.business.dao"})
public class AssessmentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssessmentDemoApplication.class, args);
    }

}
