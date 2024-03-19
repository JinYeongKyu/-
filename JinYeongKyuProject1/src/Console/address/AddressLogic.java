package Console.address;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import common.utils.CommonUtils;

public class AddressLogic {
	
	public static final int MAX_PERSONS=10;
	private List<Person> person;
	private String name;
	private int age;
	private String addr;
	private String phonenum;
	char consonant;
	Map<Character,List<Person>> addressBook;
	
	
	public AddressLogic() {
		person = new ArrayList<>();
		addressBook = new HashMap<>();
	}

	public void printMainMenu() {
		System.out.println("====================메인 메뉴====================");
		System.out.println(" 1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료");
		System.out.println("===============================================");
		System.out.println("메인 메뉴번호를 입력하세요");
	}//////////////printMainMenu()

	public int getMenuNumber() {
		Scanner sc = new Scanner(System.in);
		String menuStr;
		while(true) {
			menuStr= sc.nextLine().trim();
			if(!CommonUtils.isNumber(menuStr)) {
				System.out.println("메뉴번호는 숫자만 입력해주세요");
				continue;
			}
			break;
		}
		return Integer.parseInt(menuStr);
	}//////////getMenuNumber()
	
	public void seperateMainMenu(int mainMenu) {
		switch(mainMenu) {
			case 1:
				setPerson();
				break;
			case 2:
				printPerson();
				break;
			case 3:
				updatePerson();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				findPerson();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;
			default:
				System.out.println("메뉴에 없는 번호입니다");
		}
	}//////////seperateMainMenu()

	private void setPerson() {
		if(person.size()==MAX_PERSONS) {
			System.out.println("정원이 찼습니다");
			return;
		}
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하세요?");
		name= sc.nextLine().trim();
		System.out.println("나이를 입력하세요?");
		age = getAges(sc);
		System.out.println("주소를 입력하세요?");
		addr= sc.nextLine().trim();
		System.out.println("연락처를 입력하세요?");
		phonenum= sc.nextLine().trim();
		
		char consonant = CommonUtils.getInitialConsonant(name);
		Person subperson = new Person(name, age, addr, phonenum);
	    person.add(subperson);

	    if (!addressBook.containsKey(consonant)) {
	        addressBook.put(consonant, new ArrayList<>());
	    }
	    addressBook.get(consonant).add(subperson);
	}/////////setPerson()
		
	private void printPerson() {	
		Set<Character> keys=addressBook.keySet();
		for(Character key:keys) {
			System.out.println(String.format("[%c으로 시작하는 명단]",key));
			List<Person> values= addressBook.get(key);
			for(Person value:values) System.out.println(value);
		}
	}/////////printPerson()
	
	private Person searchPerson(String title) {
		System.out.println(title+"할 사람의 이름을 입력하세요?");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine().trim();
		for(Person p:person)			
			if(p.name.equals(name))
				return p;
		System.out.println(name+"로(으로) 검색된 정보가 없습니다");
		return null;
	}///////////searchPerson()
	
	private void findPerson() {
		Person findPerson=searchPerson("검색");
		if(findPerson !=null) {
			System.out.println(String.format("[%s로(으로) 검색한 결과]", findPerson.name));
			findPerson.print();
		}
	}/////////findPerson()
	
	private void updatePerson() {
		Person findPerson = searchPerson("수정");
		if(findPerson !=null) {
			Scanner sc = new Scanner(System.in);
				System.out.printf("수정할 나이를 입력하세요?%n",((Person)findPerson).age);
				findPerson.age = getAges(sc);
				System.out.printf("수정할 주소를 입력하세요?%n",((Person)findPerson).addr);
				((Person)findPerson).addr=sc.nextLine().trim();
				System.out.printf("수정할 연락처를 입력하세요?%n",((Person)findPerson).phonenum);
				((Person)findPerson).phonenum=sc.nextLine().trim();
				System.out.printf("%s가(이) 아래와 같이 수정되었습니다%n",findPerson.name);
				findPerson.print();
		}
	}//////////updatePerson()
	
	private void deletePerson() {
		Person findPerson=searchPerson("삭제");
		if(findPerson !=null) {
			for(Person p:person) {
				if(findPerson.equals(p)) {
					person.remove(p);
					char consonant = CommonUtils.getInitialConsonant(findPerson.name);
					addressBook.get(consonant).remove(findPerson);
					addressBook.remove(consonant);
					System.out.println(String.format("[%s가(이) 삭제되었습니다]", findPerson.name));
					break;
				}
			}
		}
	}//////////deletePerson()
	
	private int getAges(Scanner sc) {
		int years;
		while(true) {
			try {
				years=Integer.parseInt(sc.nextLine().trim());
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("나이는 숫자만 입력하세요");
			}
		}
		return years;
	}/////////getAges()
}/////////class