package homework01;

import java.util.HashMap;

import javax.swing.plaf.synth.SynthSeparatorUI;
public class TestHashMap {
	public static HashMap<String,Integer> map = new HashMap<>();
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread() {
			public void run() {
				for(int i = 0;i<100;i++) {
					map.put("map1"+i, i);
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				for(String key : map.keySet()) {
					System.out.println(key + " ------ " +map.get(key));
				}
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				for(int i = 0;i<100;i++) {
					map.put("map2"+i, i);
				}
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
	}
}
