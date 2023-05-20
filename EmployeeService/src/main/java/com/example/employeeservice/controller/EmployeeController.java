package com.example.employeeservice.controller;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.utility.PaginationData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println("Employee con " + employeeDto);
        EmployeeDto employee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginationData> getEmployees(@RequestParam(value ="page", defaultValue = "1", required = false) int page,
                                                       @RequestParam(value ="pageSize", defaultValue = "10", required = false) int pageSize) {
        PaginationData employees = employeeService.getEmployees(page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
    

    @GetMapping("{name}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String name) {
        EmployeeDto employee = employeeService.getEmployee(name);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<Boolean> getEmployeeByDepartmentCode(@PathVariable String code) {
        Boolean employees = employeeService.getEmployeeByCode(code);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("byId/{id}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id) {
        APIResponseDto employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
