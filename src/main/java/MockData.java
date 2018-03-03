import models.Course;
import models.Grade;
import models.Student;
import org.mongodb.morphia.Datastore;

public class MockData {

    public static void populate(Datastore datastore) {

        Course English = new Course("Mr. Englishman", "English");
        Course Math = new Course("The scary old Profesor", "Math");
        Course Physics = new Course("Albert Heisenberg", "Physics");
        Course Physics2 = new Course("Albert Heisenberg", "Physics2");
        Course Spanish = new Course("Senor Spaniard", "Spanish");

        Student one = new Student("One", "First", 101202, "1994-12-05");
        Student two = new Student("Two", "Second", 220022, "1995-02-02");
        Student three = new Student("Three", "Third", 123122, "1992-24-09");

        one.addCourse(English);
        one.addCourse(Math);

        two.addCourse(Spanish);
        two.addCourse(Physics);
        two.addCourse(Math);

        one.addGrade(new Grade(3.5, "2014-03-10"), "English");
        one.addGrade(new Grade(3.0, "2014-03-11"), "English");
        one.addGrade(new Grade(4.0, "21-04-2014"), "English");

        one.addGrade(new Grade(4.5, "2013-03-20"), "Math");
        one.addGrade(new Grade(3.5, "2013-03-05"), "Math");
        one.addGrade(new Grade(4.5, "2013-06-24"), "Math");
        one.addGrade(new Grade(5.0, "2013-05-17"), "Math");

        two.addGrade(new Grade(2.0, "2014-03-20"), "Spanish");
        two.addGrade(new Grade(3.0, "2014-02-31"), "Spanish");
        two.addGrade(new Grade(2.0, "2014-03-01"), "Spanish");

        two.addGrade(new Grade(4.0, "2013-03-26"), "Physics");
        two.addGrade(new Grade(3.5, "2013-03-15"), "Physics");
        two.addGrade(new Grade(4.5, "2013-06-04"), "Physics");
        two.addGrade(new Grade(5.0, "2013-05-27"), "Physics");

        two.addGrade(new Grade(5.0, "2013-04-28"), "Math");
        two.addGrade(new Grade(3.5, "2013-03-06"), "Math");
        two.addGrade(new Grade(4.0, "2013-04-17"), "Math");
        two.addGrade(new Grade(5.0, "2013-05-09"), "Math");

        datastore.save(English);
        datastore.save(Spanish);
        datastore.save(Math);
        datastore.save(Physics);
        datastore.save(Physics2);

        datastore.save(one);
        datastore.save(two);
        datastore.save(three);

    }
}
