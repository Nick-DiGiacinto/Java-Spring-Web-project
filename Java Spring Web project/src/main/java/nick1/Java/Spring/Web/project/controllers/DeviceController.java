package nick1.Java.Spring.Web.project.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import nick1.Java.Spring.Web.project.entities.Device;
import nick1.Java.Spring.Web.project.payloads.DevicePayload;
import nick1.Java.Spring.Web.project.services.ServiceDevice;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    ServiceDevice serviceDevice;

    @GetMapping
    private List<Device> getAllDevices() {
        return this.serviceDevice.getAllDevices();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Device saveDevice(@RequestBody DevicePayload body) {
        return serviceDevice.saveDevice(body);
    }

    @GetMapping("/{deviceId}")
    public Device findSingleDevice(@PathVariable int deviceId) {
        return this.serviceDevice.findDeviceById(deviceId);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findSingleDeviceAndDelete(@PathVariable int deviceId){
        this.serviceDevice.findByIdAndDelete(deviceId);
    }
    @PutMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Device findSingleDeviceAndUpdate(@PathVariable int deviceId, @RequestBody DevicePayload body) {
        return this.serviceDevice.findByIdAndUpdate(deviceId, body);
    }

}
