package nick1.Java.Spring.Web.project.controllers;

import java.io.IOException;
import java.util.List;
import nick1.Java.Spring.Web.project.entities.Device;
import nick1.Java.Spring.Web.project.entities.Employee;
import nick1.Java.Spring.Web.project.payloads.EmployeeResponse;
import nick1.Java.Spring.Web.project.services.ServiceEmployee;
import nick1.Java.Spring.Web.project.exceptions.ExceptionBadRequest;
import nick1.Java.Spring.Web.project.payloads.EmployeePayload;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    ServiceEmployee serviceEmployee;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return this.serviceEmployee
                .getAllEmployees();
    }

    @PatchMapping("/{employeeId}/avatar")
    public Employee uploadAvatar(@RequestParam("avatar")MultipartFile file, @PathVariable int employeeId){
        try {
            return serviceEmployee
                    .uploadAvatar(employeeId, file);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private EmployeeResponse saveEmployee(@RequestBody @Validated EmployeePayload body, BindingResult validation){
        if (validation.hasErrors()){
            throw new ExceptionBadRequest(validation.getAllErrors());
        }
        return  new EmployeeResponse(this.serviceEmployee
                .saveEmployee(body).getId());
    }

    @GetMapping("/{employeeId}")
    private Employee getSingleEmployee(@PathVariable int employeeId){
        return this.serviceEmployee
                .findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    private Employee findSingleEmployeeAndUpdate(@PathVariable int employeeId, @RequestBody Employee body){
        return this.serviceEmployee
                .findEmployeeByIdAndUpdate(employeeId, body);
    }

    @PatchMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void addDevice(@PathVariable int employeeId, @RequestBody Device device){
        this.serviceEmployee
                .addADevice(employeeId, device);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteEmployee(@PathVariable int employeeId){
        this.serviceEmployee
                .findEmployeeByIdAndDelete(employeeId);
    }
}
