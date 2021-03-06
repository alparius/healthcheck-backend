package healthcheck.application.service;

import healthcheck.application.model.Hospital;
import healthcheck.application.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService{

    @Autowired
    HospitalRepository hospitalRepository;

    public Hospital getById(Long hospitalId) {
        return hospitalRepository.findById(hospitalId).get();
    }

    public List<Hospital> getAllHospitalsFromCity(String city) {
        return hospitalRepository.getAllByCityCaseInsesitive(city);
    }
}
