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
	
	public void addProduct() {   //���Ӳ�Ʒ����
		System.out.print("������Ҫ���ӵĲ�Ʒ�ı�ţ�");
		
		String newProductOrder = input.next();
		
		for(int i = 0; i < products.size(); i++) {
			if(newProductOrder.equals(products.get(i).getProductOrder())) {
				System.out.print("���ҵ������Ʒ��������Ҫ���ӵ�������");
				products.get(i).setProductAmount(products.get(i).getProductAmount() + input.nextInt());
				System.out.println("��Ʒ����������");
				return;
			}
		}
		System.out.println("����������Ҫ���ӵĲ�Ʒ�����ƣ�Ϊ�˱�����ʽ����������5����ĸ�����۸��������Կո�ֿ���");
		Product newProduct = new Product(newProductOrder, input.next(), input.nextDouble(), input.nextInt(), date);
		this.products.add(newProduct);
	}  //������ȷ������֤
		
	public void saleProduct() {     //���۲�Ʒ����
		System.out.print("������Ҫ���۵Ĳ�Ʒ�ı�ţ�");
		String productOrder = input.next();
		
		for(int i = 0; i < products.size(); i++) {   //���Ҳ�Ʒ
			if(productOrder.equals(products.get(i).getProductOrder())) {
				System.out.print("���ҵ������Ʒ��������Ҫ�����ټ���");
				int saleNum = input.nextInt();
				if(saleNum > products.get(i).getProductAmount()) {
					System.out.println("��������");
					return;
				}else {
					products.get(i).setProductAmount(products.get(i).getProductAmount() - saleNum);
					System.out.println("������");
				}
				return;
			}
		}
		
		System.out.println("���޴β�Ʒ�������Ӵβ�Ʒ������������Ҫ���ӵĲ�Ʒ�����ƣ�Ϊ�˱�����ʽ����������5����ĸ�����۸��������Կո�ֿ���");
		Product newProduct = new Product(productOrder, input.next(), input.nextDouble(), input.nextInt(), date);
		this.products.add(newProduct);
	}     //����֤��������ȷ
	
	public void sortProductInPrice() {
		Product[] productsArray = (Product[]) products.toArray(new Product[products.size()]);
		ProductPriceComparator sortPrice = new ProductPriceComparator();
		Arrays.sort(productsArray, sortPrice);
		products = new ArrayList<Product>(Arrays.asList(productsArray));
		
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println("���              ����                        �۸�                   �������                       �����                              �ϼ�ʱ��");
		
		
		
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
		System.out.println("���              ����                        �۸�                   �������                       �����                                                 �ϼ�ʱ��");
		
		
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
		System.out.println("���              ����                        �۸�                   �������                       �����                                                 �ϼ�ʱ��");
	
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
		System.out.print("����������ǣ�");
		for(int i =0; i < products.size(); i++) {
			sumOfAmount += products.get(i).getProductAmount();
		}
		System.out.println(sumOfAmount);
		
		System.out.print("����ܽ���ǣ�");
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
