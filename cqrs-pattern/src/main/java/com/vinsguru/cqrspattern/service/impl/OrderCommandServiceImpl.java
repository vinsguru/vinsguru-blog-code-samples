package com.vinsguru.cqrspattern.service.impl;

import com.vinsguru.cqrspattern.entity.Product;
import com.vinsguru.cqrspattern.entity.PurchaseOrder;
import com.vinsguru.cqrspattern.entity.User;
import com.vinsguru.cqrspattern.repository.ProductRepository;
import com.vinsguru.cqrspattern.repository.PurchaseOrderRepository;
import com.vinsguru.cqrspattern.repository.UserRepository;
import com.vinsguru.cqrspattern.service.OrderCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {

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
    public void createOrder(int userIndex, int productIndex) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductId(this.products.get(productIndex).getId());
        purchaseOrder.setUserId(this.users.get(userIndex).getId());
        this.purchaseOrderRepository.save(purchaseOrder);
    }
}
