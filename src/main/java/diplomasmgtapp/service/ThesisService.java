package diplomasmgtapp.service;
//not used
public interface ThesisService {

	public void setImplementationGrade(double implementationGrade, int id);
	public void setReportGrade(double reportGrade, int id);
	public void setPresentationGrade(double presentationGrade, int id);
	public double calculateThesisGrade(int id);
}
