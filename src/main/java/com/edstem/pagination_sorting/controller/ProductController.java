package com.edstem.pagination_sorting.controller;

import com.edstem.pagination_sorting.dto.PageableResponse;
import com.edstem.pagination_sorting.dto.ProductDTO;
import com.edstem.pagination_sorting.dto.SliceResponse;
import com.edstem.pagination_sorting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping
	public ResponseEntity<PageableResponse<ProductDTO>> getProducts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice
	) {
		return ResponseEntity.ok(productService.getProducts(page, size, sortBy, direction, category, minPrice, maxPrice));
	}

	@GetMapping("/slice")
	public ResponseEntity<SliceResponse<ProductDTO>> getProductSlice(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	) {
		Slice<ProductDTO> slice = productService.getProductSlice(page, size);
		SliceResponse<ProductDTO> response = SliceResponse.<ProductDTO>builder()
				.content(slice.getContent())
				.hasNext(slice.hasNext())
				.build();

		return ResponseEntity.ok(response);
	}


	@GetMapping("/all")
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/wrapped")
	public ResponseEntity<Page<ProductDTO>> getWrappedProducts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
	) {
		return ResponseEntity.ok(productService.getWrappedProducts(page, size));
	}

}
