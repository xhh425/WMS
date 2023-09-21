package com.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wms.mapper")
public class WarehouseManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseManagementApplication.class, args);
    }

}
