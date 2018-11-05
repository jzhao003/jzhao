package ssq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import test.test.DbUtil;

public class GenerateMyNumber {

	public static void main(String[] args) {

		new GenerateMyNumber().calculateTotal();
//		new GenerateMyNumber().generateRedRandom();
	}


	public List<Integer> generateRedRandom() {
		Random rn = new Random();
		Set<Integer> set = new HashSet<Integer>();
		while (set.size()!=6) {
			set.add(rn.nextInt(33) + 1);
		}
		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		return list;
	}
	
	private List<Integer> generateRandomSumBetweenNumber(int max,int min) {
		List<Integer> list = generateRedRandom();
		int sum = getListSumValue(list);
		if (min<sum && sum<max) {
			System.out.println(list);
		} else {
			generateRandomSumBetweenNumber(max,min);
		}
		return list;
	}

	private int getListSumValue(List<Integer> list) {
		int sum = 0;
		for (Integer value: list) {
			sum = sum + value;
		}
		return sum;
	}
	
	public void calculateTotal() {
		List<Map<String, String>> list = DbUtil.execteQuerySql(
				"select lottery_date,red1,red2,red3,red4,red5,red6,blue,red1+red2+red3+red4+red5+red6 as total from ssq order by total");
//		int totalRecordsCount = list.size();
//		blue(totalRecordsCount);
		red(list);

	}

	public List<Integer> red() {
		List<Map<String, String>> list = DbUtil.execteQuerySql(
				"select lottery_date,red1,red2,red3,red4,red5,red6,blue,red1+red2+red3+red4+red5+red6 as total from ssq order by total");
		return red(list);
	}
	
	public List<Integer> red(List<Map<String, String>> list) {
		int minTotal = Double.valueOf((list.get(0).get("total"))).intValue();
		int maxTotal = Double.valueOf(list.get(list.size() - 1).get("total")).intValue();
//		double avgTotal;
		double total = 0;
		for (int i = 0; i < list.size(); i++) {
			total = total + Double.valueOf(list.get(i).get("total"));
		}
//		avgTotal = total / list.size();
//		System.out.println(minTotal);
//		System.out.println(maxTotal);
//		System.out.println(avgTotal);
		return test(0.8, list, maxTotal, minTotal);
	}

	private List<Integer> test(double prectage, List<Map<String, String>> list, int max, int min) {
		int count = 0;
		int num = (int) (list.size() * prectage);
		int x = min;
		int y = max;
		while (true) {
			if (x >= y) {
				break;
			}
			for (int i = 0; i < list.size(); i++) {
				if ((Double.valueOf(list.get(i).get("total")) >= x)
						&& (Double.valueOf(list.get(i).get("total")) <= y)) {
					count++;
				}
			}
			if (count >= num) {
				count = 0;
			} else {
				break;
			}
			x++;
			y--;
		}
		System.out.println(x);
		System.out.println(y);
		return generateRandomSumBetweenNumber(y,x);
	}

	public String blue() {
		List<Map<String, String>> list = DbUtil
				.execteQuerySql("select blue,count(blue) as total from ssq group by blue order by total");
//		for (int i = 0; i < list.size(); i++) {
//			double d = Integer.valueOf(list.get(i).get("total")) / (double) totalCount;
//			if (d < 0.0625) {
//				System.out.println(list.get(i).get("blue") + " blue number: " + d);
//			}
//		}
		return list.get(0).get("blue");
	}
}
