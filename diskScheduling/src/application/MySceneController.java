package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Random;
import javafx.event.ActionEvent;

public class MySceneController {
    /*
             *     代码 已经全部正常运行，但是由于设计初期吧sstf当做ssft，所以命名全错，修改发现bug很多，所以不做修改           
     */
	@FXML
	private Button startLOOK;
	@FXML
	private Button startCSCAN;
	@FXML
	private Button startSSFT;
	@FXML
	private Button startFCFS;
	@FXML
	private TextField lookTextField;
	@FXML
	private TextField cscanTextField;
	@FXML
	private TextField ssftTextField;
	@FXML
	private TextField fcfsTextField;
	@FXML
	private Button refresh;
	@FXML
	private LineChart<Number, Number> lookChart;
	@FXML
	private LineChart<Number, Number> cscanChart;
	@FXML
	private LineChart<Number, Number> fcfsChart;
	@FXML
	private LineChart<Number, Number> ssftChart;
	
	Random random = new Random();
	int head = random.nextInt(1498) + 1;
	int[] randomArray = getRandomArray(); //已经生成好，下面随时可以拿来用 


	// Event Listener on Button[#startLOOK].onAction
	@FXML
	public void startLOOKAction(ActionEvent event) {
		lookChart.getData().clear();
		XYChart.Series<Number, Number> lookSeries = new XYChart.Series<Number, Number>();
		int[] lookList = getLookList();
		lookSeries.getData().add(new XYChart.Data<Number, Number>(0,head));
		for(int i = 1; i < 399; i++) {
			lookSeries.getData().add(new XYChart.Data<Number , Number>(i, lookList[i]));
		}
		lookChart.getData().add(lookSeries);
		lookTextField.setText("LOOK寻道数：" + lookList[0]);
	}
	// Event Listener on Button[#startCSCAN].onAction
	@FXML
	public void startCSCANAction(ActionEvent event) {
		cscanChart.getData().clear();
		XYChart.Series<Number, Number> cscanSeries = new XYChart.Series<Number, Number>();
		int[] cscanList = getCscanList();
		cscanSeries.getData().add(new XYChart.Data<Number, Number>(0,head));
		for(int i = 1; i < 399; i++) {
			cscanSeries.getData().add(new XYChart.Data<Number , Number>(i, cscanList[i]));			
		}
		cscanChart.getData().add(cscanSeries);
		cscanTextField.setText("CSCAN寻道数：" + cscanList[0]);
	}
	// Event Listener on Button[#startSSFT].onAction
	@FXML
	public void startSSFTAction(ActionEvent event) {
		ssftChart.getData().clear();
		XYChart.Series<Number, Number> ssftSeries = new XYChart.Series<Number, Number>();
		int[] ssftList = getSsftList();
		ssftSeries.getData().add(new XYChart.Data<Number, Number>(0,head));
		for(int i = 1; i < 400; i++) {
			ssftSeries.getData().add(new XYChart.Data<Number , Number>(i, ssftList[i]));
		}
		ssftChart.getData().add(ssftSeries);
		ssftTextField.setText("SSTF寻道数：" + ssftList[0]);
	}
	// Event Listener on Button[#startFCFS].onAction
	@FXML
	public void startFCFSAction(ActionEvent event) {
		fcfsChart.getData().clear();
		XYChart.Series<Number, Number> fcfsSeries = new XYChart.Series<Number, Number>();
		int[] fcfsList = new int[400];
		System.arraycopy(randomArray, 0, fcfsList, 0, 400);
		fcfsList[0] = head;
		fcfsSeries.getData().add(new XYChart.Data<Number, Number>(0,head));
		for(int i = 1; i < 400; i++) {
			fcfsSeries.getData().add(new XYChart.Data<Number , Number>(i, fcfsList[i]));
			fcfsList[0] += Math.abs(fcfsList[i] - fcfsList[i-1]);
		}
		fcfsChart.getData().add(fcfsSeries);
		fcfsList[0] -= head;
		fcfsTextField.setText("FCFS寻道数：" + fcfsList[0]);
		
	}
	// Event Listener on Button[#startAll].onAction
	@FXML
	public void refreshAction(ActionEvent event) {
		randomArray = getRandomArray();
		lookChart.getData().clear();
		cscanChart.getData().clear();
		fcfsChart.getData().clear();
		ssftChart.getData().clear();
		
	}
	
	public int[] getRandomArray() {          //已验证，刚好400个按要求的随机数
		int count1 = 0;    //0-499数字计数
		int count2 = 0;    //500-999数字计数
		int count3 = 0;    //1000-1499数字计数
		int[] randomArray = new int[400];
		for(int i = 0; i < 400; i++) {
			randomArray[i] = random.nextInt(1500);
			if(randomArray[i] < 500) {
				count1++;
				if(count1 == 201) {
					i--;
					count1--;
				}
			}else if(randomArray[i] < 1000) {
				count2++;
				if(count2 == 101) {
					i--;
					count2--;
				}
			}else {
				count3++;
				if(count3 == 101) {
					i--;
					count3--;
				}
			}
		}
		return randomArray;
		
	}
	public int[] getLookList() {
		int closestNum = 0;                //大于head的最小的数字的下标
		int k = 1;                            //lookList的下标
		int[] lookList = new int[400];
		int[] sortedRandomArray = new int[400];
		System.arraycopy(randomArray, 0, sortedRandomArray, 0, 400);
		Arrays.sort(sortedRandomArray);
		lookList[0] = head;
		for(int i = 0; i < 400; i++) {
			if(head > sortedRandomArray[i]) {
				closestNum = i;		
			}
		}
//		System.out.println("head:" + head + "closetNum + 1:" + sortedRandomArray[closetNum + 1] + "closetNum:" + sortedRandomArray[closetNum]);
//		解除上句注释可证明closestNum存放小于head的最大的数字的下标
		closestNum++;
		for(int i = closestNum; i < 400; i++, k++) {
			System.out.println(k);
			lookList[k] = sortedRandomArray[i];
			lookList[0] += Math.abs(lookList[k] - lookList[k-1]);
		}
		System.out.print(k);
		for(int i = closestNum - 2; i >= 0 ;i--, k++) {  
			//为什么从比head小的最大数-1开始呢，因为我们描400个点，其中一个点是磁头，那随机数有400个，所以跳过比head小的最大数字，相当于把这个数字替换成head
			System.out.println(k);
			lookList[k] = sortedRandomArray[i];
			lookList[0] += Math.abs(lookList[k] - lookList[k-1]);
		}
//		最后k = 400;
		lookList[0] -= head;
		return lookList;
	}
	
	public int[] getCscanList() {
		int closestNum = 0;                //大于head的最小的数字的下标 后面的++很重要
		int k = 1;                            //lookList的下标
		int[] cscanList = new int[400];
		int[] sortedRandomArray = new int[400];
		System.arraycopy(randomArray, 0, sortedRandomArray, 0, 400);
		Arrays.sort(sortedRandomArray);
		cscanList[0] = head;
		for(int i = 0; i < 400; i++) {
			if(head > sortedRandomArray[i]) {
				closestNum = i;		
			}
		}
//		System.out.println("head:" + head + "closetNum + 1:" + sortedRandomArray[closetNum + 1] + "closetNum:" + sortedRandomArray[closetNum]);
//		解除上句注释可证明closestNum存放小于head的最大的数字的下标
		closestNum++;
		for(int i = closestNum; i < 400; i++, k++) {
			System.out.println(k);
			cscanList[k] = sortedRandomArray[i];
			cscanList[0] += Math.abs(cscanList[k] - cscanList[k-1]);
		}
		System.out.print(k);
		for(int i = 0; i <= closestNum - 2 ;i++, k++) {  
			//为什么从比head小的最大数-1开始呢，因为我们描400个点，其中一个点是磁头，那随机数有400个，所以跳过比head小的最大数字，相当于把这个数字替换成head
			System.out.println(k);
			cscanList[k] = sortedRandomArray[i];
			cscanList[0] += Math.abs(cscanList[k] - cscanList[k-1]);
		}
//		最后k = 400;
		cscanList[0] -= head;
		return cscanList;
	}
	
	public int[] getSsftList() {
		int k = 1;
		int closestNum = 0;// closestNum存放小于head的最大的数字的下标，当中枢数用
		int rightSign = 1;
		int leftSign = 1;
		int[] sstfList = new int[400];
		int[] sortedRandomArray = new int[400];
		System.arraycopy(randomArray, 0, sortedRandomArray, 0, 400);
		Arrays.sort(sortedRandomArray);
		sstfList[0] = head;
		for(int i = 0; i < 400; i++) {
			if(head > sortedRandomArray[i]) {
				closestNum = i;		
			}
		}
		sortedRandomArray[closestNum] = head; //用head替换小于head最大数，逻辑清晰
		int nowIndex = closestNum;       //用NowIndex表示当前用作左右比较的数字的下标
		System.out.println(nowIndex);
		//比较左右两边哪边差的多，近者优先
		for(int i = 0; i < 399; i++, k++) {   //只循环399次，数组0号位不用装数字
			if(Math.abs(sortedRandomArray[nowIndex] - sortedRandomArray[closestNum - leftSign]) < Math.abs(sortedRandomArray[nowIndex] -
					sortedRandomArray[closestNum + rightSign])) {    //如果左边差值小
				sstfList[k] = sortedRandomArray[closestNum - leftSign];
				nowIndex = closestNum - leftSign;
				leftSign++;              //++完后就是左边下次用上的位置
				sstfList[0] += Math.abs(sstfList[k] - sstfList[k-1]);
			}else {    //右边差值小
				sstfList[k] = sortedRandomArray[closestNum + rightSign];
				nowIndex = closestNum + rightSign;
				rightSign++;             //++完后就是右边下次用上的位置
				sstfList[0] += Math.abs(sstfList[k] - sstfList[k-1]);
			}
			System.out.println("nowIndex" + nowIndex + "k:" + k);
			if(nowIndex == 0) {
				leftSign--;
				nowIndex = closestNum + rightSign;
			}else if(nowIndex == 399) {
				rightSign--;
				nowIndex = closestNum - leftSign;
			}
			
		}
		sstfList[0] -= head;
		return sstfList;
		//函数解析：用nowIndex当做当前数的移动指针   closestNum当做中轴数字，偏移量（rightSign）以它为中心偏移
		//差值比较即当前数字 减去 【中轴数+偏移量】做下标的数
	}
	
}


