package nick1.Java.Spring.Web.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import nick1.Java.Spring.Web.project.entities.Device;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceDAO extends JpaRepository<Device, Integer> {
}