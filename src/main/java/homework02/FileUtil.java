package homework02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import javax.sound.midi.Synthesizer;

public class FileUtil {
	public static final ConcurrentHashMap<String, Integer> wordSum = new ConcurrentHashMap<>();
	//根据路径获取目录的全部文件
	public static List<File> getFileList(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		List<File> txtFiles = new ArrayList<File>();
		for(File f : files) {
			//排除掉文件夹和非文本文件
			String fileName = f.getName();
			String extension = f.getName().substring(fileName.lastIndexOf(".")+1, fileName.length());
			if(f.isDirectory() || !(extension.equals("txt"))) {
				continue;
			}
			txtFiles.add(f);
		}
		return txtFiles;
	}
	//次数加1
	public synchronized static void sumIncr(String word) {
		if (wordSum.get(word) == null) {
			wordSum.put(word, 1);
		} else {
			Integer sum = wordSum.get(word);
			wordSum.put(word, ++sum);
		}
	}
	//统计单词个数
	public static void count(List<File> files){
		InputStream is = null;
		BufferedReader br = null;
		for(File file : files) {
			try {
				is = new FileInputStream(file);
				br = new BufferedReader(new InputStreamReader(is));
				String word = null;
				while((word = br.readLine()) != null){
					word.trim();
					sumIncr(word);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//排序
	public static List<WordNumNode> sort(Map<String, Integer> wordSum) {
		List<WordNumNode> sumList = new ArrayList<>();
		for(String key : wordSum.keySet()) {
			sumList.add(new WordNumNode(key,wordSum.get(key)));
		}
		HeapSort.sort(sumList);
		return sumList;
	}
	
	public static void main(String[] args) {
		System.out.println("请输入文本文件的目录:");
		Scanner sc = new Scanner(System.in);
		String path = sc.next();
		int threadNum = sc.nextInt();
		long start = System.currentTimeMillis();
		List<File> txtFiles = getFileList(path);
		List<List<File>> list = new ArrayList<>();
		long start1 = System.currentTimeMillis();
		for(int i = 0;i<threadNum;i++) {
			list.add(new ArrayList<File>());
		}
		for(int i = 0;i<txtFiles.size();i++) {
			int index = i % threadNum;
			list.get(index).add(txtFiles.get(i));
		}
		
		MyThread[] mt = new MyThread[threadNum];
		for(int i = 0;i<threadNum;i++) {
			mt[i] = new MyThread(list.get(i));
			mt[i].start();
		}
		System.out.println("耗时:"+(System.currentTimeMillis()-start1)+"ms");
		for(int i = 0;i<threadNum;i++) {
			try {
				mt[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		for(String key : wordSum.keySet()) {
//			System.out.println(key +"------"+ wordSum.get(key));
//		}
//		System.out.println();
		
		List<WordNumNode> sortedList = sort(wordSum);
		//因为算法是把整个单词序列排序的，但是要求计算出现次数前十的单词，所以就只输出前十个
		for(int i = sortedList.size()-1;i>=0;i--) {
			if(i < sortedList.size() - 10) {
				break;
			}
			System.out.println(sortedList.get(i).getWord() +" ------ "+ sortedList.get(i).getNum());
		}
		System.out.println("耗时:"+(System.currentTimeMillis()-start)+"ms");
		
	}
}
