package main;

public class Wrap {
	private int maxWidth;
	private int minWidth;
	private int wordIterator = 0;
	private int lineWidth = 0;
	private StringBuilder newText;
	private String words[];

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	private void initVariables(String rawText) {
		words = rawText.split(" ");
		newText = new StringBuilder();
		wordIterator = 0;
		lineWidth = 0;
	}

	private boolean isEnoughSpaceForWord(int wordLength) {
		return lineWidth + wordLength <= maxWidth;
	}

	private boolean isMoreWords() {
		return wordIterator < words.length;
	}

	private boolean isEnoughCharInLine() {
		return lineWidth >= minWidth;
	}

	private void newLine() {
		newText.append("\n");
		lineWidth = 0;
	}

	private void deleteLastSpaces() {
		newText = new StringBuilder(newText.toString().replaceAll(" +$", ""));
		newText = new StringBuilder(newText.toString().replaceAll(" +\n", "\n"));
	}

	private void addWord() {
		String word = words[wordIterator];
		newText.append(word + " ");
		lineWidth += word.length() + 1;
		wordIterator++;
	}

	private void wrapWord() {
		String prefix = words[wordIterator].substring(0, maxWidth - lineWidth - 1);
		String suffix = words[wordIterator].substring(maxWidth - lineWidth - 1);
		newText.append(prefix + "-");
		newLine();
		words[wordIterator] = suffix;
	}

	public String wrapText(String rawText) {
		initVariables(rawText);
		while (isMoreWords()) {
			if (isEnoughSpaceForWord(words[wordIterator].length())) {
				addWord();
			} else if (isEnoughCharInLine()) {
				newLine();
			} else {
				wrapWord();
			}
		}
		deleteLastSpaces();
		return newText.toString();
	}

}
