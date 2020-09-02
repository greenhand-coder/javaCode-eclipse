package tjx1;
//Ë«±ß¼ì²â  ÏÔÖøĞÔË®Æ½0.05
public class JsjyUtil {
	double adverageX;
	double nowX;
	double variance;
	double n;
	double level;
	double z¦Á = 1.96;

	
	public JsjyUtil(double adverageX,double nowX, double variance, double n, double level) {
		this.adverageX = adverageX;
		this.nowX = nowX;
		this.variance = variance;
		this.n = n;
		this.level = level;
	}
	
	public int Jsjy() {
		if(Math.abs(z()) < z¦Á) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public double z() {
		double z = (adverageX - nowX) / (variance * Math.sqrt(n));
		return z;
	}
	
}
