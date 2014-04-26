package main;

public class Wrap {
	private int maxWidth;
	private int minWidth;
	private int wordIterator = 0;
	private int lineWidth = 0;
	private String newText;
	private String words[];

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}

	private void deleteLastSpaces() {
		newText = newText.replaceAll(" +$", "");
	}
	
	private void initVariables(String rawText) {
		words = rawText.split(" ");
		newText = "";
		wordIterator = 0;
		lineWidth = 0;
	}
	
	private boolean isEnoughSpaceForWord(int wordLength) {
		return lineWidth + wordLength <= maxWidth;
	}

	private boolean isMoreWords() {
		return wordIterator < words.length;
	}

	private boolean isEnoghCharInLine() {
		return lineWidth >= minWidth;
	}
	
	private void fillLine() {
		while (isMoreWords() && isEnoughSpaceForWord(words[wordIterator].length())) {
			newText += words[wordIterator] + " ";
			lineWidth += words[wordIterator].length() + 1;
			wordIterator++;
		}
	}

	private void wrapSign(String word) {
		newText += word.substring(0, maxWidth - lineWidth - 1) + "-\n";
		if (isEnoughSpaceForWord(word.substring(maxWidth - lineWidth - 1).length())) {
			newText += word.substring(maxWidth - lineWidth - 1) + " ";
			lineWidth = word.substring(maxWidth - lineWidth - 1).length() + 1;
			wordIterator++;
		} else {
			wrapSign(word.substring(maxWidth - lineWidth - 1));
		}
	}

	private void newLine() {
		newText += "\n";
		lineWidth = 0;
	}

	public String wrapText(String rawText) {
		initVariables(rawText);
		do {
			fillLine();
			if (isMoreWords()) {
				if (isEnoghCharInLine()) {
					newLine();
				} else {
					wrapSign(words[wordIterator]);
				}
			}
			deleteLastSpaces();
		} while (isMoreWords());
		return newText;
	}

}
