package fyuizee.com.ankizletbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    List<String> products = new java.util.ArrayList<>(List.of("Apple", "Banana", "Orange"));

    @GetMapping("/products")
    public List<String> getProducts() {
        return this.products;
    }
}
