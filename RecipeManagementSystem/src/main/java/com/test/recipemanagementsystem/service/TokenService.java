package com.test.recipemanagementsystem.service;

import com.test.recipemanagementsystem.model.AuthenticationToken;
import com.test.recipemanagementsystem.repository.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    ITokenRepo tokenRepo;

    public void save(AuthenticationToken token) {
        tokenRepo.save(token);
    }

    public boolean isPresent(Integer patientId) {
        Integer byUserId = tokenRepo.findByUserId(patientId);
        if (byUserId != null) {
            return true;
        }
        return false;
    }

    public Integer removeTokenByPatientId(Integer id) {
        return tokenRepo.deleteByUserId(id);
    }
}
