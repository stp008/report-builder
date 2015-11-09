package ru.mmsv.report;

import ru.mmsv.report.discovery.Discovery;

public class Main {
	
	public static void main(String[] args) {
		//String folderPath = "file://C:/Users/Just a man/Desktop/Тестовая";
		String folderPathNew = "file://C:/Users/Just a man/Desktop/Бригада5";
		Discovery.processPhotoReports(folderPathNew);
		//Discovery.replaceDatesAndTimes(folderPathNew);
	}

}
