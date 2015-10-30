package ru.mmsv.report.photo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.vfs2.FileObject;
import org.apache.poi.util.IOUtils;

public class Image {
	
	private FileObject image;
	
	private final byte[] bytes;
	
	public Image(FileObject image) throws IOException {
		this.image = image;
		InputStream inputStream = new FileInputStream(image.getName().getPath().toString());
		bytes = IOUtils.toByteArray(inputStream);
	}
	
	public String getName() {
		return image.getName().getBaseName();
	}
	
	public String getFullName() {
		return image.getName().toString();
	}
	
	public String getPath() {
		return image.getName().getPath();
	}
	
	public byte[] getBytes() {
		return bytes;
	}
}
