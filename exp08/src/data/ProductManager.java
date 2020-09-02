package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class ProductManager {
	
	private ArrayList<Product> products = new ArrayList<>();
	Scanner input = new Scanner(System.in);
	Date date = new Date(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	File file = new File("C:\\Users\\admin\\Documents\\java\\exp08\\products.dat");
	
	public void addProduct() {   //增加产品函数
		System.out.print("请输入要增加的产品的编号：");
		
		String newProductOrder = input.next();
		
		for(int i = 0; i < products.size(); i++) {
			if(newProductOrder.equals(products.get(i).getProductOrder())) {
				System.out.print("已找到所查产品，请输入要增加的数量：");
				products.get(i).setProductAmount(products.get(i).getProductAmount() + input.nextInt());
				System.out.println("产品数额已增加");
				return;
			}
		}
		System.out.println("请依次输入要增加的产品的名称（为了保护格式，名称输入5个字母）、价格、数量（以空格分开）");
		Product newProduct = new Product(newProductOrder, input.next(), input.nextDouble(), input.nextInt(), date);
		this.products.add(newProduct);
	}  //函数正确，已验证
		
	public void saleProduct() {     //销售产品函数
		System.out.print("请输入要销售的产品的编号：");
		String productOrder = input.next();
		
		for(int i = 0; i < products.size(); i++) {   //查找产品
			if(productOrder.equals(products.get(i).getProductOrder())) {
				System.out.print("已找到所查产品，请输入要卖多少件：");
				int saleNum = input.nextInt();
				if(saleNum > products.get(i).getProductAmount()) {
					System.out.println("货不够了");
					return;
				}else {
					products.get(i).setProductAmount(products.get(i).getProductAmount() - saleNum);
					System.out.println("已卖出");
				}
				return;
			}
		}
		
		System.out.println("查无次产品，请增加次产品，请依次输入要增加的产品的名称（为了保护格式，名称输入5个字母）、价格、数量（以空格分开）");
		Product newProduct = new Product(productOrder, input.next(), input.nextDouble(), input.nextInt(), date);
		this.products.add(newProduct);
	}     //已验证，函数正确
	
	public void sortProductInPrice() {
		Product[] productsArray = (Product[]) products.toArray(new Product[products.size()]);
		ProductPriceComparator sortPrice = new ProductPriceComparator();
		Arrays.sort(productsArray, sortPrice);
		products = new ArrayList<Product>(Arrays.asList(productsArray));
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("编号              名称                        价格                   库存数量                       库存金额                              上架时间");
		
		
		
		for(int i = 0; i < products.size(); i++) {
			

			
			System.out.printf("%s      %s       %f      %d          %f      %s\n",
					products.get(i).getProductOrder(), products.get(i).getProductName(), 
					products.get(i).getProductPrice(), products.get(i).getProductAmount(),
					(products.get(i).getProductPrice()* products.get(i).getProductAmount()),
					sdf.format(products.get(i).getProductOnLayDate()));
		}
	}
	
	public void sortProductInAmount() {
		Product[] productsArray = (Product[]) products.toArray(new Product[products.size()]);
		ProductAmountComparator sortAmount = new ProductAmountComparator();
		Arrays.sort(productsArray, sortAmount);
		products = new ArrayList<Product>(Arrays.asList(productsArray));
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("编号              名称                        价格                   库存数量                       库存金额                                                 上架时间");
		
		
		for(int i = 0; i < products.size(); i++) {
			System.out.printf("%s      %s       %f      %d          %f      %s\n",
					products.get(i).getProductOrder(), products.get(i).getProductName(), 
					products.get(i).getProductPrice(), products.get(i).getProductAmount(),
					(products.get(i).getProductPrice()* products.get(i).getProductAmount()),
					sdf.format(products.get(i).getProductOnLayDate()));
		}		
	}
	
	public void sortProducrInDate() {
		Product[] productsArray = (Product[]) products.toArray(new Product[products.size()]);
		ProductOnLayDateComparator sortDate = new ProductOnLayDateComparator();
		Arrays.sort(productsArray, sortDate);
		products = new ArrayList<Product>(Arrays.asList(productsArray));

		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("编号              名称                        价格                   库存数量                       库存金额                                                 上架时间");
	
		for(int i = 0; i < products.size(); i++) {
			System.out.printf("%s      %s       %f      %d          %f      %s\n",
					products.get(i).getProductOrder(), products.get(i).getProductName(), 
					products.get(i).getProductPrice(), products.get(i).getProductAmount(),
					(products.get(i).getProductPrice()* products.get(i).getProductAmount()),
					sdf.format(products.get(i).getProductOnLayDate()));
		}	
	}
	
	public void collection() {
		double sumOfAmount = 0;
		double sumOfPrice = 0;
		System.out.print("库存总数量是：");
		for(int i =0; i < products.size(); i++) {
			sumOfAmount += products.get(i).getProductAmount();
		}
		System.out.println(sumOfAmount);
		
		System.out.print("库存总金额是：");
		for(int i =0; i < products.size(); i++) {
			sumOfPrice += (products.get(i).getProductPrice()* products.get(i).getProductAmount());
		}
		System.out.println(sumOfPrice);
		
	}
	
	public void fileRead() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getName()))){
			this.products = (ArrayList<Product>) ois.readObject();
		}catch(FileNotFoundException e) {
			e.getStackTrace();
		}catch(IOException e) {
			e.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void fileWrite() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.getName()))){
			oos.writeObject(products);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
