package data;
import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.getProductPrice(), o2.getProductPrice());
	}
	

}
