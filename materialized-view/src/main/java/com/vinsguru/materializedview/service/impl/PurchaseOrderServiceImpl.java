package com.vinsguru.materializedview.service.impl;

import com.vinsguru.materializedview.dto.PurchaseOrderSummaryDto;
import com.vinsguru.materializedview.entity.Product;
import com.vinsguru.materializedview.entity.PurchaseOrder;
import com.vinsguru.materializedview.entity.User;
import com.vinsguru.materializedview.repository.ProductRepository;
import com.vinsguru.materializedview.repository.PurchaseOrderRepository;
import com.vinsguru.materializedview.repository.PurchaseOrderSummaryRepository;
import com.vinsguru.materializedview.repository.UserRepository;
import com.vinsguru.materializedview.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderSummaryRepository purchaseOrderSummaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    private List<User> users;
    private List<Product> products;

    @PostConstruct
    private void init(){
        this.users = this.userRepository.findAll();
        this.products = this.productRepository.findAll();
    }

    @Override
    public void placeOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex).getId());
        purchaseOrder.setUserId(this.users.get(userIndex).getId());
        this.purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrderSummaryDto> getSaleSummary() {
        return this.purchaseOrderSummaryRepository.findAll()
                        .stream()
                        .map(pos -> {
                            PurchaseOrderSummaryDto dto = new PurchaseOrderSummaryDto();
                            dto.setState(pos.getState());
                            dto.setTotalSale(pos.getTotalSale());
                            return dto;
                        })
                        .collect(Collectors.toList());
    }
}
