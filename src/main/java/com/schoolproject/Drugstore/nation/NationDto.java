package com.schoolproject.Drugstore.nation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NationDto {
    private Integer id;
    private String name;
}
