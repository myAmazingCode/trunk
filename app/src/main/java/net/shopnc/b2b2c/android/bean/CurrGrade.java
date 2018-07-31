package net.shopnc.b2b2c.android.bean;

/**
 * @Description XXX
 * @Author qyf
 *
 * Created 2016/6/29 16:14.
 */
public class CurrGrade {
    private String gradeName;

    private int gradeLevel;

    public CurrGrade(String gradeName, int gradeLevel) {
        this.gradeName = gradeName;
        this.gradeLevel = gradeLevel;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
