package service;

import java.io.File;
import java.util.ArrayList;
import model.Thumbnail;

public class DataService {
	/*
	 * 为整个项目提供数据服务，静态变量充当全局变量，所有数据调用均在这个类里
	 */
	protected static ArrayList<File> selectedPictureFiles = new  ArrayList<>();
	protected static ArrayList<Thumbnail> selectedPictures = new ArrayList<>();
	protected static ArrayList<Thumbnail> cutedPictures = new ArrayList<>();
	
}
