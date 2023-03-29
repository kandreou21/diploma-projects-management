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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="professors")
public class Professor{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="specialty")
	private String specialty;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="supervisor")
	private List<Subject> subjects;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="professor_id")
	private List<Thesis> theses;
	
	@Column(name="user_name", unique=true)
	private String username;

	public Professor() {}
	
	public Professor(int id, String fullname, String specialty) {
		this.id = id;
		this.fullname = fullname;
		this.specialty = specialty;
		this.subjects = new ArrayList<Subject>();
		this.theses = new ArrayList<Thesis>();
	}
	
	public Professor(String fullname, String specialty) {
		this.fullname = fullname;
		this.specialty = specialty;
		this.subjects = new ArrayList<Subject>();
		this.theses = new ArrayList<Thesis>();
	}
	
	public Professor(String username) {
		this.username = username;
	}
	//why have username as field and not set their fullnames with the User's username value? because if we change the fullname username!=fullname we will not be able to retrieve profile

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public List<Subject> getSubjects() { return subjects; }
	
	public List<Thesis> getTheses() {
		return theses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addSubject(Subject subject) {
		subjects.add(subject);
	}
	
	public void removeSubject(Subject subject) {
		subjects.remove(subject);
	}
	
	public void assignThesis(Thesis thesis) {
		theses.add(thesis);
	}

	//tha pairnoun orisma gia poio thesis subject na settaroun to grade kai tha kanoun call tis Thesis methods
	public void setImplementationGrade(Thesis thesis, double implementationGrade) {
		theses.get(theses.indexOf(thesis)).setImplementationGrade(implementationGrade);
	}
	
	public void setReportGrade(Thesis thesis, double reportGrade) {
		theses.get(theses.indexOf(thesis)).setReportGrade(reportGrade);
	}

	public void setPresentationGrade(Thesis thesis, double presentationGrade) {
		theses.get(theses.indexOf(thesis)).setPresentationGrade(presentationGrade);
	}
	
	public double calculateThesisGrade(Thesis thesis) {
		return theses.get(theses.indexOf(thesis)).getOverallGrade();
	}
	
	public String toString() {
		return "Professor [id=" + id + ", fullname=" + fullname + ", specialty=" + specialty + ", username=" + username
				+ "]";
	}

}
