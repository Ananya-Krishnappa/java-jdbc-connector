package com.bridgelabz.jdbcconnector.type;

public enum Gender {
	MALE("M"), FEMALE("F");

	public final String label;

	private Gender(String label) {
		this.label = label;
	}

	public static Gender valueOfLabel(String label) {
		for (Gender e : Gender.values()) {
			if (e.label.equals(label)) {
				return e;
			}
		}
		return null;
	}
}
