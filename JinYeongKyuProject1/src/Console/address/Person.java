package Console.address;

import java.util.Scanner;

public class Person {
	
	String name;
	int age;
	String addr;
	String phonenum;
	//기본생성자
	public Person() {}
	//인자생성자
	public Person(String name, int age, String addr, String phonenum) {
		super();
		this.name = name;
		this.age = age;
		this.addr=addr;
		this.phonenum = phonenum;
	}

	String get() {
		return String.format("이름:%s,나이:%s,사는곳:%s,연락처:%s",name,age,addr,phonenum);
	}
	void print() {
		System.out.println(get());
	}
	
	@Override
	public String toString() {
		return String.format("이름:%s,나이:%s,사는곳:%s,연락처:%s",name,age,addr,phonenum);
	}
}
