package homeworkD2;

public class Select {
	
	private int[] Poker = new int[52];
	
	public void loadPoker(){   //初始化扑克
		for(int i = 0; i < 52; i++ ) {
			Poker[i] = (i + 1) % 13;
			if( (i + 1) % 13 == 0) Poker[i] = 13;
		}
	}
	
	public void getSum() {   //求得每四个数的和
		for(int i = 0; i < 13; i++) {
			int []reservoir = selectPoker();
			System.out.printf("%2d + %2d + %2d + %2d = %2d", reservoir[0], reservoir[1], reservoir[2], reservoir[3], reservoir[0]+ reservoir[1]+ reservoir[2] + reservoir[3]);
			if((reservoir[0]+ reservoir[1]+ reservoir[2]+reservoir[3]) == 24) {
				System.out.print(" YES ");
			}
			System.out.println();
		}
	}
	
	
	
	public int[] selectPoker() {
		int []reservoir = new int[4];   //构造一个存放四张牌的数组
		for(int i = 0; i < 4; i++) {
			//Poker[0-51]
			int x = (int) (Math.random() * 52 );
			if(Poker[x] != 0) {
				int temp = Poker[x];
				Poker[x] = 0;
				reservoir[i] = temp;
			}else {
				i--;
			}
		}
		return reservoir;
	}

}