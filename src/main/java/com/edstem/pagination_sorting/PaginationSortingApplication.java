package com.edstem.pagination_sorting;

import com.edstem.pagination_sorting.model.Product;
import com.edstem.pagination_sorting.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PaginationSortingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaginationSortingApplication.class, args);
	}

	@Bean
	CommandLineRunner seedDatabase(ProductRepository productRepository) {
		return args ->
		{
			List<Product> products = new ArrayList<>();
			for (int i = 0; i < 1000; i++) {
				products.add(Product.builder()
						.name("Product " + i)
						.category("Category " + (i % 10))
						.price(Math.round(Math.random() * 10000) / 100.0)
						.rating(Math.round(Math.random() * 50) / 10.0)
						.build());
			}
			productRepository.saveAll(products);
		};
	}
}
