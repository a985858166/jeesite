package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.common.utils.DateUtils;

public class Test {

	public static void main(String[] args) {
		//测试文件
	}
	@org.junit.Test
	public void test(){
        for (int i = 0; i < 100; i++) {
            String orderOn = DateUtils.getDate("yyyyMMdd").concat(String.valueOf((int)((Math.random()*9+1)*1000)));
            System.out.println(orderOn);
        }

    }
    @org.junit.Test
    public void test2(){

    }
}
