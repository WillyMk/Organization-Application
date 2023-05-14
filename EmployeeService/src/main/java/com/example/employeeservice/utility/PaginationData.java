package com.example.employeeservice.utility;

import lombok.Data;

import java.util.List;

@Data
public class PaginationData {
    private List<?> content;
    private int pageNo;
    private int pageSize;

    private Long totalElements;
    private int totalPages;

    private boolean last;
}
