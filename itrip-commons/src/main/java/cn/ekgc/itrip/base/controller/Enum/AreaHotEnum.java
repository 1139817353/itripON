package cn.ekgc.itrip.base.controller.Enum;

public enum AreaHotEnum {
	AREA__HOT_NO(0),
	AREA_HOT_YES(1)
	;
	private int code;
	private AreaHotEnum(int code){
		this.code= code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
