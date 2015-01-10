package scanner.core.models.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class UserDataId implements Serializable{
	
	@Column(name = "userId")
	private int userId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "productId")
	private Product productId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	public Product getProductId() {
		return productId;
	}
	

	public void setProductId(Product product) {
		this.productId = product;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDataId other = (UserDataId) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
}
