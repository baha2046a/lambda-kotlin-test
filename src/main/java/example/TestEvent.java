package example;

public class TestEvent {
	private String operation;
	private String message;

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getOperation() {
		return operation;
	}
}
