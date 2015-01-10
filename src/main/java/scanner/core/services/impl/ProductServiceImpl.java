package scanner.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scanner.core.models.entities.Product;
import scanner.core.repositories.ProductRepo;
import scanner.core.services.ProductService;
import scanner.core.services.util.ProductList;
import scanner.core.services.util.UserProductList;
import scanner.rest.resources.ProductResource;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
    @Qualifier("ProductRepo")
    private ProductRepo productRepo;
	
	@Override
	public Product findProduct(int id) {
		return productRepo.findProduct(id);
	}

	@Override
	public ProductList findAllProductsOfStore(int storeId) {
		return new ProductList(productRepo.findAllProductsOfStore(storeId));
	}

	@Override
	public boolean addProductToStore(int storeId,List<ProductResource> newProducts) {
		return productRepo.addProductToStore(new UserProductList(storeId, newProducts).getUserProducts());
	}

	@Override
	public List<Product> findOptionalProductForStore(int storeId) {
		return productRepo.findOptionalProductForStore(storeId);
	}

}
