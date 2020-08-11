package com.vinsguru.inventory.service;

import com.vinsguru.dto.InventoryRequestDTO;
import com.vinsguru.dto.InventoryResponseDTO;
import com.vinsguru.enums.InventoryStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {

    private Map<Integer, Integer> productInventoryMap;

    @PostConstruct
    private void init(){
        this.productInventoryMap = new HashMap<>();
        this.productInventoryMap.put(1, 5);
        this.productInventoryMap.put(2, 5);
        this.productInventoryMap.put(3, 5);
    }

    public InventoryResponseDTO deductInventory(final InventoryRequestDTO requestDTO){
        int quantity = this.productInventoryMap.getOrDefault(requestDTO.getProductId(), 0);
        InventoryResponseDTO responseDTO = new InventoryResponseDTO();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setUserId(requestDTO.getUserId());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setStatus(InventoryStatus.UNAVAILABLE);
        if(quantity > 0){
            responseDTO.setStatus(InventoryStatus.AVAILABLE);
            this.productInventoryMap.put(requestDTO.getProductId(), quantity - 1);
        }
        return responseDTO;
    }

    public void addInventory(final InventoryRequestDTO requestDTO){
        this.productInventoryMap
                .computeIfPresent(requestDTO.getProductId(), (k, v) -> v + 1);
    }

}
