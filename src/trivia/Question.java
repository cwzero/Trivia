package trivia;

public class Question {
	private String text;

	public Question(String text) {
		setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Question) {
			return ((Question) obj).getText().equals(getText());
		}
		return super.equals(obj);
	}
}
