package com.vinsguru.cqrspattern.service;

public interface OrderCommandService {
    void createOrder(int userIndex, int productIndex);
}
