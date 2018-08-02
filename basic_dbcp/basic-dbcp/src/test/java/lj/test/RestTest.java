package lj.test;

import java.util.HashMap;

import lj.util.HttpUtils;

public class RestTest {

	public static void main(String[] args) {
		HashMap<String,String> params=new HashMap<String,String>();
		params.put("userName","testttt");
		String str=HttpUtils.doPost("http://localhost:8888/pllm/rest/userInfo",new HashMap<String,String>(),params);
		System.out.println("return:"+str);
	}

}
