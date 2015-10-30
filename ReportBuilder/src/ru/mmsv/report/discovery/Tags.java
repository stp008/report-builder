package ru.mmsv.report.discovery;

public enum Tags {
	
	VIEW_TAG("���._���"),
	POINT_TAG("��������_�����"),
	ALTPOINT_TAG("����._�����"),
	ELECTROPOINT_TAG("��������_�����_��������������"),
	ALTELECTROPOINT_TAG("����._�����_��������������"),
	TERRITORY_TAG("����._���."),
	ELECTRICITY_TAG("�����_��������������"),
	GROUNDING_TAG("����������"),
	SIGNS_TAG("�����������"),
	LTE_TAG("LTE"),
	SATELLITE_TAG("����������");
	
	private String tag;
	
	private Tags(String tag) {
		this.tag = tag;
	}
	
	public String getTagName() {
		return tag;
	}
}
