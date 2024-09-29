package hw.model.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

import hw.model.dto.Stock;

public class StockService {
	

	// 스캐너
	private Scanner sc = new Scanner(System.in);
	
	// 보유중인 주식 HashSet(중복제거)
	private HashSet<Stock> stockSet = new HashSet<Stock>();
	
	
	// 기본 생성자
	public StockService() {
		//현재 보유중인 주식
		stockSet.add(new Stock("네이버", 170400, 0.71, 15));
		stockSet.add(new Stock("엔비디아", 161000, 0.033, 20));
		stockSet.add(new Stock("화이자", 38680, 5.78, 37));
		stockSet.add(new Stock("메디컬 프로퍼티스", 7911, 10.08, 145));
	}
	
	public void stockDisplay() {
		
		int menuNum = 0;
		
		do {
			System.out.println("***주식 관리 프로그램***");
			System.out.println("1. 현재 보유 주식 조회하기");
			System.out.println("2. 주식 매수하기");
			System.out.println("3. 주식 매도하기");
			System.out.println("4. 배당률 순으로 조회하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴를 선택 해 주세요 : ");
			
			menuNum = sc.nextInt();
			switch(menuNum) {
			case 1 : displayAllStocks(); break;
			case 2 : buyStock(); break;
			case 3 : sellStock(); break;
			case 4 : displayRateStock(); break;
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
	public void displayAllStocks() {
		System.out.println("===보유 주식 목록===");
		
		int index = 1;
		for(Stock stk : stockSet) {
			System.out.println(index + ". " + stk);
			index++;
		}
	}
	
	
	/**
	 *  주식 매수하기
	 */
	public void buyStock() {
		System.out.println("===주식 매수하기===");
		
		System.out.print("주식회사 이름 : ");
		String name = sc.next();
		
		for(Stock st : stockSet) {
			// 이미 보유중인 주식의 경우 추가로 구매 또는 취소
			if(st.getName().equals(name)) {
				System.out.println("보유중인 주식입니다.");
				System.out.println("추가 구매 하시겠습니까? (Y/N)");
				String input = sc.next();
				
				if(input.equalsIgnoreCase("Y")) {
					System.out.print("구매할 개수를 입력 해 주세요 : ");
					int countPlus = sc.nextInt();  
					st.setCount(st.getCount() + countPlus);
					System.out.println("구매에 성공하였습니다.");
					System.out.println("구매후 " + name + "의 보유개수 : " + st.getCount());
					return;
				} else {
					System.out.println("구매를 취소합니다");
					return;
				}
			}
		}
		
		
		System.out.print("가격 : ");
		int price = sc.nextInt();
		
		System.out.print("배당율(연) : ");
		double allocation = sc.nextDouble();
		
		System.out.print("구매할 개수 : ");
		int count = sc.nextInt();
		
		stockSet.add(new Stock(name,price,allocation,count));
		System.out.println("주식을 구매하였습니다");
	}
	
	
	/**
	 *  주식 매도하기
	 */
    public void sellStock() {
        System.out.println("===매도 하기===");
        System.out.println("<현재 보유중인 주식 목록>");
        
        ArrayList<Stock> stockList = new ArrayList<Stock>(stockSet); // stockSet을 리스트로 변환
        
        int index = 1;
        for (Stock stk : stockList) {
            System.out.println(index + ". " + stk);
            index++;
        }
        
        System.out.print("매도할 주식의 이름을 입력 해주세요 : ");
        String name = sc.next();
        Stock stockToSell = null; //매도할 주식을 저장할 변수 선언 및 초기화
        
        for (Stock stk : stockList) { //List를 순회해 입력값과 일치하는 주식을 stockToSell에 저장
            if (stk.getName().equals(name)) { 
                stockToSell = stk;
                break;
            }
        }
        
        if (stockToSell == null) {
            System.out.println("보유 주식명을 확인하신 후 다시 이용해주세요");
            return;
        }
        
        System.out.print("매도할 개수를 입력 해주세요 : ");
        int count = sc.nextInt();
        if (stockToSell.getCount() - count < 0) {
            System.out.println("소지한 개수만큼만 매도하실 수 있습니다");
            
        } else {
            stockSet.remove(stockToSell);			//stockSet에서 stockToSell은 사라지지만 stockToSell변수는 메모리에 존재
            
            if (stockToSell.getCount() - count == 0) {
                System.out.println(stockToSell.getName() + "의 주식을 전량 매도하였습니다");
            } 
            
            else {
                stockToSell.setCount(stockToSell.getCount() - count);
                stockSet.add(stockToSell); 
                System.out.println(stockToSell.getName() + "의 주식을 " + count + "개 매도하였습니다");
            }
        }
    }
	
	
	/**
	 *  배당률 순으로 정렬
	 */
	public void displayRateStock() {
		System.out.println("===배당률 순으로 주식 정렬===");
		ArrayList<Stock> stockList = new ArrayList<Stock>(stockSet);
		stockList.sort(Comparator.comparingDouble(Stock::getAllocation).reversed());
		
		int index = 1;
		for(Stock stk : stockList) {
			System.out.println(index + ". " + stk);
			index++;
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
