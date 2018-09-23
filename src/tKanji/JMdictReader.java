package tKanji;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tKanji.CheckKanji.CheckKanji;
import tKanji.JMdict.JMdictEntry;
import tKanji.JMdict.JMdictManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;

public class JMdictReader {
	public static void main(String[] args) {
//		System.setProperty("jdk.xml.entityExpansionLimit", "0");
//		JMdictManager.toDict("JMdict_e.xml","JMdictSimple.json");
//		System.clearProperty("jdk.xml.entityExpansionLimit");

		try {
			StringBuffer sb = new StringBuffer();
			JSONParser parser = new JSONParser();
			Object obj = parser
					.parse(new InputStreamReader(new FileInputStream("JMdictSimple.json"), StandardCharsets.UTF_8));

			JSONObject jsonObject = (JSONObject) obj;

			JSONArray msg = (JSONArray) jsonObject.get("dict");
			Iterator<JSONObject> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JSONObject jo = iterator.next();
				JMdictEntry entry = JMdictEntry.getEntry(jo);
				if (CheckKanji.checkKanjiWord((String) jo.get("kanjiElement"), CheckKanji.N5_KANJI)) {
					String hanviet = CheckKanji.getHanViet((String) jo.get("kanjiElement"),
							CheckKanji.N5_KANJI, CheckKanji.N5_KANJI_VN);
					sb.append(entry.toString() + "\t" + hanviet + "\n");
				}
			}
			Files.write(Paths.get("n5_kanji.txt"), sb.toString().getBytes("UTF8"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

}
