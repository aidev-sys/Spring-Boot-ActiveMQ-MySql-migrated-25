package edson.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootActiveMQApplication {

    public final static String PRODUCT_MESSAGE_QUEUE = "product-message-queue";
    public final static String CLIENT_MESSAGE_QUEUE = "client-message-queue";

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActiveMQApplication.class, args);
    }
}