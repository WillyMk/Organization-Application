package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto department){
        DepartmentDto departmentDto = departmentService.saveDepartment(department);
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getDepartments(){
        List<DepartmentDto> departments = departmentService.getDepartments();
        return new ResponseEntity<List<?>>(departments, HttpStatus.OK);
    }

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getByCode(@PathVariable String code){
        DepartmentDto departmentDto = departmentService.getByCode(code);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @GetMapping("findBy/{id}")
    public ResponseEntity<Department> getDepById(@PathVariable Long id){
        return new ResponseEntity<Department>(departmentService.getDepById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDepartments(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
