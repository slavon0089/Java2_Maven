import java.util.List;
import java.util.ArrayList;

public class ListCourse implements Student {

    private List<Course> courseList = new ArrayList<>();
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
