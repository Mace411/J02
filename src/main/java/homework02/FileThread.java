package homework02;

import java.io.File;
import java.util.List;

public class FileThread extends Thread{
	private List<File> fList;
	public FileThread (List<File> fList) {
		this.fList = fList;
	}
	@Override
	public void run() {
		FileUtil.count(fList);
	}
}
