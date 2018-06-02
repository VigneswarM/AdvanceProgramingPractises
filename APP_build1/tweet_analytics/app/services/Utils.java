package services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Represents Utils.
 * @author Prasanth, Vaishnavi, Anil, Simarpreet
 *
 * @version 1.0
 * @since 1.0
 */

public class Utils {

	/**
	 * Replaces the URL in tweet text, with html tags
	 * @param text Tweet text
	 * @return text with url replaced with html tags
	 * @throws returns normal tweet text if an exception is thrown
	 */
	
	public String replaceUrlWithHtmlTags(String text){
		try {
		    // Pattern for recognizing a URL, based off RFC 3986
		    final Pattern urlPattern = Pattern.compile(
		            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
		                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
		                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
		            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		    Matcher matcher = urlPattern.matcher(text);
		    ArrayList<String> urlList=new ArrayList<>();
		    while (matcher.find()) {
		        int matchStart = matcher.start(1);
		        int matchEnd = matcher.end();
		        urlList.add(text.substring(matchStart, matchEnd));
		    }
	
		    for(String url:urlList) {
		        text = text.replaceAll(url, "<a href=\"" + url + "\" target=\"_blank\" >" + url + "</a>");
		    }
	    return text;
		}catch(Exception e) {
			return text;
		}
	}
}
