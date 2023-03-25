package diplomasmgtapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name="applications")
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	//mono kai mono gia thn dhmiourgia enos application(Constructor) ta bazo os fields ta parakato 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_id")	
	private Student applicantStudent; //Owning side prepei na uparxei o student

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subject_id")	 //Owning side prepei na uparxei to subject
	private Subject subject;
	
	public Application() {}
	
	public Application(int id,Student applicantStudent, Subject subject) {
		this.id = id;
		this.applicantStudent = applicantStudent;
	}
	
	public Application(Student applicantStudent, Subject subject) {
		this.applicantStudent = applicantStudent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public Student getApplicantStudent() {
		return applicantStudent;
	}
	
	public void setApplicantStudent(Student applicantStudent) {
		this.applicantStudent = applicantStudent;
	}

	public String toString() {
		return "Application [id: " + id + ", Applicant Student: " + applicantStudent + ", Subject: " + subject + "]";
	}
}
