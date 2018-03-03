import com.mongodb.MongoClient;
import models.Course;
import models.Student;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import resources.*;

import java.io.IOException;
import java.net.URI;

public class Server {

    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {

        ResourceConfig rc = new ResourceConfig(StudentResource.class, CourseResource.class,
                StudentGradeResource.class, CourseListResource.class, CourseGradeResource.class);
        rc.packages("org.glassfish.jersey.examples.linking").register(DeclarativeLinkingFeature.class);
        rc.register(CustomHeaders.class);
        //rc.register(DateParamConverterProvider.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        final Morphia morphia = new Morphia();

        // tell Morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("org.wsi.lab.models");


        // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("localhost", 8004), "morphia_example");
        datastore.getDB().dropDatabase();

        datastore.ensureIndexes();

        if (datastore.getCount(Student.class) == 0 && datastore.getCount(Course.class) == 0) {
            MockData.populate(datastore);
        }

    }
}
