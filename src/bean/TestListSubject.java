package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

	private int entYear;

	private String studentNo;

	private String StudentName;

	private String classNum;

	private Map<Integer, Integer> points;

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return StudentName;
	}

	public void setStudentName(String studentName) {
		StudentName = studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public String getPoint(int key) {
        if (points == null) {
            return null;
    }
        Integer value = points.get(key);
        return (value != null) ? String.valueOf(value) : null;
    }

    public void putPoint(int key, int value) {
        if (points != null) {
            points.put(key, value);
        }
    }
}
