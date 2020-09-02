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
             *     ���� �Ѿ�ȫ���������У�����������Ƴ��ڰ�sstf����ssft����������ȫ���޸ķ���bug�ܶ࣬���Բ����޸�           
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
	int[] randomArray = getRandomArray(); //�Ѿ����ɺã�������ʱ���������� 


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
		lookTextField.setText("LOOKѰ������" + lookList[0]);
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
		cscanTextField.setText("CSCANѰ������" + cscanList[0]);
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
		ssftTextField.setText("SSTFѰ������" + ssftList[0]);
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
		fcfsTextField.setText("FCFSѰ������" + fcfsList[0]);
		
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
	
	public int[] getRandomArray() {          //����֤���պ�400����Ҫ��������
		int count1 = 0;    //0-499���ּ���
		int count2 = 0;    //500-999���ּ���
		int count3 = 0;    //1000-1499���ּ���
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
		int closestNum = 0;                //����head����С�����ֵ��±�
		int k = 1;                            //lookList���±�
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
//		����Ͼ�ע�Ϳ�֤��closestNum���С��head���������ֵ��±�
		closestNum++;
		for(int i = closestNum; i < 400; i++, k++) {
			System.out.println(k);
			lookList[k] = sortedRandomArray[i];
			lookList[0] += Math.abs(lookList[k] - lookList[k-1]);
		}
		System.out.print(k);
		for(int i = closestNum - 2; i >= 0 ;i--, k++) {  
			//Ϊʲô�ӱ�headС�������-1��ʼ�أ���Ϊ������400���㣬����һ�����Ǵ�ͷ�����������400��������������headС��������֣��൱�ڰ���������滻��head
			System.out.println(k);
			lookList[k] = sortedRandomArray[i];
			lookList[0] += Math.abs(lookList[k] - lookList[k-1]);
		}
//		���k = 400;
		lookList[0] -= head;
		return lookList;
	}
	
	public int[] getCscanList() {
		int closestNum = 0;                //����head����С�����ֵ��±� �����++����Ҫ
		int k = 1;                            //lookList���±�
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
//		����Ͼ�ע�Ϳ�֤��closestNum���С��head���������ֵ��±�
		closestNum++;
		for(int i = closestNum; i < 400; i++, k++) {
			System.out.println(k);
			cscanList[k] = sortedRandomArray[i];
			cscanList[0] += Math.abs(cscanList[k] - cscanList[k-1]);
		}
		System.out.print(k);
		for(int i = 0; i <= closestNum - 2 ;i++, k++) {  
			//Ϊʲô�ӱ�headС�������-1��ʼ�أ���Ϊ������400���㣬����һ�����Ǵ�ͷ�����������400��������������headС��������֣��൱�ڰ���������滻��head
			System.out.println(k);
			cscanList[k] = sortedRandomArray[i];
			cscanList[0] += Math.abs(cscanList[k] - cscanList[k-1]);
		}
//		���k = 400;
		cscanList[0] -= head;
		return cscanList;
	}
	
	public int[] getSsftList() {
		int k = 1;
		int closestNum = 0;// closestNum���С��head���������ֵ��±꣬����������
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
		sortedRandomArray[closestNum] = head; //��head�滻С��head��������߼�����
		int nowIndex = closestNum;       //��NowIndex��ʾ��ǰ�������ұȽϵ����ֵ��±�
		System.out.println(nowIndex);
		//�Ƚ����������ı߲�Ķ࣬��������
		for(int i = 0; i < 399; i++, k++) {   //ֻѭ��399�Σ�����0��λ����װ����
			if(Math.abs(sortedRandomArray[nowIndex] - sortedRandomArray[closestNum - leftSign]) < Math.abs(sortedRandomArray[nowIndex] -
					sortedRandomArray[closestNum + rightSign])) {    //�����߲�ֵС
				sstfList[k] = sortedRandomArray[closestNum - leftSign];
				nowIndex = closestNum - leftSign;
				leftSign++;              //++����������´����ϵ�λ��
				sstfList[0] += Math.abs(sstfList[k] - sstfList[k-1]);
			}else {    //�ұ߲�ֵС
				sstfList[k] = sortedRandomArray[closestNum + rightSign];
				nowIndex = closestNum + rightSign;
				rightSign++;             //++�������ұ��´����ϵ�λ��
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
		//������������nowIndex������ǰ�����ƶ�ָ��   closestNum�����������֣�ƫ������rightSign������Ϊ����ƫ��
		//��ֵ�Ƚϼ���ǰ���� ��ȥ ��������+ƫ���������±����
	}
	
}


