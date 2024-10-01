package hw.model.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import hw.model.dto.Stock;

public class StockService {
	
	private Scanner sc = new Scanner(System.in);
	
	private ArrayList<Stock> stockList = new ArrayList<Stock>();
	

	public StockService() {
		stockList.add(new Stock("네이버", 170400, 0.71, 15, 1));
		stockList.add(new Stock("엔비디아", 161000, 0.033, 20, 2));
		stockList.add(new Stock("화이자", 38680, 5.78, 37, 3));
		stockList.add(new Stock("메디컬 프로퍼티스", 7911, 10.08, 145, 4));
	}
	
	public void stockDisplay() {
		
		int menuNum = 0;
		
		do {
			System.out.println("***주식 관리 프로그램***");
			System.out.println("1. 현재 보유 주식 조회하기");
			System.out.println("2. 주식 매수하기");
			System.out.println("3. 주식 매도하기");
			System.out.println("4. 주식 정보 변경하기");
			System.out.println("5. 배당률 순으로 조회하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴를 선택 해 주세요 : ");
			
			menuNum = sc.nextInt();
			switch(menuNum) {
			case 1 : displayAllStocks(stockList); break;
			case 2 : buyStock(); break;
			case 3 : sellStock(); break;
			case 4 : updateStock(); break;
			case 5 : displayRateStock(); break;
			case 0 : 
				System.out.println("프로그램을 종료합니다");
				break;
			default :
				System.out.println("잘못누르셨습니다. 메뉴를 확인해주세요");
			}
			
		} while(menuNum !=0);
	}
	
	
	/**
	 *  현재 보유주식 조회하기
	 */
	public void displayAllStocks(ArrayList<Stock> list) {
		if(list.isEmpty()) {
			System.out.println("보유중인 주식이 없습니다.");
			
		}else {			
			System.out.println("===보유 주식 목록===");
			for(Stock stk : list) {
				System.out.println(stk);
			}
		}
	}
	
	
	/**
	 *  주식 매수하기
	 */
	public void buyStock() {
		System.out.println("===주식 매수하기===");
		
		System.out.print("구매할 주식 이름 : ");
		String name = sc.next();
			
		for(Stock stk : stockList) {
			if(stk.getName().equals(name)) {
					System.out.println("보유중인 주식입니다.");
					System.out.print("추가 구매 하시겠습니까? (Y/N) : ");
					char input = sc.next().toUpperCase().charAt(0);
					
					if(input == 'Y') {
						System.out.print("구매할 개수를 입력 해주세요 : ");
						int countPlus = sc.nextInt();
						stk.setCount(stk.getCount() + countPlus);
						System.out.println("구매에 성공하였습니다.");
						System.out.println("구매 후 " + name + "의 보유개수 : " + stk.getCount());
							
					} else { 
						System.out.println("구매를 취소합니다");
					}
					return;
			
			}
		}
		
		System.out.print("주식 번호 : ");
		int num = sc.nextInt();
		
		for(Stock stk : stockList) {
			if(stk.getNum() == num) {
				System.out.println("입력한 주식 번호가 이미 존재합니다.\n번호를 다시 확인하세요.");
				return;
			}
		}
			System.out.print("가격 : ");
			int price = sc.nextInt();
			
			System.out.print("배당률(연) : ");
			double allocation = sc.nextDouble();
		
			System.out.print("구매할 개수 : ");
			int count = sc.nextInt();
			
			System.out.println("주식 구매 완료");
			Stock newStock = new Stock(name,price,allocation,count,num);
			stockList.add(newStock);
	}
	
	
	/**
	 *  주식 매도하기
	 */	
	public void sellStock() {
		System.out.println("===주식 매도하기===");
		System.out.println("<현재 보유중인 주식 목록>");
	
		for(Stock stk : stockList) {
			System.out.println(stk);
		}
		
		System.out.print("매도할 주식의 번호를 입력하세요 : ");
		int num = sc.nextInt();
		
		for(Stock stk : stockList) {
			if(stk.getNum() != num) {
				System.out.println("해당 주식이 없습니다.\n번호를 다시 확인하세요");
				return;
				
			} else{
				System.out.print("매도할 수량을 입력해주세요 : ");
				int sell = sc.nextInt();
				
				if(stk.getCount() - sell == 0) {
					System.out.println(stk.getName() + "을 전량 매도 하였습니다");
					stockList.remove(stk);
					return;
				
				} else if(stk.getCount() - sell > 0) {
					System.out.println(stk.getName() + "의 보유주식을 " + sell + "개 만큼 매도하였습니다.");
					stk.setCount(stk.getCount() - sell);
					return;
				
				} else if(stk.getCount() - sell < 0) {
					System.out.println("보유 수량만큼만 매도하실 수 있습니다. \n수량을 다시 확인하세요");
					return;	
				}
			}
		}
	}
		
	
    /**
     *  주식 정보 변경하기
     */    
    public void updateStock() {
 
    	System.out.println("<현재 보유중인 주식 목록>");

    	for (Stock stk : stockList) {
    		System.out.println(stk);
    	}
        
    	System.out.println("===주식 정보 변경하기===");
        
        int updateMenu = 0;
        
        System.out.print("변경 할 주식 번호를 입력 해주세요 : ");
        int stockNum = sc.nextInt();
        
        boolean flag = true;
        for(Stock list : stockList) {
        	
        	if(list.getNum() == stockNum) {
        		
        		flag = false;
        		
	            System.out.println("1. 주식명");
	            System.out.println("2. 주식 가격");
	            System.out.println("3. 주식 배당률");
	            System.out.println("4. 주식 보유수량");
	            System.out.println("5. 주식 번호변경");
	            System.out.println("0. 변경 종료");
	            System.out.print("변경할 내용을 선택해 주세요 : ");
            
	            updateMenu = sc.nextInt();
	            sc.nextLine();
	            
	            switch(updateMenu) {
	            case 1 : 
	            	System.out.println("===주식명 변경===");
	            	System.out.print("변경할 주식명을 입력하세요 : ");
	            	String name = sc.nextLine();
	            	list.setName(name);
	            	break;
	            case 2 :
	            	System.out.println("===주식 가격 변경===");
	            	System.out.print("변경할 가격을 입력하세요 : ");
	            	int price = sc.nextInt();
	            	list.setPrice(price);
	            	break;
	            case 3 :
	            	System.out.println("===주식 배당률 변경===");
	            	System.out.print("변경할 배당률을 입력하세요 : ");
	            	double allocation = sc.nextDouble();
	            	list.setAllocation(allocation);
	            	break;
	            case 4 :
	            	System.out.println("===주식 보유수량 변경=== ");
	            	System.out.print("변경할 수량을 입력하세요 : ");
	            	int count = sc.nextInt();
	            	list.setCount(count);
	            	break;
	            case 5 :
	            	System.out.println("===주식 번호변경===");
	            	System.out.print("변경할 주식번호를 입력하세요 : ");
	            	int num = sc.nextInt();
	            	for(Stock stk : stockList) {
	        			if(stk.getNum() == num) {
	        				System.out.println("입력한 주식 번호가 이미 존재합니다.\n번호를 다시 확인하세요.");
	        				break;
	        			}else {
	        				list.setNum(num);
	        			}
	        		}
	            	break;
	            case 0 : System.out.println("취소합니다"); break;
	            default : System.out.println("메뉴 번호를 다시 확인해주세요"); break;
	            }   
	            System.out.println("변경 완료");
        	}
    	}
        if(flag == true) {
        	System.out.println("해당하는 주식이 없습니다.");
        	return;
        }
    }
    
    
	/**
	 *  배당률 순으로 정렬
	 */
	public void displayRateStock() {
		System.out.println("===배당률 순으로 주식 정렬===");
		ArrayList<Stock> copyStockList = new ArrayList<Stock>(stockList); //원본 객체 Copy
		copyStockList.sort(Comparator.comparingDouble(Stock::getAllocation).reversed()); //Copy한 객체를 정렬
		
		for(Stock stk : copyStockList) {
			System.out.println(stk);
		}
	}
	
}
