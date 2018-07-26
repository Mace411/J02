package homework02;

public class CountThread extends Thread {
	private int threadNum;
	public CountThread(int threadNum) {
		this.threadNum = threadNum;
	}
	@Override
	public void run() {
		int index = 0;
		while(true) {
			if(FileUtil.wordList.size() == index) {
				continue;
			}else {
				FileUtil.sumIncr(FileUtil.wordList.get(index));;
				index++;
			}
		}
	}
}
