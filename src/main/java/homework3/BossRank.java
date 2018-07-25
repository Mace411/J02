package homework3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BossRank {

	public static final ConcurrentHashMap<Integer,Boss> bossRank = new ConcurrentHashMap<>();
	
	static {
		bossRank.put(1, new Boss("系统榜单第一",80,1));
		bossRank.put(2, new Boss("系统榜单第二",80,2));
		bossRank.put(3, new Boss("系统榜单第三",80,3));
		bossRank.put(4, new Boss("系统榜单第四",80,4));
		bossRank.put(5, new Boss("系统榜单第五",80,5));
		bossRank.put(6, new Boss("系统榜单第六",80,6));
		bossRank.put(7, new Boss("系统榜单第七",80,7));
		bossRank.put(8, new Boss("系统榜单第八",80,8));
		bossRank.put(9, new Boss("系统榜单第九",80,9));
		bossRank.put(10, new Boss("系统榜单第十",80,10));
	}
	
	public void challenge(int level , String name , int score) {
		Boss boss = bossRank.get(level);
		synchronized (boss) {
			if(boss.getScore() < score) {
				boss.updateBoss(name, level, score);
			}
		}
	}
	
	public List<String> getBossList(){
		List<String> bl = new ArrayList<String>();
		for(Integer level : bossRank.keySet()) {
			Boss boss = bossRank.get(level);
			bl.add(boss.toString());
		}
		return bl;
	}
}
