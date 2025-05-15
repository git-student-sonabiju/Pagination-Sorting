package com.edstem.pagination_sorting.service;

import com.edstem.pagination_sorting.dto.PageableResponse;
import com.edstem.pagination_sorting.dto.ProductDTO;
import com.edstem.pagination_sorting.model.Product;
import com.edstem.pagination_sorting.repository.ProductRepository;
import com.edstem.pagination_sorting.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public PageableResponse<ProductDTO> getProducts(int page, int size, String sortBy, String direction, String category, Double minPrice, Double maxPrice) {
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Specification<Product> spec = ProductSpecification.getSpecification(category, minPrice, maxPrice);
		Page<Product> productPage = productRepository.findAll(spec, pageable);

		List<ProductDTO> dtoList = productPage.getContent()
				.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());

		return new PageableResponse<>(
				dtoList,
				productPage.getNumber(),
				productPage.getSize(),
				productPage.getTotalElements(),
				productPage.getTotalPages(),
				productPage.isLast()
		);
	}

	private ProductDTO toDTO(Product product) {
		return new ProductDTO(
				product.getId(),
				product.getName(),
				product.getCategory(),
				product.getPrice(),
				product.getRating()
		);
	}
}
