package net.sweetmonster.hocuspocusp5;

import java.util.jar.*;
import java.util.*;
import java.io.*;

public class PackageUtils {

	private static boolean debug = true;

	public static List getClasseNamesInPackage(String jarName,
			String packageName) {
		ArrayList classes = new ArrayList();

		packageName = packageName.replaceAll("\\.", "/");
		if (debug)
			System.out
					.println("Jar " + jarName + " looking for " + packageName);
		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(
					jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if ((jarEntry.getName().startsWith(packageName))
						&& (jarEntry.getName().endsWith(".class"))) {
					if (debug)
						System.out.println("Found "
								+ jarEntry.getName().replaceAll("/", "\\."));
					classes.add(jarEntry.getName().replaceAll("/", "\\."));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classes;
	}

	/**
	*
	*/
	public static void main(String[] args) {
		List list = PackageUtils.getClasseNamesInPackage(
				"C:/j2sdk1.4.1_02/lib/mail.jar", "com.sun.mail.handlers");
		System.out.println(list);
		/*
		 * output :
		 * 
		 * Jar C:/j2sdk1.4.1_02/lib/mail.jar looking for com/sun/mail/handlers
		 * Found com.sun.mail.handlers.text_html.class Found
		 * com.sun.mail.handlers.text_plain.class Found
		 * com.sun.mail.handlers.text_xml.class Found
		 * com.sun.mail.handlers.image_gif.class Found
		 * com.sun.mail.handlers.image_jpeg.class Found
		 * com.sun.mail.handlers.multipart_mixed.class Found
		 * com.sun.mail.handlers.message_rfc822.class
		 * [com.sun.mail.handlers.text_html.class,
		 * com.sun.mail.handlers.text_xml.class, com
		 * .sun.mail.handlers.image_jpeg.class, ,
		 * com.sun.mail.handlers.message_rfc822.class]
		 */
	}
}
