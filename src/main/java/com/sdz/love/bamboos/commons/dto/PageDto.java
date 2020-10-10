package com.sdz.love.bamboos.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageDto {
    private int page;
    private int size;

    private String name;
    private String type;
    private String state;
}
