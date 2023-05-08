package com.example.departmentservice.entity;

import com.example.departmentservice.dto.DepartmentDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;

    public static DepartmentDto toData(Department department){
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setDepartmentCode(department.getDepartmentCode());
        dto.setDepartmentName(department.getDepartmentName());
        dto.setDepartmentDescription(department.getDepartmentDescription());
        return dto;
    }
}
