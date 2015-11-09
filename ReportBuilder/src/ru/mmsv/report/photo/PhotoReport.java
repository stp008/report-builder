package ru.mmsv.report.photo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.VFS;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import ru.mmsv.report.Point;
import ru.mmsv.report.Report;
import ru.mmsv.report.discovery.Tags;
import ru.mmsv.report.utils.ExcelHelper;
import ru.mmsv.report.utils.ImageHelper;

public class PhotoReport implements Report{
	
	private static Log logger = LogFactory.getLog(PhotoReport.class);
	
	private final FileName name;
	
	private Map<String, List<Image>> reviews = new HashMap<>();
	
	private FileObject report = null;
	
	private boolean isBuild;
	
	public PhotoReport(FileName name, Map<Tags, List<Image>> images) {
		this.name = name;
		logger.info(images);
		parseReviews(images);
	}

	public String getName() {
		return name.getBaseName();
	}
	
	@Override
	public boolean isBuild() {
		return isBuild;
	}
	
	@Override
	public FileObject getReport() {
		return report;
	}
	
	@Override
	public FileObject build() {
		try {
		Workbook wb = ExcelHelper.createWorkbook(name.getBaseName().replace(".xlsx", ""));
		Sheet sheet = wb.getSheetAt(0);
		ExcelHelper.insertCell(sheet, 0, 0, name.getBaseName().replace(".xlsx", ""));
		Point nextPoint = new Point(1, 1);
		nextPoint = insertReview(wb, sheet, Constants.VIEW, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		nextPoint = insertReview(wb, sheet, Constants.POINT, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		if(reviews.containsKey(Constants.ALT_POINT) && !(reviews.get(Constants.ALT_POINT).isEmpty())) {
			nextPoint = insertReview(wb, sheet, Constants.ALT_POINT, new Point(nextPoint.getColumn()+2, nextPoint.getStartRow()-3), Constants.JPEG_FORMAT);
		}
		nextPoint = insertReview(wb, sheet, Constants.TERRITORY, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		nextPoint = insertReview(wb, sheet, Constants.ELECTRO_POINT, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		if(reviews.containsKey(Constants.ALT_ELECTRO_POINT) && !(reviews.get(Constants.ALT_ELECTRO_POINT).isEmpty())) {
			nextPoint = insertReview(wb, sheet, Constants.ALT_ELECTRO_POINT, new Point(nextPoint.getColumn()+2, nextPoint.getStartRow()-3), Constants.JPEG_FORMAT);
		}
		nextPoint = insertReview(wb, sheet, Constants.ELECTRICITY, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		nextPoint = insertReview(wb, sheet, Constants.GROUNDING, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		nextPoint = insertReview(wb, sheet, Constants.SIGNS, new Point(1, nextPoint.getRow()), Constants.JPEG_FORMAT);
		nextPoint = insertReview(wb, sheet, Constants.LTE, new Point(1, nextPoint.getRow()), Workbook.PICTURE_TYPE_PNG);
		nextPoint = insertReview(wb, sheet, Constants.SATELLITE, new Point(nextPoint.getColumn()+2, nextPoint.getStartRow()-3), Workbook.PICTURE_TYPE_PNG);
		ExcelHelper.writeToWorkbook(wb, name.getPath());
		report = VFS.getManager().resolveFile(name.toString());
		isBuild = true;
		return report;
		} catch(Exception e) {
			logger.error(e.getStackTrace(), e);
		}
		return report;
	}
	
	private void parseReviews(Map<Tags, List<Image>> images) {
		for (Tags tag : images.keySet()) {
			switch(tag) {
			case ALTELECTROPOINT_TAG:
				reviews.put(Constants.ALT_ELECTRO_POINT, images.get(tag));
				break;
			case ALTPOINT_TAG:
				reviews.put(Constants.ALT_POINT, images.get(tag));
				break;
			case ELECTRICITY_TAG:
				reviews.put(Constants.ELECTRICITY, images.get(tag));
				break;
			case ELECTROPOINT_TAG:
				reviews.put(Constants.ELECTRO_POINT, images.get(tag));
				break;
			case GROUNDING_TAG:
				reviews.put(Constants.GROUNDING, images.get(tag));
				break;
			case LTE_TAG:
				reviews.put(Constants.LTE, images.get(tag));
				break;
			case POINT_TAG:
				reviews.put(Constants.POINT, images.get(tag));
				break;
			case SATELLITE_TAG:
				reviews.put(Constants.SATELLITE, images.get(tag));
				break;
			case TERRITORY_TAG:
				reviews.put(Constants.TERRITORY, images.get(tag));
				break;
			case VIEW_TAG:
				reviews.put(Constants.VIEW, images.get(tag));
				break;
			case SIGNS_TAG:
				reviews.put(Constants.SIGNS, images.get(tag));
				break;
			default:
				break;			
			}
		}		
	}
	
	private Point insertReview(Workbook wb, Sheet sheet, String name, Point point, int format) {
		if(reviews.containsKey(name) && !(reviews.get(name).isEmpty())) {
			int row = point.getRow();
			ExcelHelper.insertCell(sheet, point.getColumn(), row = row+2, name);
			logger.info("Current processing tag: " + name);
			Point thresholdPoint = ImageHelper.insertImagesInARow(wb, sheet, reviews.get(name), new Point(point.getColumn(), ++row), format);
			return thresholdPoint;
		}
		return point;
	}
	
}
