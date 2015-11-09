package ru.mmsv.report.discovery;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.VFS;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ru.mmsv.report.Report;
import ru.mmsv.report.photo.Image;
import ru.mmsv.report.photo.PhotoReport;
import ru.mmsv.report.utils.DiscoveryHelper;
import ru.mmsv.report.utils.ExcelHelper;

public class Discovery {
	
	private static Log logger = LogFactory.getLog(Discovery.class);
	
	public static boolean processPhotoReports(String path) {
		try {
			FileSystemManager fsManager = VFS.getManager();
			FileObject diskC = fsManager.resolveFile(path);
			for (FileObject report : diskC.getChildren()) {
				String name = "Фотоотчет обследования (" + report.getName().getBaseName().substring(0, 5) + ").xlsx";
				if(report.getType() == FileType.FOLDER && report.getChild("Фото") != null) {
					Map<Tags, List<Image>> images = DiscoveryHelper.getImageMap(report.getChild("Фото"));
					Report rep = new PhotoReport(VFS.getManager().resolveFile(report.getName().toString() + "/" + name).getName(), images);
					rep.build();
				}
			}
			logger.info("Processing finished");
			return true;
		}catch (Exception e) {
		   e.printStackTrace();
		   return false;
		}	
	}
	
	@SuppressWarnings("deprecation")
	public static boolean replaceDatesAndTimes(String path) {
		String fontName = "Arial";
		int fontHeight = 14;
		Calendar initDate = Calendar.getInstance();
		initDate.set(2015, 10, 19, 10, 00);
		Date initTime = initDate.getTime();
		
		try {
			FileSystemManager fsManager = VFS.getManager();
			FileObject diskC = fsManager.resolveFile(path);
			for (FileObject folder : diskC.getChildren()) {
				if (folder.getType() == FileType.FILE)
					continue;
				for (FileObject report : folder.getChildren()) {
					String wbName = report.getName().getPath();
					String name = "Отчет обследования";
					if(report.getName().getBaseName().contains(name)) {
						int startHours = initTime.getHours();
						int startMinutes = initTime.getMinutes();
						initTime.setTime(initTime.getTime() + 1800000L);
						int finishHours = initTime.getHours();
						int finishMinutes = initTime.getMinutes();
						
						String timeStr = "Время прибытия - " + startHours + "-" + startMinutes + 
								" \n Время отъезда - " + finishHours + "-" + finishMinutes + " \n Повторно (да/нет) нет";
						Workbook wb = ExcelHelper.openWorkbook(wbName);
						String dateStr = initTime.getDate() + "." + initTime.getMonth() + "." + (initTime.getYear() + 1900);
						Sheet sheet = wb.getSheetAt(0);
						ExcelHelper.insertCell(wb, sheet, 2, 2, dateStr, fontName, fontHeight);
						ExcelHelper.insertCell(wb, sheet, 2, 14, timeStr, fontName, fontHeight);
						ExcelHelper.insertCell(wb, sheet, 2, 30, "Дата: " + dateStr, fontName, fontHeight);
						ExcelHelper.writeToWorkbook(wb, wbName);
						
						initTime.setTime(initTime.getTime() + 3600000L);
						
						if (finishHours > 18) {
							initTime.setTime(initTime.getTime() + 57600000L);
						}
						
					}
				}
			}
			logger.info("Processing finished");
			return true;
		}catch (Exception e) {
		   e.printStackTrace();
		   return false;
		}	
	}
	
}
