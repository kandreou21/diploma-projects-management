package diplomasmgtapp.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="students")
public class Student{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="fullname")
	private String fullname;
	
	@Column(name="year_of_studies")
	private int yearOfStudies;
	
	@Column(name="avg_grade")
	private double averageGrade;
	
	@Column(name="courses_remain")
	private int remainingCourses;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="applicantStudent")
	private List<Application> applications;
	
	@Column(name="user_name", unique=true)
	private String username;

	public Student() {}
	
	public Student(String fullname, int yearOfStudies, double averageGrade, int remainingCourses) {
		this.fullname = fullname;
		this.yearOfStudies = yearOfStudies;
		this.averageGrade = averageGrade;
		this.remainingCourses = remainingCourses;
		this.applications = new ArrayList<Application>();
	}
	
	public Student(String username) {
		this.username = username;
	}
	//why have username as field and not set their fullnames with the User's username value? because if we change the fullname username!=fullname we will not be able to retrieve profile
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getYearOfStudies() {
		return yearOfStudies;
	}

	public void setYearOfStudies(int yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public int getRemainingCourses() {
		return remainingCourses;
	}

	public void setRemainingCourses(int remainingCourses) {
		this.remainingCourses = remainingCourses;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public void applyToDiplomaThesis(Application application) {
		if (!containsApplication(application)) {
			applications.add(application);
		}
		application.setApplicantStudent(this);
	}
	
	public boolean containsApplication(Application application) {//checks if an app is in List applications 
		for (Application app : applications) {
			if (app.equals(application)) 
				return true;
		}
		return false;
	}
	
}
