package data;

import java.util.Scanner;

public class Main {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductManager a = new ProductManager();
		Scanner input = new Scanner(System.in);
		
		
		
		while(true) {
			System.out.println("请输入指令     1.增加  2. 卖出   3.排序  4.统计  5.读取文档  6.存档");
			int x = input.nextInt();
			if(x == 1) {
				a.addProduct();
				System.out.println();
				System.out.println();
			}else if(x == 2) {
				a.saleProduct();
				System.out.println();
				System.out.println();
			}else if(x == 3) {
				System.out.print("请输入指令     1.按价格排序      2.按库存数量排序     3.按上架日期排序  ");
				int y = input.nextInt();
				if(y == 1) {
					a.sortProductInPrice();
					System.out.println();
					System.out.println();
				}else if(y == 2) {
					a.sortProductInAmount();
					System.out.println();
					System.out.println();
				}else if(y == 3) {
					a.sortProducrInDate();
					System.out.println();
					System.out.println();
				}
			}else if(x == 4) {
				a.collection();
				System.out.println();
				System.out.println();
			}else if(x == 5) {
				a.fileRead();
				System.out.println("读档成功");
				System.out.println();
				System.out.println();
			}else if(x == 6) {
				a.fileWrite();
				System.out.println("存档成功");
				System.out.println();
				System.out.println();
			}
			
		}

	}

}
