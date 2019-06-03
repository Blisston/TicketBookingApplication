package booking;

public class Book {
	private int b_id;
	private String name;
	private String time;
	private int screen_no;
	private int amount;
	
	public Book(int b_id, String time, int screen_no) {
		super();
		this.b_id = b_id;
		this.time = time;
		this.screen_no = screen_no;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getScreen_no() {
		return screen_no;
	}
	public void setScreen_no(int screen_no) {
		this.screen_no = screen_no;
	}
	
	}
