package homework3;

public class Boss {
	private String name;
	private int score;
	private int level;
	
	public Boss() {
	}
	public Boss(String name, int score, int level) {
		super();
		this.name = name;
		this.score = score;
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void updateBoss(String name, int level, int score) {
		this.name = name;
		this.level = level;
		this.score = score;
	}
	@Override
	public String toString() {
		return "[name=" + name + "],[score=" + score + "],[level=" + level +"]";
	}
}
