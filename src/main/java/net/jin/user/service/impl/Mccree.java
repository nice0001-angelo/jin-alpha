package net.jin.user.service.impl;

import net.jin.user.service.OverWatch;

public class Mccree implements OverWatch{
	public void name() { // 오버라이딩
		System.out.println("이름 : 맥크리");
	}
	public void lClick() { // 오버라이딩
		System.out.println("좌클릭 : 피스키퍼");
	}
	public void rClick() { // 오버라이딩
		System.out.println("우클릭 : 모든 총알 발사");
	}
	public void shiftButton() { // 오버라이딩
		System.out.println("shift : 구르기");
	}
	public void eButton() { // 오버라이딩
		System.out.println("e : 섬광탄");
	}
	public void qButton() { // 오버라이딩
		System.out.println("q : 황야의 무법자(궁극기)");
	}
}
