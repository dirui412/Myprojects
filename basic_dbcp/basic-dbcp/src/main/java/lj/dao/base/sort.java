package lj.dao.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class sort {
	//对数组排序
	static char[] sort1(String a)
	{
		char[] chrCharArray; //创建一个字符数组chrCharArray
		chrCharArray = a.toCharArray();
		  for (int i = 0; i < chrCharArray.length; i++)
		    {
		        for (int j = i; j < chrCharArray.length; j++)
		        {
		            if (chrCharArray[i] >chrCharArray[j])
		            {
		                char temp = chrCharArray[i];
		                chrCharArray[i] = chrCharArray[j];
		                chrCharArray[j] = temp;
		            }
		        }
		    }
		return chrCharArray;
	}
	static //排序输出
	String sort2(char[] a)
	{
		String anwser = null; //最终的结果数组
		StringBuffer sb = new StringBuffer(anwser);
		int num = 0;//有多少个不同的字母
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(String.valueOf(a[0]), "1");
		for(int i = 1; i< a.length; i++)
		{
			if(a[i]==a[i-1])
			{
				String s = map.get(String.valueOf(a[i])); //a[i]个数
				map.put(String.valueOf(a[i]), s+1);
			}
			else
			{
				map.put(String.valueOf(a[i]), "1");
			}
		}
		
		num = map.size();//有多少个不同的字母
		char[] a2 = new char[num];//存放不同的字母
		
			char[] a1;//暂时存放key
			int count1 = 0;
			for (Entry<String, String> entry : map.entrySet()) 
			{
				String key = entry.getKey();
				a1 = key.toCharArray();
				a2[count1] = a1[0];
				count1++;
			}
			
			int[] b2 = new int[num];//存放不同字母的对应个数
			int count2 = 0;
			for (Entry<String, String> entry : map.entrySet()) 
			{
				String value = entry.getValue();
				b2[count2] = value.indexOf(0);;
				count2++;
			}
			//求数量最大的字母个数
			int max=0;
			for(int i=0; i<b2.length; i++)
			{
				if(b2[i]>max)
					max = b2[i];
			}
			while(max!=0)
			{
				for(int i=0; i<num; i++)
				{
					if(b2[i]!=0)
					{
						sb.append(a2[i]);
						b2[i]--;
					}
				}
				max--;
			}
			anwser = sb.toString();
			return anwser;
	}
	public static void main(String[] args) {
		System.out.println("请输入");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String answer = sort2(sort1(s));
		System.out.println(answer);
		sc.close();
	}

}
