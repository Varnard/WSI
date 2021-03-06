package models;

import org.bson.types.ObjectId;
import org.glassfish.jersey.linking.InjectLinkNoFollow;
import org.mongodb.morphia.annotations.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Optional;


@Entity("courses")
@Indexes(
        @Index(value = "courseName", fields = @Field("courseName"))
)
@XmlRootElement
public class Course {

//         @InjectLinks({
//            @InjectLink(resource= resources.StudentResource.class, rel = "All students"),
//            @InjectLink(resource= resources.CourseListResource.class, rel = "All courses"),
//            @InjectLink(value = "students/{index}/courses/{courseName}",  rel ="self", condition ="${instance.studentPath}"),
//            @InjectLink(value = "courses/{courseName}", rel ="self", condition ="${instance.coursePath}"),
//            @InjectLink(resource = resources.CourseResource.class, rel = "Student courses", condition ="${instance.studentPath}"),
//            @InjectLink(resource = resources.StudentGradeResource.class, rel= "grades", condition ="${instance.studentPath}"),
//            @InjectLink(resource = resources.CourseGradeResource.class, rel="grades", condition ="${instance.coursePath}"),
//    })
//    @XmlElement(name="link")
//    @XmlElementWrapper(name ="links")
//    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
//         List<Link> links;

    private String teacher;
    private String courseName;
    private String studentPath ="true";
    private String coursePath="false";


    @Id
    @XmlTransient
    //@XmlJavaTypeAdapter(ObjectIdJaxbAdapter.class)
    ObjectId id;

    @InjectLinkNoFollow
    private ArrayList<Grade> grades;
    private int gradesId;



    public Course() {
    }

    public Course(String teacher, String courseName) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.grades = new ArrayList<>();
        gradesId=0;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @XmlAttribute
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @XmlTransient
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    @XmlTransient
    public String getCoursePath() {
        return coursePath;
    }

    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
    }

    @XmlTransient
    public String getStudentPath() {
        return studentPath;
    }

    public void setStudentPath(String studentPath) {
        this.studentPath = studentPath;
    }

    @XmlTransient
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }


    public int addGrade(Grade grade)
    {
        int id = gradesId;
        gradesId++;

        grade.setId(id);
        grades.add(grade);

        return id;
    }

    public boolean checkGrade(int gradeId, int studentIndex)
    {
        Optional<Grade> match
                = grades.stream()
                .filter(g -> g.getId()== gradeId)
                .findFirst();

        if (match.isPresent())
        {
            if (match.get().getStudentIndex()==studentIndex)
            return true;
            else return false;
        }
        else return false;
    }

    public boolean removeGrade(int removedId)
    {
        Optional<Grade> match
                = grades.stream()
                .filter(g -> g.getId()== removedId)
                .findFirst();

        if (match.isPresent())
        {
            grades.remove(match.get());
            return true;
        }
        else return false;
    }

    public boolean updateGrade(Grade grade)
    {
        Optional<Grade> match
                = grades.stream()
                .filter(g -> g.getId()== grade.getId())
                .findFirst();

        if (match.isPresent())
        {
            grades.remove(match.get());
            grades.add(grade);
            return true;
        }
        else return false;
    }

 /*   public int addGrade(Grade grade, int studentIndex)
    {
        int id = gradesId;
        gradesId++;

        grade.setId(id);
        grade.setStudentIndex(studentIndex);
        grades.add(grade);

        return id;
    }*/

    public boolean validate(){
        return (teacher!=null && !teacher.isEmpty()) &&
                (courseName!=null && !courseName.isEmpty());
    }

    @Override
    public String toString()
    {
        String course="Course name: " + courseName + "\n"
                + "Teacher: " + teacher + "\n";

        return course;
    }

}
