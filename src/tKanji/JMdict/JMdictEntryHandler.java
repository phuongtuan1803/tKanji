package tKanji.JMdict;

import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class JMdictEntryHandler extends DefaultHandler {
	private JSONArray listJson = new JSONArray();
	private StringBuilder sb = new StringBuilder();
	private JMdictEntry entry = null;
	private boolean bk_ele = false;
	private boolean br_ele = false;
	private boolean bpos = false;
	private boolean bgloss = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("entry")) {
			entry = new JMdictEntry();			
		}else if (qName.equalsIgnoreCase("keb")) {
			bk_ele = true;		} 
		else if (qName.equalsIgnoreCase("reb")) {
			br_ele = true;
		} else if (qName.equalsIgnoreCase("pos")) {
			bpos = true;
		} else if (qName.equalsIgnoreCase("gloss")) {
			bgloss = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("entry")) {
        	listJson.add(entry.getJSonObject());
        }
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bk_ele) {
			entry.setKanjiElement(new String(ch, start, length));
			bk_ele = false;
		} else if (br_ele ) {
			entry.setReadingElement(new String(ch, start, length));
			br_ele = false;
		} else if (bpos) {
			entry.setPartOfSpeech(new String(ch, start, length));
			bpos = false;
		} else if (bgloss) {
			entry.setGloss(new String(ch, start, length));
			bgloss = false;
		}
	}

	@Override
	public String toString() {
		JSONObject jo = new JSONObject();
		jo.put("dict", listJson);
		return jo.toJSONString();
	}


}