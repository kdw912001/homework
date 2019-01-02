package com.Test3;

import java.util.Arrays;

public class StudentTest {

	public static void main(String[] args) {
		Student studentArray[] = {
				new Student("홍길동",15,170,80),
				new Student("한사람",13,180,70),
				new Student("임걱정",16,175,65)
		};
		System.out.println("이름   나이   신장   몸무게");
		double ageSum = 0, heightSum = 0, weightSum = 0;
		int ageMax = studentArray[0].getAge();
		int ageMin = studentArray[0].getAge();
		int heightMax = studentArray[0].getHeight();
		int heightMin = studentArray[0].getHeight();
		int weightMax = studentArray[0].getWeight();
		int weightMin = studentArray[0].getWeight();
		
		String[] str = new String[6];//max,min에 해당하는 이름 저장
		for(int i=0; i<studentArray.length;i++) {
			System.out.println(studentArray[i].studentInfo());
			ageSum += studentArray[i].getAge();
			heightSum += studentArray[i].getHeight();
			weightSum +=studentArray[i].getWeight();
			//초기값을 0번째 객체배열의 값을 설정했으니 연산자에 =도 추가함.
			//=을 설정하지 않았을 시 조건문을 수행하지 않아 str값 저장 안 하는 경우 발생
			if(ageMax <= studentArray[i].getAge()) {
				ageMax = studentArray[i].getAge();
				str[0] = studentArray[i].getName();
			}
			if(ageMin>= studentArray[i].getAge()) {
				ageMin = studentArray[i].getAge();
				str[1] = studentArray[i].getName();
			}
			if(heightMax <= studentArray[i].getHeight()) {
				heightMax = studentArray[i].getHeight();
				str[2] = studentArray[i].getName();
			}
			if(heightMin >= studentArray[i].getHeight()) {
				heightMin = studentArray[i].getHeight();	
				str[3] = studentArray[i].getName();
			}
			if(weightMax <= studentArray[i].getWeight()) {
				weightMax = studentArray[i].getWeight();
				str[4] = studentArray[i].getName();
			}
			if(weightMin >= studentArray[i].getWeight()) {
				weightMin = studentArray[i].getWeight();
				str[5] = studentArray[i].getName();	
			}
			
			/*if(ageMax == studentArray[i].getAge()) {
				ageMax = studentArray[i].getAge();
				str[0] = studentArray[i].getName();
			}
			if(ageMin> studentArray[i].getAge()) {
				ageMin = studentArray[i].getAge();
				str[1] = studentArray[i].getName();
			}
			if(heightMax == studentArray[i].getHeight()) {
				str[2] = studentArray[i].getName();
			}
			if(heightMin == studentArray[i].getHeight()) {
				str[3] = studentArray[i].getName();
			}
			
			if(weightMax == studentArray[i].getWeight()) {
				str[4] = studentArray[i].getName();
			}
			if(weightMin == studentArray[i].getWeight()) {
				str[5] = studentArray[i].getName();
			}*/
			
		}
		System.out.printf("나이 평균 : %.3f\n", ageSum/studentArray.length);
		System.out.printf("신장 평균 : %.3f\n", heightSum/studentArray.length);
		System.out.printf("몸무게 평균 : %.3f\n", weightSum/studentArray.length);
		
		
		System.out.println();
		System.out.println("나이가 가장 많은 학생 : "+str[0]);
		System.out.println("나이가 가장 적은 학생 : "+str[1]);
		System.out.println("신장이 가장 큰 학생 : "+str[2]);
		System.out.println("신장이 가장 작은 학생 : "+str[3]);
		System.out.println("몸무게가 가장 많이 나가는 학생 : "+str[4]);
		System.out.println("몸무게가 가장 적게 나가는 학생 : "+str[5]);
	}

}
