package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {

	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if (origin == null || origin.length == 0) {
			return;
		}
		for (int i = 0 ,j = origin.length - 1 ; i < j ; i++ ,j--) {
			int temp = origin[i];
			origin[i] = origin[j];
			origin[j] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		//先判断是否为空
		if (oldArray == null ) {
			return null;
		}
		//定义个计算器
		int count = 0;
		//定义个临时变量
		int[] temp = new int[oldArray.length];
		//变量oldArray，统计不为0的数添加到临时数组
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				temp[count++] = oldArray[i];
			}
		}
		//用copyof复制temp数组的count长度到新的数组，返回
		return Arrays.copyOf(temp, count);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		//判断如都为null返回null
		if (array1==null && array2 == null) {
			return null;
		}
		//一个为null另一个不为null，则返回不为null的数组
		if (array1 == null && array2 != null) {
			return array2;
		}
		if (array2 == null && array1 != null) {
			return array1;
		}
		//定义一个新的临时数组，长度为a1+a2;
		int[] temp = new int[array1.length+array2.length];
		int count = 0;//temp 的下标
		int i = 0; //array1的下标
		int j = 0; //array2的下标
		while (i < array1.length && j<array2.length) { 	//当一个数组遍历完跳出
			if (array1[i] < array2[j]) {				//如果array[i]小
				temp[count++] = array1[i++];			//新数组就添加array[i],并同时自增
			}else if (array1[i] > array2[j]) {			//反之一样
				temp[count++] = array2[j];
			}else {
				temp[count++] = array2[j++];			//当相等的时候，随便添加一个，然后都自增
				i++;
			}
		}
		
		while (i == array1.length && j < array2.length) {//当一个数组结束后，添加另外的随着的所有元素
			temp[count++] = array2[j++];
		}
		
		while (j == array2.length  && i < array1.length) {
			temp[count++] = array1[i++];
		}
		
		return  Arrays.copyOf(temp, count);
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		//判断如果为null,则返回null
		if (oldArray == null) {
			return null;
		}
		//如果size小于0,则抛出异常
		if (size < 0) {
			throw new IndexOutOfBoundsException("size小于0");
		}
		//定义新数组，长度为oldArray,length+size;
		int[] newArray = new int[oldArray.length + size];
		//复制数据
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray ;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		//当max为1的时候，返回空数组
		if (max == 1) {
			return new int[0];
		}
		//当max为2的时候返回数组{1,1}
		if (max == 2) {
			return new int[]{1,1};
		}
		//定义新数组；
		int[] arr  = new int[max];
		arr[0] = 1;
		arr[1] = 1;
		//定义一个计数器计算数组的长度
		int count = 2;
		for (int i = 2; i < arr.length; i++) {
			arr[i] = arr[i-1]+arr[i-2];
			//当arr[i]>=max 的时候跳出
			if (arr[i] >= max) {
				break;
			}else {
				count++;
			}
		}
		return Arrays.copyOf(arr, count);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if (max < 3 && max > 0) {
			return new int[0];
		}
		
		int[] arr = new int[max];
		int count = 0;
		for (int i = 2; i < max; i++) {
			if (isPrime(i)) {
				arr[count++] = i;
			}
		}
		return Arrays.copyOf(arr, count);
	}
	
	private boolean isPrime(int i) {
		int n = 2;
		while (n < i) {
			if (i % n == 0) {
				break;
			}else {
				i++;
			}
		}
		return i == n;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max <=2) {
			return new int[0];
		}
		int[] arr = new int[max];
		int count = 0;
		for (int i = 2; i < max; i++) {
			if (isPerfectNum(i)) {
				arr[count++] = i;
			}
		}
		return Arrays.copyOf(arr, count);
	}
	
	private boolean isPerfectNum(int i) {
		int sum = 0;
		for (int j = 1; j < i; j++) {
			if (j % i == 0) {
				sum+=j;
			}
		}
		return sum == i;
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		if (array == null) {
			return null;
		}
		if (array.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length-1) {
				sb.append(array[i]);
			}else {
				sb.append(array[i]+seperator);
			}
		}
		return sb.toString();
	}
	

}
