package ru.mmsv.report;

import org.apache.commons.vfs2.FileObject;

public interface Report {
	
	public boolean isBuild();
	
	public FileObject build();

	FileObject getReport();

}
