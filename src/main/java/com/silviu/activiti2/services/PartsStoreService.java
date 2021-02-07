package com.silviu.activiti2.services;

import com.silviu.activiti2.pojo.SparePart;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PartsStoreService {

    public List<SparePart> getAvailablePartsInStore() {
        return Arrays.asList(new SparePart("tires", "500$"),
                new SparePart("brakes", "100$"),
                new SparePart("windshield wipers", "10$"));
    }
}
