package com.vinsguru.materializedview.service;

import com.github.javafaker.Faker;
import com.vinsguru.materializedview.entity.Product;
import com.vinsguru.materializedview.entity.PurchaseOrder;
import com.vinsguru.materializedview.entity.User;
import com.vinsguru.materializedview.repository.ProductRepository;
import com.vinsguru.materializedview.repository.PurchaseOrderRepository;
import com.vinsguru.materializedview.repository.UserRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        //
        //this.createUsers(faker);
        //this.createProducts(faker);
/*        IntStream.range(0, 10)
                .forEach(i -> this.createOrder(faker));*/
        //this.createOrder(faker);

        //System.out.println("Created users");
    }

    private void createOrder(Faker faker){
        List<Product> products = this.productRepository.findAll();
        List<User> users = this.userRepository.findAll();

        List<PurchaseOrder> purchaseOrders = IntStream.range(0, 100_000)
                .mapToObj(i -> {
                    int userIndex = faker.number().numberBetween(0, 10000);
                    int prodIndex = faker.number().numberBetween(0, 1000);
                    PurchaseOrder purchaseOrder = new PurchaseOrder();
                    purchaseOrder.setUserId(users.get(userIndex).getId());
                    purchaseOrder.setProductId(products.get(prodIndex).getId());
                    return purchaseOrder;
                })
                .collect(Collectors.toList());
        this.purchaseOrderRepository.saveAll(purchaseOrders);
    }

    private void createUsers(Faker faker){
        List<User> users = IntStream.range(0, 10000)
                .mapToObj(i -> {
                    User user = new User();
                    user.setFirstname(faker.name().firstName());
                    user.setLastname(faker.name().lastName());
                    user.setState(faker.address().stateAbbr());
                    return user;
                })
                .collect(Collectors.toList());
        this.userRepository.saveAll(users);
    }

    private void createProducts(Faker faker){
        List<Product> products = IntStream.range(0, 1000)
                .mapToObj(i -> {
                    Product product = new Product();
                    product.setDescription(faker.book().title());
                    product.setPrice(faker.number().numberBetween(1, 200));
                    return product;
                })
                .collect(Collectors.toList());
        this.productRepository.saveAll(products);
    }
}
