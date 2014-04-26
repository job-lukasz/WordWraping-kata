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

	private void fillLine() {
		while ((wordIterator < words.length) && (lineWidth + words[wordIterator].length() <= maxWidth)) {
			newText += words[wordIterator] + " ";
			lineWidth += words[wordIterator].length() + 1;
			wordIterator++;
		}
	}

	private void wrapSign(String word) {
		newText += word.substring(0, maxWidth - lineWidth - 1) + "-\n";
		if (word.substring(maxWidth - lineWidth - 1).length() > maxWidth) {
			wrapSign(word.substring(maxWidth - lineWidth - 1));
		} else {
			newText += word.substring(maxWidth - lineWidth - 1) + " ";
			lineWidth = word.substring(maxWidth - lineWidth - 1).length() + 1;
			wordIterator++;
		}
	}

	private void newLine() {
		newText += "\n";
		lineWidth = 0;
	}

	public String wrapText(String rawText) {
		words = rawText.split(" ");
		newText = "";
		wordIterator = 0;
		lineWidth = 0;
		do {
			fillLine();
			if (wordIterator < words.length) {
				if (lineWidth >= minWidth) {
					newLine();
				} else {
					wrapSign(words[wordIterator]);
				}
			}
			deleteLastSpaces();
		} while (wordIterator < words.length);

		return newText;
	}
}
