package com.rasal.myreflection;

import java.lang.reflect.Field;

public class App {
	private static final String newline = "\n";
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Person p = new Person();
		p.setName("Sajana Maharjan");
		p.setAddress("address here");
		p.hello = 5;
		/*Car c = new Car();
		c.setMake("Toyota");
		c.setModel("Corolla");
		c.setYear("2015");
		
		p.setOwnCar(c);*/
		
		Person p1 = new Person();
		p1.setName("Sajana Maharjan");
		p1.setAddress("address here");
		p1.hello = 6;
		
		System.out.println(ObjectsComparator.ObjectsCompare(p, p1));
	}
	
	@SuppressWarnings("rawtypes")
	public static <T> String getFieldsCSV(T a) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		
		Class c = a.getClass();
		
		sb.append("simple name: ").append(c.getSimpleName()).append(newline);
		for (Field f : c.getDeclaredFields()) {
			f.setAccessible(true);
			String value = getStringFromObject(f.get(a));
			sb.append(f.getName()).append(": ").append(value).append(newline);
		}
		
		
		return sb.toString();
	}

	private static String getStringFromObject(Object object) throws IllegalArgumentException, IllegalAccessException {
		if (object == null) {
			return "null";
		}
		
		if (object instanceof String) {
			return object.toString();
		} else {
			return getFieldsCSV(object);
		}
	}
	
	
}
