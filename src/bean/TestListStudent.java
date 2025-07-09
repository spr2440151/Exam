package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {

	private String subjectName;

	private String subjectCd;

	private int num;

	private int point;

	private int no;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}

	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getNo(){
		return no;
	}

	public void setNo(int no){
		this.no =no;
	}

}
