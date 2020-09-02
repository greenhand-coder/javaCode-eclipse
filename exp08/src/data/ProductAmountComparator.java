package data;

import java.util.Comparator;

public class ProductAmountComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.getProductAmount(), o2.getProductAmount());
	}

}
