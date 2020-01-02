
package acme.features.utiles;

public class Spamfilter {

	public static Boolean spamThreshold(final String text, final String spamWordString, final Double spamThresholdLimit) {
		Boolean res = false;
		Double textThreshold = 0.0;
		String[] textWords = text.split(" ");
		String[] spamWords = spamWordString.split(",");
		Double textLength = (double) textWords.length;
		for (int i = 0; i < spamWords.length; i++) {
			spamWords[i] = spamWords[i].trim();
		}

		for (String i : textWords) {
			if (Spamfilter.matchWithSpam(i, spamWords)) {
				textThreshold += 1.0;
			}
		}

		if (textThreshold / textLength * 100 > spamThresholdLimit) {
			res = true;
		}

		return res;
	}

	private static Boolean matchWithSpam(final String word, final String[] spamWords) {
		boolean res = false;
		for (String i : spamWords) {
			if (word.contains(i)) {
				res = true;
			}
		}
		return res;
	}
}
