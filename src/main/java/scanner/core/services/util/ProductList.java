package scanner.core.services.util;

import java.util.ArrayList;
import java.util.List;

import scanner.core.models.entities.Product;
import scanner.core.models.entities.UserProduct;

public class ProductList {
	private List<Product> products = new ArrayList<Product>();

	public ProductList() {
    		
    }
	
    public ProductList(List<UserProduct> list) {
    	for (UserProduct userProduct : list){
    		this.products.add(userProduct.getUserDataId().getProductId());
    	}
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
