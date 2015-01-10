package scanner.rest.mvc;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import scanner.core.models.entities.Account;
import scanner.core.models.entities.Product;
import scanner.core.services.ProductService;
import scanner.core.services.impl.UserInfo;
import scanner.core.services.util.ProductList;
import scanner.rest.resources.ProductListResource;
import scanner.rest.resources.ProductResource;
import scanner.rest.resources.asm.ProductListResourceAsm;
import scanner.rest.resources.asm.ProductResourceAsm;

@Controller
@RequestMapping("/rest/products")
public class ProductController {

	@Autowired
	private UserInfo userInfo;
	
	private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @RequestMapping( value="/{productId}",method = RequestMethod.GET) 
    @PreAuthorize("permitAll")
	public ResponseEntity<ProductResource> getProduct(@PathVariable int productId) {
    	Product product=productService.findProduct(productId);
    	ProductResource res = new ProductResourceAsm().toResource(product);
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(res.getLink("self").getHref()));
        return new ResponseEntity<ProductResource>(res, headers, HttpStatus.OK);
	}
    
    /*
    @RequestMapping( value="/all",method = RequestMethod.GET) 
    @PreAuthorize("permitAll")
	public ResponseEntity<ProductResource> getAllProductsInSystem() {
    	
    	Product product=productService.findProduct(productId);
    	ProductResource res = new ProductResourceAsm().toResource(product);
    	HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(res.getLink("self").getHref()));
        return new ResponseEntity<ProductResource>(res, headers, HttpStatus.OK);
       
	}
	*/
    
    
    @RequestMapping( value="/updateProducts",method = RequestMethod.POST) 
    @PreAuthorize("permitAll")
	public ResponseEntity<ProductResource> getAllProducts(@RequestBody List<ProductResource> newProducts) {
    	Account active=userInfo.getActiveUser();
    	int storeId=active.getId();
    	boolean result=productService.addProductToStore(storeId, newProducts);
        return new ResponseEntity<ProductResource>(HttpStatus.OK);
	}
    
    
    @RequestMapping( value="/optionalProducts",method = RequestMethod.GET) 
    @PreAuthorize("permitAll")
	public ResponseEntity<ProductListResource> getOptionalProducts() {
    	Account active=userInfo.getActiveUser();
    	int storeId=active.getId();
    	ProductList productsList=new ProductList();
    	productsList.setProducts(productService.findOptionalProductForStore(storeId));
    	ProductListResource res=new ProductListResourceAsm().toResource(productsList);
        return new ResponseEntity<ProductListResource>(res, HttpStatus.OK);
	}
    
}
