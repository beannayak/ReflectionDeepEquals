package com.rasal.myreflection;

import java.lang.reflect.Field;
import java.util.Collection;

public class ObjectsComparator<T> {

	public static <T> boolean ObjectsCompare(T a, T b) throws IllegalArgumentException, IllegalAccessException {
		Class classOfT = a.getClass();

		for (Field f : classOfT.getDeclaredFields()) {
			f.setAccessible(true);
			Object aVal = f.get(a);
			Object bVal = f.get(b);

			if (aVal == null && bVal == null) {
				continue;
			} else if ((aVal == null && bVal != null) || (aVal != null && bVal == null)) {
				return false;
			}

			if (!aVal.getClass().equals(bVal.getClass())) {
				return false;
			}

			if (isPrimitiveOrString(aVal, bVal)) {
				if (!aVal.equals(bVal)) {
					return false;
				}
			} else if (isCollection(aVal)){
				Collection aCol = (Collection) aVal;
				Collection bCol = (Collection) bVal;
				
			} else {
				if (!ObjectsCompare(aVal, bVal)) {
					return false;
				}
			}

		}

		return true;
	}

	private static boolean isCollection(Object aVal) {
		return Collection.class.isAssignableFrom(aVal.getClass());
	}

	private static boolean isPrimitiveOrString(Object aVal, Object bVal) {
		return Number.class.isAssignableFrom(aVal.getClass()) || aVal instanceof String
				|| Character.class.isAssignableFrom(aVal.getClass());
	}
}
