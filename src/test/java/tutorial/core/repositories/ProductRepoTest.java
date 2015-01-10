package tutorial.core.repositories;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scanner.core.models.entities.Product;
import scanner.core.repositories.ProductRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class ProductRepoTest {
	
	@Autowired
	@Qualifier("ProductRepo")
    private ProductRepo repo;

    private Product product;

    @Before
    @Transactional
    @Rollback(false)
    public void setup()
    {/*
    	product = new Product();
    	product.setCategory("Category");
    	product.setDescription("Description");
    	product.setId(0);
    	product.setName("name");
    	product.setImageLink("Link");

*/    }

    @Test
    @Transactional
    public void testFind()
    {/*
    	Product product = repo.findProduct(this.product.getId());
        assertEquals(product.getId(), 4);
        */
    }
    
    @Test
    @Transactional
    public void testGetProducts(){
    	
    	//List<UserProductSimple> products = repo.findOptionalProductForStore(1);
    	System.out.println("example");
    }
    
    
    
}
