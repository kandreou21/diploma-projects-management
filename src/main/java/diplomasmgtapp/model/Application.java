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
public class Application{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="student_id")	
	private Student applicantStudent; //Owning side(fk) prepei na uparxei o student

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="subject_id")	 //Owning side(fk) prepei na uparxei to subject
	private Subject subject;
	
	public Application() {}
	
	public Application(Student applicantStudent, Subject subject) {
		this.applicantStudent = applicantStudent;
		this.subject = subject;
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

	public boolean equals(Application app) {
		if (applicantStudent.equals(app.getApplicantStudent()) && 
			subject.equals(app.getSubject())) {
			return true;
		}
		return false;
	}
}
