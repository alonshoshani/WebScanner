package scanner.core.services.util;

import java.util.ArrayList;
import java.util.List;

import scanner.core.models.entities.UserDataId;
import scanner.core.models.entities.UserProduct;
import scanner.rest.resources.ProductResource;


public class UserProductList {
	 private List<UserProduct> userProducts = new ArrayList<UserProduct>();

	    public UserProductList(int storeId,List<ProductResource> list) {
	        for(ProductResource product : list){
	        	UserProduct tempUserProduct=new UserProduct();
	        	UserDataId tempUserDataId=new UserDataId();
	        	tempUserDataId.setProductId(product.toProduct());
	        	tempUserDataId.setUserId(storeId);
	        	tempUserProduct.setUserDataId(tempUserDataId);
	        	tempUserProduct.setMinPrice(product.getMinPrice());
	        	this.userProducts.add(tempUserProduct);
	        	
	        }
	    }

	    public List<UserProduct> getUserProducts() {
	        return userProducts;
	    }

	    public void setUserProducts(List<UserProduct> userProducts) {
	        this.userProducts = userProducts;
	    }
}
