package net.jin.overwatch.service.impl;

import net.jin.overwatch.service.OverWatch;

public class Reaper implements OverWatch {
	public void name() { // 오버라이딩
		System.out.println("이름 : 리퍼");
	}
	public void lClick() { // 오버라이딩
		System.out.println("좌클릭 : 헬파이어 샷건");
	}
	public void rClick() { // 오버라이딩
		System.out.println("우클릭 : 없음");
	}
	public void shiftButton() { // 오버라이딩
		System.out.println("shift : 망령화");
	}
	public void eButton() { // 오버라이딩
		System.out.println("e : 그림자 밟기");
	}
	public void qButton() { // 오버라이딩
		System.out.println("q : 죽음의 꽃(궁극기)");
	}
}
