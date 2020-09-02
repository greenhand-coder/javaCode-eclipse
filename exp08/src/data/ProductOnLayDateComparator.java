package data;

import java.util.Comparator;

public class ProductOnLayDateComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return o1.getProductOnLayDate().compareTo(o2.getProductOnLayDate());
	}

}
