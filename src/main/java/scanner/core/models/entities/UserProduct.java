package scanner.core.models.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userproduct")
public class UserProduct {
	
	@EmbeddedId
	private UserDataId userDataId;
	
	private int minPrice;
	
	public UserDataId getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(UserDataId id) {
		this.userDataId = id;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userDataId == null) ? 0 : userDataId.hashCode());
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
		UserProduct other = (UserProduct) obj;
		if (userDataId == null) {
			if (other.userDataId != null)
				return false;
		} else if (!userDataId.equals(other.userDataId))
			return false;
		return true;
	}

	
}
