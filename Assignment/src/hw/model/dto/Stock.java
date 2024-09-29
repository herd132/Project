package hw.model.dto;

import java.util.Objects;

public class Stock {

	private String name;
	private int price;
	private double allocation;
	private int count;
	
	public Stock() {}

	public Stock(String name, int price, double allocation, int count) {
		super();
		this.name = name; //이름
		this.price = price; //가격
		this.allocation = allocation; //배당률
		this.count = count; // 소지갯수
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
	
	

	@Override
	public int hashCode() {
		return Objects.hash(allocation, count, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Double.doubleToLongBits(allocation) == Double.doubleToLongBits(other.allocation) && count == other.count
				&& Objects.equals(name, other.name) && price == other.price;
	}

	@Override
	public String toString() {
		return String.format("이름 : %s / 가격 : %d / 배당율(연) : %s / 보유개수 : %d주 ",
							name,price,allocation,count);
	}
	
	
}
