
import java.util.Objects;

public class Courses implements Course {
    private String name;

    public Courses(String name) {
        this.name = name;
    }

    public String getName() {
        this.name = name;
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        else if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Courses course = (Courses) obj;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
