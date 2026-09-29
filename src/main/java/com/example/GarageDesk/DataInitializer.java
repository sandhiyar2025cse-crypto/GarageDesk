package com.example.GarageDesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.GarageDesk.entity.Bay;
import com.example.GarageDesk.entity.Mechanic;
import com.example.GarageDesk.repository.BayRepository;
import com.example.GarageDesk.repository.MechanicRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MechanicRepository mechanicRepository;
    private final BayRepository bayRepository;

    public DataInitializer(MechanicRepository mechanicRepository,
                           BayRepository bayRepository) {
        this.mechanicRepository = mechanicRepository;
        this.bayRepository = bayRepository;
    }

    @Override
    public void run(String... args) {

        // Add default mechanics only when no mechanics exist
        if (mechanicRepository.count() == 0) {

            mechanicRepository.save(
                    new Mechanic(
                            "Arun Kumar",
                            "9876543210",
                            "Engine Specialist",
                            true
                    )
            );

            mechanicRepository.save(
                    new Mechanic(
                            "Vijay Kumar",
                            "9876543220",
                            "Brake Specialist",
                            true
                    )
            );

            mechanicRepository.save(
                    new Mechanic(
                            "Rahul Kumar",
                            "9876543230",
                            "General Service",
                            true
                    )
            );
        }

        // Add default bays only when no bays exist
        if (bayRepository.count() == 0) {

            bayRepository.save(
                    new Bay(
                            "BAY-01",
                            "General Service",
                            false
                    )
            );

            bayRepository.save(
                    new Bay(
                            "BAY-02",
                            "Heavy Service",
                            false
                    )
            );

            bayRepository.save(
                    new Bay(
                            "BAY-03",
                            "Quick Service",
                            false
                    )
            );

            bayRepository.save(
                    new Bay(
                            "BAY-04",
                            "Electrical Service",
                            false
                    )
            );
        }
    }
}