 package Console.address;

public class AddressApp {

	public static void main(String[] args) {
		AddressLogic logic = new AddressLogic();
		while(true) {
		//메인 메뉴 출력
		logic.printMainMenu();
		//메인메뉴 번호 입력받기
		int  mainMenu = logic.getMenuNumber();
		//메뉴에 따른 분기
		logic.seperateMainMenu(mainMenu);
		}

	}
	
}
