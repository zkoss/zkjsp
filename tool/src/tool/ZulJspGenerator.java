/* ZulJspGenerator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jul 26, 2007 2:34:20 PM 2007, Created by Ian Tsai 
		Nov 21, 2008 4:32:21 PM 2008, Modified by Flyworld
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */
package tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ian
 * 
 */
public class ZulJspGenerator {
	String _respositoryPath = "";
	String _author = "Flyworld"; // Default User
	String _package = "org.zkoss.jsp.zul"; // Default zkjsp package
	boolean _genTld = true;
	boolean _genJava = true;
	boolean _override = false;

	public ZulJspGenerator(String respositoryPath) {
		_respositoryPath = respositoryPath;
	}

	public ZulJspGenerator(String respositoryPath, boolean genTld,
			boolean genJava, boolean override, String author) {

		_respositoryPath = respositoryPath;
		_genTld = genTld;
		_genJava = genJava;
		_override = override;
		_author = author;
	}

	public boolean generate(ComponentMap cmpMap) {

		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss ");
		SimpleDateFormat form2 = new SimpleDateFormat("yyyy");

		String svnPath = _respositoryPath
				+ "zuljsp\\src\\org\\zkoss\\jsp\\zul\\";
		String outTld = _respositoryPath
				+ "zuljsp\\src2\\archive\\META-INF\\zuljsp.tld";
		String templatePath = _respositoryPath + "tool\\templete\\";
		String[][] values = (String[][]) cmpMap.getMapVales();
		String[] keys = (String[]) cmpMap.getMapKeys();
		List<String[]> lst = new LinkedList<String[]>();
		for (int i = 0; i < values.length; i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(keys[i]);
			for (String value : values[i]) {
				temp.add(value);
			}
			lst.add((String[]) temp.toArray(new String[] {}));
		}
		String[][] zulClasses = (String[][]) lst.toArray(new String[][] {});

		/**
		 * These are templete
		 * */
		String tempTagJava = templatePath + "TempleteTag.java";
		String tagContentTld = templatePath + "tagContent.tld";
		String templateTld = templatePath + "template.tld";

		final PreProcessor pro = new PreProcessor("${", "}");
		/**
		 *
		 */
		pro.setVariable("datetime", form.format(new Date()));
		pro.setVariable("year", form2.format(new Date()));
		pro.setVariable("package", _package);
		pro.setVariable("author", _author);

		try {
			List<String> javaTagPattern = loadTxtFile(tempTagJava);
			List<String> tagTldPattern = loadTxtFile(tagContentTld);
			List<String> tldPattern = loadTxtFile(templateTld);

			PrintStream tldPrint = null;
			int index = 0;
			if (_genTld)// tld Header...
			{
				tldPrint = createPrinter(outTld);
				String temp;
				for (; index < tldPattern.size(); index++)
					if ((temp = tldPattern.get(index)).indexOf("#{}") < 0)
						pro.preProcess(tldPrint, temp);
					else
						break;
				index++;
			}

			for (String[] loading : zulClasses) {
				for (int i = 1; i < loading.length; i++) {
					String clazz = loading[i].substring(0, 1).toUpperCase()
							+ loading[i].substring(1);
					// Because the "FCKeditor"
					clazz = clazz.equals("Fckeditor") ? "FCKeditor" : clazz;
					System.out.println("Generating Class : " + clazz);
					pro.setVariable("class", clazz);
					pro.setVariable("importclass", loading[0] + "." + clazz);
					pro.setVariable("filename", clazz + "Tag.java");
					if (_genJava)
						generateTagJavaFile(pro, clazz, svnPath,
								javaTagPattern, _override);

					// tld body...
					if (_genTld)
						for (String tag : tagTldPattern)
							pro.preProcess(tldPrint, tag);
				}
			}
			// tld footer...
			if (_genTld) {
				for (; index < tldPattern.size(); index++)
					pro.preProcess(tldPrint, tldPattern.get(index));
				tldPrint.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public void setAutor(String author) {
		_author = author;
	}

	public String getAutor() {
		return _author;
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static List<String> loadTxtFile(String path) throws IOException {
		List<String> ansList = new LinkedList<String>();

		BufferedReader re = new BufferedReader(new FileReader(path));
		String line = null;
		while ((line = re.readLine()) != null)
			ansList.add(line);
		return ansList;
	}

	/**
	 * 
	 * @param pro
	 * @param className
	 * @param outPath
	 * @param pattern
	 * @param _override
	 * @return
	 * @throws IOException
	 */
	private static boolean generateTagJavaFile(PreProcessor pro,
			String className, String outPath, List<String> pattern,
			boolean _override) throws IOException {
		// output works...
		String oFile = outPath + className + "Tag.java";
		if (!_override && (new File(oFile)).exists()) {
			System.out.println("[Warning] : " + oFile
					+ " is existed ! nothing generated.");
			return false;
		} else if ((new File(oFile)).exists()) {
			System.out.print("[Override] ");
		}
		PrintStream print = createPrinter(oFile);
		for (String str : pattern)
			pro.preProcess(print, str);
		print.close();
		System.out.println(oFile + " : is created.");
		return true;
	}

	/**
	 * 
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	private static PrintStream createPrinter(String filepath)
			throws IOException {
		File file = new File(filepath);
		if (file.exists())
			file.delete();
		if (!file.createNewFile())
			throw new IOException("file Create Faild:" + filepath);
		// output works...
		return new PrintStream(file);
	}

}// end of class
