package scanner.rest.resources.asm;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import scanner.core.models.entities.Product;
import scanner.rest.mvc.AccountController;
import scanner.rest.mvc.ProductController;
import scanner.rest.resources.ProductResource;

public class ProductResourceAsm extends ResourceAssemblerSupport<Product, ProductResource>  {
	public ProductResourceAsm() {
        super(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product product) {
    	ProductResource res=new ProductResource();
    	res.setRid(product.getId());
    	res.setCategory(product.getCategory());
    	res.setName(product.getName());
    	res.setDescription(product.getDescription());
    	res.setImageLink(product.getImageLink());
    	res.setLinkZap(product.getZapLink());
    	res.add(linkTo(methodOn(AccountController.class).getAccount(product.getId())).withSelfRel());
        return res;
    }
}
