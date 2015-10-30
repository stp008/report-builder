package ru.mmsv.report.discovery;

public enum Tags {
	
	VIEW_TAG("общ._вид"),
	POINT_TAG("основная_опора"),
	ALTPOINT_TAG("альт._опора"),
	ELECTROPOINT_TAG("основная_точка_электропитания"),
	ALTELECTROPOINT_TAG("альт._точка_электропитания"),
	TERRITORY_TAG("прил._тер."),
	ELECTRICITY_TAG("линии_электропитания"),
	GROUNDING_TAG("заземление"),
	SIGNS_TAG("обозначения"),
	LTE_TAG("LTE"),
	SATELLITE_TAG("Координаты");
	
	private String tag;
	
	private Tags(String tag) {
		this.tag = tag;
	}
	
	public String getTagName() {
		return tag;
	}
}
