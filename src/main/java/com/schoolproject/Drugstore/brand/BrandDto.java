package com.schoolproject.Drugstore.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandDto {
    private Integer id;
    private String name;
    private String description;
    private String avatar;
    private Integer nationId;

}
