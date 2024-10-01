package hw.model.dto;

public class Stock {

	private String name;
	private int price;
	private double allocation;
	private int count;
	private int num;
	
	public Stock() {}

	public Stock(String name, int price, double allocation, int count, int num) {
		super();
		this.name = name; //이름
		this.price = price; //가격
		this.allocation = allocation; //배당률
		this.count = count; // 소지갯수
		this.num = num; //번호
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getAllocation() {
		return allocation;
	}

	public void setAllocation(double allocation) {
		this.allocation = allocation;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}


	@Override
	public String toString() {
		return String.format("%d.%s  / 가격 : %d / 배당율(연) : %s / 보유개수 : %d주 ",
							num,name,price,allocation,count);
	}
	
	
}
