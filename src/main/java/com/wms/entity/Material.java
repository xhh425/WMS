package com.wms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Material {
    private Integer mid;
    private String mname;
    private String specification;
    private String unit;
    private Integer mNumber;
    private String mPS;
}
