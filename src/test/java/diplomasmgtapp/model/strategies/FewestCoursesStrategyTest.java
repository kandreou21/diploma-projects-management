package diplomasmgtapp.model.strategies;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.util.List;
import diplomasmgtapp.model.Application;
import diplomasmgtapp.model.Subject;
import diplomasmgtapp.model.Student;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application.properties")
class FewestCoursesStrategyTest {
	private static Subject subject;
	private static Student student;
	private static Student studentOther;
	private static Application appWinner;
	private static Application appOther;

	@Autowired
	private FewestCoursesStrategy fewestCourses;

	@BeforeAll
	static void setup() {
		subject = new Subject();
		student = new Student("student", 5, 5, 5);
		studentOther = new Student("studentOther", 1, 10, 25);
        appWinner = new Application(student, subject);
        appOther = new Application(studentOther, subject);
    }
	
	@Test
	void testFewestCourses() {
		assertEquals(fewestCourses.findBestApplicant(List.of(appWinner, appOther)), appWinner);
	}
}
