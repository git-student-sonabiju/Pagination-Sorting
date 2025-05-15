package com.edstem.pagination_sorting.specification;

import com.edstem.pagination_sorting.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
	public static Specification<Product> getSpecification(String category, Double minPrice, Double maxPrice) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (category != null) {
				predicates.add(criteriaBuilder.equal(root.get("category"), category));
			}
			if (minPrice != null) {
				predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
			}
			if (maxPrice != null) {
				predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));

			}
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};

	}
}
