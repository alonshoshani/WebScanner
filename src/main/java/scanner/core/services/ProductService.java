package scanner.core.services;

import java.util.List;

import scanner.core.models.entities.Product;
import scanner.core.services.util.ProductList;
import scanner.rest.resources.ProductResource;

public interface ProductService {
	public Product findProduct(int productId);
    public ProductList findAllProductsOfStore(int storeId);
    public boolean addProductToStore(int storeId,List<ProductResource> newProducts);
    public List<Product> findOptionalProductForStore(int storeId);
}
