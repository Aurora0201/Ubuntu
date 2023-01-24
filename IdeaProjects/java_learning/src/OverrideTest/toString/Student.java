package OverrideTest.toString;

import java.util.Objects;

/**
 *
 *
 * <br/>
 * 学生类，储存学生的学号和学校信息
 * <br/>
 * @author binjunkai
 * @version 1.0
 */
public class Student {
    //学号
    private int actNo;
    //学校
    private String school;

    /**
     *
     * @return the String of actNo and school
     */
    @Override
    public String toString() {
        return "Student{" +
                "actNo=" + actNo +
                ", school='" + school + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return actNo == student.actNo && Objects.equals(school, student.school);
    }

    /**
     * 设置学生学号
     * @param actNo 学号信息
     *
     */
    public void setActNo(int actNo) {
        this.actNo = actNo;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}
