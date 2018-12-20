package com.hw.csdn.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static String dateToLongString(Date date){
        if(date == null)
            return null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
    public static void main(String[] args) {

        List<Integer> integers=new ArrayList<>();
        integers.add(6);
        integers.add(4);
        integers.add(8);
        integers.add(3);
        integers.add(2);
        integers.add(1);
        integers.add(10);
        //冒泡排序
        /*for (int i = 0; i < integers .size(); i++)    {
            for (int j = integers .size()-1; j > i; j--)  {
                int no= integers .get(j);
                int no1= integers .get(j-1);
                if (no<no1){
                    //互换位置
                    int a=integers.get(j);
                    integers.set(j,integers.get(j-1));
                    integers.set(j-1,a);
                }
            }
        }*/
        //选择排序
        /*for(int i = 0; i < integers.size() - 1; i++) {// 做第i趟排序
            int k = i;
            for(int j = k + 1; j < integers.size(); j++){// 选最小的记录
                if(integers.get(j) < integers.get(k)){
                    k = j; //记下目前找到的最小值所在的位置
                }
            }
            //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换
            if(i != k){
                int temp = integers.get(i);
                int temp2=integers.get(k);
                integers.set(i,temp2);
                integers.set(k,temp);
            }
        }*/
        for (Integer integer: integers) {
            System.out.println(integer);
        }
    }
}
