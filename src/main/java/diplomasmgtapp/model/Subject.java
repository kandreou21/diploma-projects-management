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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="subjects")
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="objectives")
	private String objectives;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="subject")
	private List<Application> applications;
	
	@ManyToOne
	@JoinColumn(name="supervisor_id")
	private Professor supervisor; //xreiazetai gia na ginetai get apo tous Students poios einai o ekastoste Prof enos mathimatos 
	
	public Subject() {}

	public Subject(String title, String objectives, Professor supervisor) { //extracted from Professor user stories 
		this.title = title;
		this.objectives = objectives;
		this.supervisor = supervisor;
		this.applications = new ArrayList<Application>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public Professor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Professor supervisor) {
		this.supervisor = supervisor;
	}

	public void addApplication(Application application) {	//https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/
		applications.add(application);
		application.setSubject(this);	
	}
	
	public void removeApplication(Application application) {
		applications.remove(application);
	}
}
