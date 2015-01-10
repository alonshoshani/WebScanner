package scanner.rest.resources;
import org.springframework.hateoas.ResourceSupport;

import scanner.core.models.entities.Product;


public class ProductResource extends ResourceSupport { 
	private int rid;
	private String name;
	private String description;
	private String category;
	private String imageLink;
	private String linkZap;
	private int minPrice;

	public int getRid() {
		return rid;
	}
	
	public void setRid(int id) {
		this.rid = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getImageLink() {
		return imageLink;
	}
	
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getLinkZap() {
		return linkZap;
	}

	public void setLinkZap(String linkZap) {
		this.linkZap = linkZap;
	}
	
	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public Product toProduct() {
		Product product = new Product();
		product.setId(rid);
		product.setDescription(description);
		product.setCategory(category);
		product.setImageLink(imageLink);
		product.setZapLink(linkZap);
		product.setName(name);
        return product;
    }
}
