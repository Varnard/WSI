package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Embedded("grades")
@Indexes(
        @Index(value = "id", fields = @Field("id"))
)
@XmlRootElement
public class Grade {
//    @InjectLinks({
//            @InjectLink(value = "students/{index}/courses/{courseName}", rel = "Parent course", condition ="${instance.studentPath}"),
//            @InjectLink(resource = resources.StudentGradeResource.class, rel = "all Grades", condition ="${instance.studentPath}"),
//            @InjectLink(value = "students/{index}/courses/{courseName}/grades/{id}", rel = "self", condition ="${instance.studentPath}"),
//            @InjectLink(value = "courses/{courseName}", rel = "Parent course", condition ="${instance.coursePath}"),
//            @InjectLink(resource = resources.CourseGradeResource.class, rel = "all Grades", condition ="${instance.coursePath}"),
//            @InjectLink(value = "courses/{courseName}/grades/{id}", rel ="self", condition ="${instance.coursePath}"),
//    })
//    @XmlElement(name="link")
//    @XmlElementWrapper(name ="links")
//    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
//    List<Link> links;

    private int studentIndex;
    private double mark;

    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="CET")
    private Date date;

    private int id;
    private String studentPath ="true";
    private String coursePath="false";

    private final static double[] grades = {2,2.5,3,3.5,4,4.5,5};

    public Grade(double mark, String date) {
        this.mark = mark;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            this.date = sdf.parse(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Grade() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentIndex() {
        return studentIndex;
    }

    public void setStudentIndex(int studentIndex) {
        this.studentIndex = studentIndex;
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

    public boolean validate(){
        return (studentIndex>0 &&
                date!=null &&
                Arrays.asList(grades).contains(mark));
    }

    @Override
    public String toString()
    {
       String grade="StudentIndex: " + studentIndex  + "\n"
                + "Mark: " + mark + "\n"
                + "Date: " + date + "\n"
                + "Id: " + id + "\n";



        return grade;
    }

}
