package ru.mmsv.report.photo;

import org.apache.poi.ss.usermodel.Workbook;

public class Constants {
	
	public static final String VIEW = "Общий вид";
	
	public static final String POINT = "Опора:";
	
	public static final String ALT_POINT = "Альтернативная опора:";
	
	public static final String ELECTRO_POINT = "Точка подключения электропитания:";
	
	public static final String ALT_ELECTRO_POINT = "Альтернативная точка подключения электропитания:";
	
	public static final String TERRITORY = "Прилегающая территория:";
	
	public static final String ELECTRICITY = "Линии электросети:";
	
	public static final String GROUNDING = "Заземление:";
	
	public static final String SIGNS = "Обозначения и другие особенности:";
	
	public static final String LTE = "GSM-LTE связь:";
	
	public static final String SATELLITE = "Спутники и координаты:";
	
	public static final int JPEG_FORMAT = Workbook.PICTURE_TYPE_JPEG;
		
	private Constants(){}
	
}
