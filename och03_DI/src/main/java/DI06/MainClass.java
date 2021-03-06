package DI06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
	String configLocation1 = "classpath:applicationCTX610.xml";
	String configLocation2 = "classpath:applicationCTX611.xml";
	AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
	
	Student student1 = ctx.getBean("student1", Student.class);
	System.out.println(student1.getName()); //선덕여왕
	System.out.println(student1.getHobbys()); // 승마, 정치
	
	StudenetInfo studenetInfo = ctx.getBean("studentInfo1",StudenetInfo.class);
	Student student2 = studenetInfo.getStudent();
	System.out.println(student2.getName()); //선덕여왕
	System.out.println(student2.getHobbys()); // 승마, 정치
	
	if(student2.equals(student2)) {
		System.out.println("student1 == student2");
	}
	Student student3 = ctx.getBean("student3", Student.class);
	System.out.println(student3.getName());
	if(student1.equals(student3)) {
	}else {
		System.out.println("student1 == student3");
	}
	Family family =  ctx.getBean("family", Family.class);
	System.out.println(family.getPapaName());
	System.out.println(family.getMamiName());
	System.out.println(family.getSisterName());
	System.out.println(family.getBrotherName());
	
	ctx.close();

	}

}
