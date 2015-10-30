package ru.mmsv.report.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;

import ru.mmsv.report.discovery.Tags;
import ru.mmsv.report.photo.Image;

public class DiscoveryHelper {
	
	private static Log logger = LogFactory.getLog(DiscoveryHelper.class);
	
	public static Map<Tags, List<Image>> getImageMap (FileObject folder) throws Exception {
		Map<Tags, List<Image>> images = new HashMap<>();	
		
		List<Image> viewList = new ArrayList<>();
		images.put(Tags.VIEW_TAG, viewList);
		List<Image> pointList = new ArrayList<>();
		images.put(Tags.POINT_TAG, pointList);
		List<Image> altpointList = new ArrayList<>();
		images.put(Tags.ALTPOINT_TAG, altpointList);
		List<Image> electropointList = new ArrayList<>();
		images.put(Tags.ELECTROPOINT_TAG, electropointList);
		List<Image> altelectroPointList = new ArrayList<>();
		images.put(Tags.ALTELECTROPOINT_TAG, altelectroPointList);
		List<Image> territoryList = new ArrayList<>();
		images.put(Tags.TERRITORY_TAG, territoryList);
		List<Image> electricityList = new ArrayList<>();
		images.put(Tags.ELECTRICITY_TAG, electricityList);
		List<Image> groundingList = new ArrayList<>();
		images.put(Tags.GROUNDING_TAG, groundingList);
		List<Image> lteList = new ArrayList<>();
		images.put(Tags.LTE_TAG, lteList);
		List<Image> satelliteList = new ArrayList<>();
		images.put(Tags.SATELLITE_TAG, satelliteList);
		List<Image> signsList = new ArrayList<>();
		images.put(Tags.SIGNS_TAG, signsList);
		
		for (FileObject child : folder.getChildren()) {
			String name = child.getName().getBaseName();			
			
			if(name.contains(Tags.VIEW_TAG.getTagName())) {
				logger.info("View: " + child.getName());
				viewList.add(new Image(child));		
			}
			if(name.contains(Tags.POINT_TAG.getTagName())) {
				logger.info("Point: " + child.getName());
				pointList.add(new Image(child));		
			}
			if(name.contains(Tags.ALTPOINT_TAG.getTagName())) {
				logger.info("Alternative point: " + child.getName());
				altpointList.add(new Image(child));			
			}
			if(name.contains(Tags.ELECTROPOINT_TAG.getTagName())) {
				logger.info("Electric point: " + child.getName());
				electropointList.add(new Image(child));		
			}
			if(name.contains(Tags.ALTELECTROPOINT_TAG.getTagName())) {
				logger.info("Alternative electric point: " + child.getName());
				altelectroPointList.add(new Image(child));		
			}
			if(name.contains(Tags.TERRITORY_TAG.getTagName())) {
				logger.info("Land: " + child.getName());
				territoryList.add(new Image(child));		
			}
			if(name.contains(Tags.ELECTRICITY_TAG.getTagName())) { 
				logger.info("Electric roads: " + child.getName());
				electricityList.add(new Image(child));			
			}
			if(name.contains(Tags.GROUNDING_TAG.getTagName())) {
				logger.info("Grounding: " + child.getName());
				groundingList.add(new Image(child));		
			}
			if(name.contains(Tags.SIGNS_TAG.getTagName())) { 
				logger.info("Signs: " + child.getName());
				signsList.add(new Image(child));				
			}
		}
		
		for(FileObject parent : folder.getParent().getChildren()){
			String name = parent.getName().getBaseName();
			if(name.contains(Tags.LTE_TAG.getTagName())) {
				logger.info("LTE signal level: " + parent.getName());
				lteList.add(new Image(parent));		
			}
			if(name.contains(Tags.SATELLITE_TAG.getTagName())) { 
				logger.info("Satellites: " + parent.getName());
				satelliteList.add(new Image(parent));				
			}
		}
		
		return images;
	}
	
}
