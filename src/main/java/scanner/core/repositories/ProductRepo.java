package scanner.core.repositories;

import java.util.List;

import scanner.core.models.entities.Product;
import scanner.core.models.entities.UserProduct;


public interface ProductRepo {
	public Product findProduct(int id);
    public List<UserProduct> findAllProductsOfStore(int id);
    public Product createProduct(Product product);
    public boolean addProductToStore(List<UserProduct> newProducts);
    public List<Product> findOptionalProductForStore(int storeId);
}
