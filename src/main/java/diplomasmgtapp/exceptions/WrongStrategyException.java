package diplomasmgtapp.exceptions;

public class WrongStrategyException extends Exception{

	private static final long serialVersionUID = -688484930509659637L;

	public WrongStrategyException() {
		super("Please check the assignment strategy that you chose");
	}
}
