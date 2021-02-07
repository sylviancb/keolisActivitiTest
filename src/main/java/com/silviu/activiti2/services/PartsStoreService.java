package com.silviu.activiti2.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PartsStoreService {

    public List<String> getAvailablePartsInStore() {
        return Arrays.asList("tires", "brakes", "windshield wipers");
    }
}
