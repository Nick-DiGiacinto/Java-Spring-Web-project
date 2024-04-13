package nick1.Java.Spring.Web.project.services;

import java.util.List;
import java.util.Optional;
import nick1.Java.Spring.Web.project.entities.Device;
import nick1.Java.Spring.Web.project.exceptions.ExceptionNotFound;
import nick1.Java.Spring.Web.project.payloads.DevicePayload;
import nick1.Java.Spring.Web.project.repositories.DeviceDAO;
import nick1.Java.Spring.Web.project.repositories.EmployeeDAO;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceDevice {

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    EmployeeDAO employeeDAO;

    public List<Device> getAllDevices(){
        return this.deviceDAO.findAll();
    }

    public Device findDeviceById(int id){
        return this.deviceDAO.findById(id).orElseThrow(() -> new ExceptionNotFound(id));
    }

    public Device saveDevice(DevicePayload body){
        Device newDevice = new Device(body.type(), body.status());
        return this.deviceDAO.save(newDevice);
    }

    public void findByIdAndDelete(int id){
        Optional<Device> optionalDevice = deviceDAO.findById(id);
        if (optionalDevice.isPresent()){
            Device found = optionalDevice.get();
            this.deviceDAO.delete(found);
        }else {
            throw new ExceptionNotFound(id);
        }

    }

    public Device findByIdAndUpdate(int id, DevicePayload body){
        Optional<Device> optionalDevice = deviceDAO.findById(id);

        if (optionalDevice.isPresent()){
            Device found = optionalDevice.get();
            found.setStatus(body.status());
            return this.deviceDAO.save(found);
        }else {
            throw new ExceptionNotFound(id);
        }

    }
}
