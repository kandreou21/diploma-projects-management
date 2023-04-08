package diplomasmgtapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="theses")
public class Thesis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
		
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name="subject_id")
	private Subject subject;
	
	@Column(name="implementation_grade")
	private double implementationGrade;
	
	@Column(name="report_grade")
	private double reportGrade;
	
	@Column(name="presentation_grade")
	private double presentationGrade;
	
	@Transient
	private double overallGrade;
	
	public Thesis() {}

	public Thesis(Student student, Subject subject) {
		this.student = student;
		this.subject = subject;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public double getImplementationGrade() {
		return implementationGrade;
	}

	public void setImplementationGrade(double implementationGrade) {
		this.implementationGrade = implementationGrade;
	}

	public double getReportGrade() {
		return reportGrade;
	}

	public void setReportGrade(double reportGrade) {
		this.reportGrade = reportGrade;
	}

	public double getPresentationGrade() {
		return presentationGrade;
	}

	public void setPresentationGrade(double presentationGrade) {
		this.presentationGrade = presentationGrade;
	}

	public double getOverallGrade() {
		return 0.7*implementationGrade + 0.15*reportGrade + 0.15*presentationGrade;
	}

	@Override
	public String toString() {
		return "Thesis [id=" + id + ", student=" + student + ", subject=" + subject + ", implementationGrade="
				+ implementationGrade + ", reportGrade=" + reportGrade + ", presentationGrade=" + presentationGrade
				+ ", overallGrade=" + overallGrade + "]";
	}
}
