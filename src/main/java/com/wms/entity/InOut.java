package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InOut {
    private Integer mioid;
    private Date miodate;
    private Integer uid;
    private String mioPS;
    private Integer mid;
    private Integer inNumber;
    private Integer outNumber;
}
