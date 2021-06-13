
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Course> courses1 = new ArrayList<>();

        courses1.add(new Courses("Mathematics"));
        courses1.add(new Courses("History"));
        courses1.add(new Courses("Astronomy"));
        courses1.add(new Courses("Programming"));
        courses1.add(new Courses("Physics"));

        ListCourse student1 = new ListCourse();
        student1.setCourseList(courses1);
        student1.setName("Alex");

        List<Course> courses2 = new ArrayList<>();
        courses2.add(new Courses("Mathanalis"));
        courses2.add(new Courses("Physics"));
        courses2.add(new Courses("Physics"));
        courses2.add(new Courses("Astronomy"));
        courses2.add(new Courses("Economy"));
        courses2.add(new Courses("Economy"));

        ListCourse student2 = new ListCourse();
        student2.setCourseList(courses2);
        student2.setName("Bron");

        List<Course> courses3 = new ArrayList<>();
        courses3.add(new Courses("Mathematics"));
        courses3.add(new Courses("Economy"));
        courses3.add(new Courses("Programming"));
        courses3.add(new Courses("Physics"));

        ListCourse student3 = new ListCourse();
        student3.setCourseList(courses3);
        student3.setName("John");

        List<Course> courses4 = new ArrayList<>();
        courses4.add(new Courses("Physics"));
        courses4.add(new Courses("History"));
        courses4.add(new Courses("Astronomy"));
        courses4.add(new Courses("Programming"));

        ListCourse student4 = new ListCourse();
        student4.setCourseList(courses4);
        student4.setName("Smith");

        List<Student> studentList = new ArrayList<>();

        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        List<Course> courses = getUniqueCourses(studentList);
        System.out.println("Unique courses: ");
        for (Course course : courses) {
            System.out.print(course.getName() + ", ");
        }


        List<Student> students = bestStudents(studentList);
        System.out.println("\nBest 3 students:");
        for (Student student : students) {
            System.out.print(student.getName() + ", ");
        }

        List<Student> studentsInCourse = studentsOnCourse(studentList, new Courses("History"));
        System.out.println("\nStudents on course: ");
        for (Student student : studentsInCourse) {
            System.out.print(student.getName() + ", ");
        }
    }

    static List<Course> getUniqueCourses(List<Student> students) {
        return students.stream().flatMap(
                s -> s.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    static List<Student> bestStudents(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(student -> student.getAllCourses().size(), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    static List<Student> studentsOnCourse(List<Student> studentList, Course course) {
        return studentList.stream()
                .filter(student -> student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }

}
