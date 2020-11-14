package com.itau.taskcontrol.model;

public enum EnumStatus {

	PENDING(0), COMPLETED(1);
	
	private Integer value;

	private EnumStatus(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
}
