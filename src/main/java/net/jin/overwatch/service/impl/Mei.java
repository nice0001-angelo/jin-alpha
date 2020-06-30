package net.jin.overwatch.service.impl;

import net.jin.overwatch.service.OverWatch;

public class Mei implements OverWatch{
	public void name() { // 오버라이딩
		System.out.println("이름 : 메이");
	}
	public void lClick() { // 오버라이딩
		System.out.println("좌클릭 : 냉각총");
	}
	public void rClick() { // 오버라이딩
		System.out.println("우클릭 : 고드름 투사체");
	}
	public void shiftButton() { // 오버라이딩
		System.out.println("shift : 급속 빙결");
	}
	public void eButton() { // 오버라이딩
		System.out.println("e : 빙벽");
	}
	public void qButton() { // 오버라이딩
		System.out.println("q : 눈보라(궁극기)");
	}
}
