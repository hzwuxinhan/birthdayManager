package com.edu.util;

import java.util.Calendar;


/**********
 * 
 * �й����ڷ���ļ�����
 * ��ǰ�涨������1900���Ժ�ģ�1900����ǰ���ܻ��������
 * @author bing
 *
 */
public class DayUtil {
	
	private static String[] stemNames = {"��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};
	private static String[] branchNames = {"��", "��", "��", "î", "��", "��", "��", "δ", "��", "��","��","��"};
	private static int[] daysInGrerianMont = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private static final int BASEYEAR = 1900;
	private static final int BASEMONTH = 1;
	private static final int BASEDAY = 1;
	private static final int BASEDAYOFWEEK = 1;
	

	//���ؽ�������
	public static int getCurYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	
	//���ؽ������
	public static int getCurMonth(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH)+1;
	}
	
	//���ؽ������
	public static int getCurDay(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
	
	//����������ݼ�����������Ф
	public static String getAnimals(int year){
		String s = "�Ｆ������ţ������������";
		int index = (year % 12);
		return Character.toString(s.charAt(index));
	}
	
	//���������·�������
	public static String getConstellation(int mouth, int day){
		String s = "Ħ��ˮƿ˫������ţ˫�Ӿ�зʨ�Ӵ�Ů�����Ы����Ħ��";
		int days[] = {20,19,20,20,21,21,22,23,23,23,22,21,20};
		int index = ((day <= days[mouth -1])?0:2) + (mouth -1) *2;
		return s.substring(index, index+2) + "��";
	}
	
	//��������ж��Ƿ�������
	public static boolean isLeapYear(int year){
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
			return true;
		}
		return false;
	}
	
	//�ж����ڴ�С
	public static boolean isBig(int year1, int month1, int day1, int year2, int month2, int day2){
		if(year1 > year2){
			return true;
		}else if(year1 == year2){
			if(month1 > month2){
				return true;
			}else if(month1 == month2){
				if(day1 >= day2){
					return true;
				}
			}
		}
		return false;
	}
	
	//�õ���һ������
	public static int getNextLeapYear(int year){
		if(isLeapYear(year)){
			year +=1;
		}
		while(isLeapYear(year) == false){
			year += 1;
		}
		return year;
	}
	
	//���㵽��һ������������Ҫ��ʱ��
	public static int getResidueToNextBirthday(int year,int month,int day){
		int CurYear,CurMonth,CurDay;
		Calendar calendar = Calendar.getInstance();
		CurYear = DayUtil.getCurYear();
		CurMonth = DayUtil.getCurMonth();
		CurDay = DayUtil.getCurDay();
		Calendar calendar2 = Calendar.getInstance();
		if(DayUtil.isLeapYear(year) && month == 2 && day == 29){
			year = CurYear;
			//�������Ҳ������
			if(DayUtil.isLeapYear(CurYear) && DayUtil.isBig(year, month, day, CurYear, CurMonth, CurDay)){
			}else{
				year +=1;
				while(DayUtil.isLeapYear(year) == false){
					year += 1;
				}
			}
		}else{
			year = CurYear;
			if(DayUtil.isBig(year, month, day, CurYear, CurMonth, CurDay)){
			}else{
				year += 1;
			}
		}
		calendar2.set(year, month-1, day);
		long residueDay = (calendar2.getTimeInMillis() - calendar.getTimeInMillis())/(24*60*60*1000);
		return (int)residueDay;
	}
	
	//�õ���һ����������
	public static int getNextBirthdayYear(int year, int month, int day){
		int CurYear,CurMonth,CurDay;
		Calendar calendar = Calendar.getInstance();
		CurYear = getCurYear();
		CurMonth = getCurMonth();
		CurDay = getCurDay();
		while(DayUtil.isBig(CurYear, CurMonth, CurDay, year, month, day)){
			year += 1;
		}
		return year;
	}
	
	//�õ�����
	//�������������
	public static int getAge(int year, int month, int day){
		int age = 0;
		int CurYear = getCurYear();
		int CurMonth = getCurMonth();
		int CurDay = getCurDay();
		while(isBig(CurYear, CurMonth, CurDay, year, month, day)){
			year += 1;
			age += 1;
		}
		return age;
	}
	
	//�õ���ɵ�֧��
	public static String getChineseEra(int year){
		int index = (year - 4) % 60;
		return stemNames[index % 10] + branchNames[index % 12];
	}
	
	//���㵱ǰ�����ڱ����еĵڼ���
	public static int dayofYear(int year, int month, int day){
		int num = 0;
		for(int i = 1; i < month; i ++){
			num += daysInGregorianMonth(year, i);
		}
		num += day;
		return num;
	}
	
	//����ĳ��ĳ�µ�����
	public static int daysInGregorianMonth(int year, int month){
		int num = 0;
		num = daysInGrerianMont[month-1];
		if(isLeapYear(year) && month == 2){
			num += 1;
		}
		return num;
	}
	
	//����ĳ�������
	public static int daysInGregorianYear(int year){
		int num = 0;
		num = 365;
		if(isLeapYear(year)){
			num += 1;
		}
		return num;
	}
	
	//�ƶ�ĳ��ĳ��ĳ�������ڼ�,0-1,1-2,2-3,3-4,4-5,5-6,6-7
	public static int dayOfWeek(int year, int month, int day){
		int num = 0;
		for(int i = BASEYEAR; i < year; i ++){
			num += daysInGregorianYear(year);
		}
		for(int i = 1; i < month; i ++){
			num += daysInGregorianMonth(year, i);
		}
		num += day;
		return num % 7;
	}
}