package nick1.Java.Spring.Web.project.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import nick1.Java.Spring.Web.project.entities.Device;
import nick1.Java.Spring.Web.project.entities.Employee;
import nick1.Java.Spring.Web.project.exceptions.ExceptionNotFound;
import nick1.Java.Spring.Web.project.payloads.EmployeePayload;
import nick1.Java.Spring.Web.project.repositories.DeviceDAO;
import nick1.Java.Spring.Web.project.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee {

    @Autowired
    EmployeeDAO employeeDAO;

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    private Cloudinary cloudinaryUploader;

    public List<Employee> getAllEmployees(){
        return this.employeeDAO.findAll();
    }

    public Employee findEmployeeById(int id){
        return employeeDAO.findById(id).orElseThrow(() -> new ExceptionNotFound(id));
    }

    public Employee findEmployeeByIdAndUpdate(int id, Employee updatedBody){
        Optional<Employee> optionalEmployee = employeeDAO.findById(id);

        if (optionalEmployee.isPresent()){
            Employee found = optionalEmployee.get();
            found.setUsername(updatedBody.getUsername());
            found.setName(updatedBody.getName());
            found.setSurname(updatedBody.getSurname());
            found.setEMail(updatedBody.getEMail());

            return this.employeeDAO.save(found);
        }else {
            throw new ExceptionNotFound(id);
        }
    }

    public Employee saveEmployee(EmployeePayload body){
        Employee newEmployee = new Employee(body.username(),body.name(), body.surname(), body.eMail(), "https://ui-avatars.com/api/?name="+ body.name() + "+" + body.surname(), new ArrayList<>());
        return this.employeeDAO.save(newEmployee);
    }

    public Employee uploadAvatar(int id, MultipartFile file)throws IOException {
        Employee found = this.findEmployeeById(id);
        String avatarUrl = (String) cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatarUrl(avatarUrl);
        return this.employeeDAO.save(found);
    }

    public void findEmployeeByIdAndDelete(int id){
        Optional<Employee> optionalEmployee = employeeDAO.findById(id);

        if (optionalEmployee.isPresent()){
            Employee found = optionalEmployee.get();
            this.employeeDAO.delete(found);
        }else{
            throw new ExceptionNotFound(id);
        }
    }

    public void addADevice(int employeeId, Device device){
        Employee employee = employeeDAO.findById(employeeId).orElseThrow(() -> new ExceptionNotFound(employeeId));

        device.setEmployee(employee);

        List<Device> devicesList = employee.getDevicesList();
        devicesList.add(device);
        employee.setDevicesList(devicesList);

        employeeDAO.save(employee);
    }
}
