package scanner.core.repositories.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import scanner.core.models.entities.Product;
import scanner.core.models.entities.UserProduct;
import scanner.core.repositories.ProductRepo;

@Repository("ProductRepo")
@Transactional
public class jpaProductRepo implements ProductRepo{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Product findProduct(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<UserProduct> findAllProductsOfStore(int id) {
		Query query = em.createQuery("SELECT a FROM UserProduct a WHERE userid=?1");
        query.setParameter(1, id);
        List<UserProduct> products = query.getResultList();
        return products;
	}

	@Override
	public Product createProduct(Product product) {
		em.persist(product);
		return product;
	}

	@Override
	public boolean addProductToStore(List<UserProduct> newProducts) {
		for(UserProduct userProduct : newProducts){
			em.persist(userProduct);
		}
		em.flush();
		return true;
	}

	@Override
	public List<Product> findOptionalProductForStore(int storeId) {
		Query query1 = em.createQuery("SELECT b FROM Product b Where b.id not in (SELECT a.userDataId.productId.id FROM UserProduct a Where a.userDataId.userId=?1)");
		query1.setParameter(1, storeId);
		List<Product> notExistProductsInStore = query1.getResultList();
		return notExistProductsInStore;
		
	}

}
