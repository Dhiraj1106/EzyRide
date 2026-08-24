package org.example.ezyride.Service;

import org.example.ezyride.DAO.DriverDAO;
import org.example.ezyride.Entity.Driver;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverDAO driverDAO;

    public DriverService(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Driver registerDriver(Driver driver) {

        if (driverDAO.findByUserId(driver.getUser().getId()).isPresent()) {
            throw new RuntimeException("Driver profile already exists");
        }

        if (driver.getVerificationStatus() == null) {
            driver.setVerificationStatus(
                    org.example.ezyride.Entity.VerificationStatus.PENDING
            );
        }

        driver.setOnline(false);

        return driverDAO.save(driver);
    }
}