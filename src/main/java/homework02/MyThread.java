package homework02;

import java.io.File;
import java.util.List;

public class MyThread extends Thread{
	private List<File> fList;
	public MyThread (List<File> fList) {
		this.fList = fList;
	}
	@Override
	public void run() {
		FileUtil.count(fList);
	}
}
