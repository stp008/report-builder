package ru.mmsv.report.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;

import ru.mmsv.report.Point;
import ru.mmsv.report.photo.Image;

public class ImageHelper {
	
	public static XSSFClientAnchor insertImage (Workbook wb, Sheet sheet, Image image, Point point, int format) {
		int pictureId = wb.addPicture(image.getBytes(), format);
		Drawing drawing = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = new XSSFClientAnchor();
		anchor.setCol1(point.getColumn());
		anchor.setRow1(point.getRow());
		XSSFPicture picture = (XSSFPicture) drawing.createPicture(anchor, pictureId);		   
		picture.resize(0.09);
		if (format == Workbook.PICTURE_TYPE_PNG)
			picture.resize(0.4);
		return anchor;
	}
	
	public static Point insertImagesInARow (Workbook wb, Sheet sheet, List<Image> images, Point point, int format) {
		int tempColFrom = point.getColumn();
		int thresholdRow = 0;
		int thresholdCol = 0;
		XSSFClientAnchor anchor;
		for (Image image : images) {
			anchor = insertImage(wb, sheet, image, new Point(tempColFrom, point.getRow()), format);	
			tempColFrom = anchor.getTo().getCol() + 1;
			thresholdCol = anchor.getTo().getCol();
			int tempRow = anchor.getTo().getRow();
			if (thresholdRow < tempRow)
				thresholdRow = tempRow;
		}
		Point nextPoint = new Point(thresholdCol, thresholdRow);
		nextPoint.setStartColumn(point.getColumn());
		nextPoint.setStartRow(point.getRow());
		
		return nextPoint;
	}
	
}
