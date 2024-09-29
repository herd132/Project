package hw.run;

import hw.model.service.StockService;

public class StockRun {

	public static void main(String[] args) {
		StockService stservice = new StockService();
		stservice.stockDisplay();
	}
}
