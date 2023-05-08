package com.example.employeeservice.repository;

import com.example.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String firstName);
    List<Employee> findEmployeeByDepartmentCode(String departmentCode);
}
