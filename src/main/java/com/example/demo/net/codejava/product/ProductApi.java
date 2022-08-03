package com.example.demo.net.codejava.product;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApi {

	@Autowired private ProductRepository repo;
	
	@PostMapping
	@RolesAllowed("ROLE_ONBOARDING_REVIEWER,ROLE_ONBOARDING_MANAGER")
	public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
		Product savedProduct = repo.save(product);
		URI productURI = URI.create("/products/" + savedProduct.getId());
		return ResponseEntity.created(productURI).body(savedProduct);
	}
	
	@GetMapping
	@RolesAllowed({"ROLE_ONBOARDING_REVIEWER", "ROLE_ASSOCIATE","ROLE_ONBOARDING_MANAGER"})
	public List<Product> list() {
		return repo.findAll();
	}
}
