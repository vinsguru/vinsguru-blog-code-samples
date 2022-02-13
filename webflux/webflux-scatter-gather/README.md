# Spring WebFlux Gateway Aggregator

This is a simple project using Spring WebFlux to demonstrate gateway-aggregator pattern.

### Usage:
- You need to have docker installed
- Go to data directory and issue ```docker-compose up```
- This uses ```json-server``` to simulate some backend services.
- You can try these to endpoints to confirm the services are working as expected

```bash
# shows the product 1 information
http://localhost:3000/products/1 

# shows the promotion details for product id = 1 
http://localhost:3000/promotions/1 

# to get all the reviews for product id = 1
http://localhost:3000/reviews?productId=1
```

Once you have the above endpoints working, then start the spring application. Follow [this article](https://www.vinsguru.com/spring-webflux-aggregation/) for more information.