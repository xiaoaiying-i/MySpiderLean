package com.xiaoai.oldcrawler;

import java.util.Random;

/**
 * [1]假设排列着100个乒乓球。由两个人轮流拿球装入口袋。能拿到第100个乒
乓球的人为胜利者。条件满足:每次拿球者至少要拿1个。但最多不能超过5个。问:
如果你是最先拿球的人。你该拿几个以后怎么拿就能保证你能得到第100个乒
乓球 
 * @author xiaoaiying
 *
 */
public class t016TakeTheBall {
	
	public static void main(String[] args) {
		int sum = 100;//球的总数
		//第一个人一开始拿4个球，剩下96球即6的16倍，后面不管第二人拿几个球都第一个人都要拿两人加起来等于6个，那么最后第100个球肯定是第一个人获得
		int one = 4;//第一个人
		int tow;//第二个人
		String now = "one";//记录当前拿球者
		//第一个人先拿球
		sum-=one;
		System.out.println("one拿球："+one);
		
		//用于生成随机数
		Random random = new Random();
        
		while (sum>0) {
			//第二个人拿球
			tow = random.nextInt(5)%5 + 1;//第二个拿球随机生成1-5之间的数
			sum-=tow;
			System.out.println("tow拿球："+tow);
			now = "tow";
			
			System.out.println("剩余球数："+sum);
			System.out.println("--------------------------------");
			//当第二个人拿完1球也要判断是否已经结束，不然由于循环最后now=“one”，如果不判断那最后胜利者肯定输出为one
			if (sum<=0) {
				break;
			}
			
			//第一个人拿球
			if(sum<=5) {
				one=sum;
			}else {
				one = 6-tow;
			}
			sum-=one;
			System.out.println("one拿球："+one);
			now = "one";
			
		}
		System.out.println("胜利者为："+now);
		
	}

}
