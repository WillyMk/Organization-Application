package com.example.employeeservice.service;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    //    private RestTemplate restTemplate;
    public final WebClient webClient;

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        System.out.println("Employee " + employeeDto);
        if (employeeDto.getFirstName() == null) {
            throw new RuntimeException("Employee firstname cannot be null");
        }
        if (employeeDto.getLastName() == null) {
            throw new RuntimeException("Employee lastname cannot be null");
        }
        Employee employee = Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .departmentCode(employeeDto.getDepartmentCode())
                .build();

        Employee emp = employeeRepo.save(employee);
        return Employee.toData(emp);
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream().map((employee -> Employee.toData(employee))).collect(Collectors.toList());
    }

    public EmployeeDto getEmployee(String name) {
        Employee employee = employeeRepo.findByFirstName(name);
        if (employee == null) {
            throw new RuntimeException("Couldn't find employee");
        } else {
            return Employee.toData(employee);
        }
    }

    public APIResponseDto getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            Employee e = employee.get();
            if(e.getDepartmentCode() == null) {
                throw new RuntimeException("Body has a null department value");
            }
            DepartmentDto departmentDto = webClient.get()
                    .uri("http://localhost:8080/api/department/" + e.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();

            APIResponseDto apiResponseDto = new APIResponseDto();
            apiResponseDto.setEmployeeDto(Employee.toData(e));
            apiResponseDto.setDepartmentDto(departmentDto);
            return apiResponseDto;
        }
        throw new RuntimeException("Couldn't find employee");
    }
    public Boolean getEmployeeByCode(String code) {
        List<Employee> employees = employeeRepo.findEmployeeByDepartmentCode(code);
        System.out.println("Employees " + employees);
        if(employees.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
}
