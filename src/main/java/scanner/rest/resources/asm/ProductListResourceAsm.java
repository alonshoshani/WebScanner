package scanner.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import scanner.core.services.util.ProductList;
import scanner.rest.mvc.ProductController;
import scanner.rest.resources.ProductListResource;
import scanner.rest.resources.ProductResource;

public class ProductListResourceAsm extends ResourceAssemblerSupport<ProductList, ProductListResource> {


    public ProductListResourceAsm() {
        super(ProductController.class, ProductListResource.class);
    }

    @Override
    public ProductListResource toResource(ProductList productList) {
        List<ProductResource> resList = new ProductResourceAsm().toResources(productList.getProducts());
        ProductListResource finalRes = new ProductListResource();
        finalRes.setProducts(resList);
        return finalRes;
    }

}
