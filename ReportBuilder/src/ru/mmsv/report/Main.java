package ru.mmsv.report;

import java.util.List;
import java.util.Map;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;

import ru.mmsv.report.discovery.Tags;
import ru.mmsv.report.photo.Image;
import ru.mmsv.report.photo.PhotoReport;
import ru.mmsv.report.utils.DiscoveryHelper;

public class Main {

	public static void main(String[] args) {
		try {
				FileSystemManager fsManager = VFS.getManager();
				FileObject diskC = fsManager.resolveFile("file://E:/Google Диск/ФФ/#5");
				for (FileObject report : diskC.getChildren()) {
					String name = "Фотоотчет обследования (" + report.getName().getBaseName().substring(0, 5) + ").xlsx";
					if(report.getChild("Фото") != null) {
						Map<Tags, List<Image>> images = DiscoveryHelper.getImageMap(report.getChild("Фото"));
						Report rep = new PhotoReport(VFS.getManager().resolveFile(report.getName().toString() + "/" + name).getName(), images);
						rep.build();
					}
				}
			}catch (Exception e) {
			   e.printStackTrace();
			}
	}

}
