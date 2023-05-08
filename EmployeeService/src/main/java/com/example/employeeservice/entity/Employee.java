package com.example.employeeservice.entity;

import com.example.employeeservice.dto.EmployeeDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String departmentCode;

    private String departmentName;

    public static EmployeeDto toData(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmail(employee.getEmail());
        dto.setFirstName(employee.getFirstName());
        dto.setId(employee.getId());
        dto.setLastName(employee.getLastName());
        dto.setDepartmentCode(employee.getDepartmentCode());
        dto.setDepartmentName(employee.getDepartmentName());
        return dto;
    }
}
