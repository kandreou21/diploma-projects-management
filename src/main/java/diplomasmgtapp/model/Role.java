package diplomasmgtapp.model;

public enum Role {
	STUDENT("Student"),   //https://www.programiz.com/java-programming/enums h klash role tha einai eite User eite Admin(logika prepei na allaktei sta roles pou uparxoun sto susthma mas)
    PROFESSOR("Professor");
  	
	private final String value;
	
	private Role(String value) {
        this.value = value;
    }
	
	public String getValue() {
        return value;
    }
}
