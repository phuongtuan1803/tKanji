package tKanji.JMdict;

import org.json.simple.JSONObject;

public class JMdictEntry {
	JSONObject jo = new JSONObject();

	public static JMdictEntry getEntry(JSONObject o) {
		JMdictEntry retVal = new JMdictEntry();
		JSONObject jo = new JSONObject();
		jo.put("kanjiElement", o.get("kanjiElement"));
		jo.put("readingElement", o.get("readingElement"));
		jo.put("partOfSpeech", o.get("partOfSpeech"));
		jo.put("gloss", o.get("gloss"));
		retVal.setJSONObject(jo);
		return retVal;
	}

	public JSONObject getJSONObject() {
		return jo;
	}

	public void setJSONObject(JSONObject jo) {
		this.jo = jo;
	}

	public void setKanjiElement(String kanjiElement) {
		if (jo.get("kanjiElement") == null)
			jo.put("kanjiElement", kanjiElement);
	}

	public void setReadingElement(String readingElement) {
		if (jo.get("readingElement") == null)
			jo.put("readingElement", readingElement);
	}

	public void setPartOfSpeech(String partOfSpeech) {
		if (jo.get("partOfSpeech") == null)
			jo.put("partOfSpeech", partOfSpeech);
	}

	public void setGloss(String gloss) {
		if (jo.get("gloss") == null)
			jo.put("gloss", gloss);
	}

	public JSONObject getJSonObject() {
		return jo;
	}

	@Override
	public String toString() {
		String kanjiElement = jo.get("kanjiElement") == null ? "\t" : jo.get("kanjiElement").toString();
		String readingElement = jo.get("readingElement") == null ? "\t" : jo.get("readingElement").toString();
		String partOfSpeech = jo.get("partOfSpeech") == null ? "\t" : jo.get("partOfSpeech").toString();
		String gloss = jo.get("gloss") == null ? "\t" : jo.get("gloss").toString();
		return kanjiElement + "\t" +readingElement + "\t" + partOfSpeech + "\t"+ gloss;
	}

}
