package com.novelbio.portal.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.util.StringUtils;

public class MIMETypes {
	private MIMETypes() {
	}

	private static final Map<String, String> _MIMETypeList = new HashMap<>();

	public static String getContentTypeFor(String fileName) {
		Validate.notEmpty(fileName);
		String[] fileNameParts = StringUtils.tokenizeToStringArray(fileName, ".");
		String part = ".";
		if (fileNameParts.length > 1) {
			part += fileNameParts[fileNameParts.length - 1];
		}
		return _MIMETypeList.get(part);
	}

	static {
		_MIMETypeList.put(".323", "text/h323");
		_MIMETypeList.put(".3gp", "video/3gpp");
		_MIMETypeList.put(".3gpp", "video/3gpp");
		_MIMETypeList.put(".acp", "audio/x-mei-aac");
		_MIMETypeList.put(".act", "text/xml");
		_MIMETypeList.put(".actproj", "text/plain");
		_MIMETypeList.put(".ade", "application/msaccess");
		_MIMETypeList.put(".adp", "application/msaccess");
		_MIMETypeList.put(".ai", "application/postscript");
		_MIMETypeList.put(".aif", "audio/aiff");
		_MIMETypeList.put(".aifc", "audio/aiff");
		_MIMETypeList.put(".aiff", "audio/aiff");
		_MIMETypeList.put(".asf", "video/x-ms-asf");
		_MIMETypeList.put(".asm", "text/plain");
		_MIMETypeList.put(".asx", "video/x-ms-asf");
		_MIMETypeList.put(".au", "audio/basic");
		_MIMETypeList.put(".avi", "video/avi");
		_MIMETypeList.put(".bmp", "image/bmp");
		_MIMETypeList.put(".bwp", "application/x-bwpreview");
		_MIMETypeList.put(".c", "text/plain");
		_MIMETypeList.put(".cat", "application/vnd.ms-pki.seccat");
		_MIMETypeList.put(".cc", "text/plain");
		_MIMETypeList.put(".cdf", "application/x-cdf");
		_MIMETypeList.put(".cer", "application/x-x509-ca-cert");
		_MIMETypeList.put(".cod", "text/plain");
		_MIMETypeList.put(".cpp", "text/plain");
		_MIMETypeList.put(".crl", "application/pkix-crl");
		_MIMETypeList.put(".crt", "application/x-x509-ca-cert");
		_MIMETypeList.put(".cs", "text/plain");
		_MIMETypeList.put(".css", "text/css");
		_MIMETypeList.put(".csv", "application/vnd.ms-excel");
		_MIMETypeList.put(".cxx", "text/plain");
		_MIMETypeList.put(".dbs", "text/plain");
		_MIMETypeList.put(".def", "text/plain");
		_MIMETypeList.put(".der", "application/x-x509-ca-cert");
		_MIMETypeList.put(".dib", "image/bmp");
		_MIMETypeList.put(".dif", "video/x-dv");
		_MIMETypeList.put(".dll", "application/x-msdownload");
		_MIMETypeList.put(".doc", "application/msword");
		_MIMETypeList.put(".dot", "application/msword");
		_MIMETypeList.put(".dsp", "text/plain");
		_MIMETypeList.put(".dsw", "text/plain");
		_MIMETypeList.put(".dv", "video/x-dv");
		_MIMETypeList.put(".edn", "application/vnd.adobe.edn");
		_MIMETypeList.put(".eml", "message/rfc822");
		_MIMETypeList.put(".eps", "application/postscript");
		_MIMETypeList.put(".etd", "application/x-ebx");
		_MIMETypeList.put(".etp", "text/plain");
		_MIMETypeList.put(".exe", "application/x-msdownload");
		_MIMETypeList.put(".ext", "text/plain");
		_MIMETypeList.put(".fdf", "application/vnd.fdf");
		_MIMETypeList.put(".fif", "application/fractals");
		_MIMETypeList.put(".fky", "text/plain");
		_MIMETypeList.put(".gif", "image/gif");
		_MIMETypeList.put(".gz", "application/x-gzip");
		_MIMETypeList.put(".h", "text/plain");
		_MIMETypeList.put(".hpp", "text/plain");
		_MIMETypeList.put(".hqx", "application/mac-binhex40");
		_MIMETypeList.put(".hta", "application/hta");
		_MIMETypeList.put(".htc", "text/x-component");
		_MIMETypeList.put(".htm", "text/html");
		_MIMETypeList.put(".html", "text/html");
		_MIMETypeList.put(".htt", "text/webviewhtml");
		_MIMETypeList.put(".hxx", "text/plain");
		_MIMETypeList.put(".i", "text/plain");
		_MIMETypeList.put(".iad", "application/x-iad");
		_MIMETypeList.put(".ico", "image/x-icon");
		_MIMETypeList.put(".ics", "text/calendar");
		_MIMETypeList.put(".idl", "text/plain");
		_MIMETypeList.put(".iii", "application/x-iphone");
		_MIMETypeList.put(".inc", "text/plain");
		_MIMETypeList.put(".infopathxml", "application/ms-infopath.xml");
		_MIMETypeList.put(".inl", "text/plain");
		_MIMETypeList.put(".ins", "application/x-internet-signup");
		_MIMETypeList.put(".iqy", "text/x-ms-iqy");
		_MIMETypeList.put(".isp", "application/x-internet-signup");
		_MIMETypeList.put(".java", "text/java");
		_MIMETypeList.put(".jfif", "image/jpeg");
		_MIMETypeList.put(".jnlp", "application/x-java-jnlp-file");
		_MIMETypeList.put(".jpe", "image/jpeg");
		_MIMETypeList.put(".jpeg", "image/jpeg");
		_MIMETypeList.put(".jpg", "image/jpeg");
		_MIMETypeList.put(".js", "text/javascript");
		_MIMETypeList.put(".jsl", "text/plain");
		_MIMETypeList.put(".kci", "text/plain");
		_MIMETypeList.put(".la1", "audio/x-liquid-file");
		_MIMETypeList.put(".lar", "application/x-laplayer-reg");
		_MIMETypeList.put(".latex", "application/x-latex");
		_MIMETypeList.put(".lavs", "audio/x-liquid-secure");
		_MIMETypeList.put(".lgn", "text/plain");
		_MIMETypeList.put(".lmsff", "audio/x-la-lms");
		_MIMETypeList.put(".lqt", "audio/x-la-lqt");
		_MIMETypeList.put(".lst", "text/plain");
		_MIMETypeList.put(".m1v", "video/mpeg");
		_MIMETypeList.put(".m3u", "audio/mpegurl");
		_MIMETypeList.put(".m4e", "video/mpeg4");
		_MIMETypeList.put(".MAC", "image/x-macpaint");
		_MIMETypeList.put(".mak", "text/plain");
		_MIMETypeList.put(".man", "application/x-troff-man");
		_MIMETypeList.put(".map", "text/plain");
		_MIMETypeList.put(".mda", "application/msaccess");
		_MIMETypeList.put(".mdb", "application/msaccess");
		_MIMETypeList.put(".mde", "application/msaccess");
		_MIMETypeList.put(".mdi", "image/vnd.ms-modi");
		_MIMETypeList.put(".mfp", "application/x-shockwave-flash");
		_MIMETypeList.put(".mht", "message/rfc822");
		_MIMETypeList.put(".mhtml", "message/rfc822");
		_MIMETypeList.put(".mid", "audio/mid");
		_MIMETypeList.put(".midi", "audio/mid");
		_MIMETypeList.put(".mk", "text/plain");
		_MIMETypeList.put(".mnd", "audio/x-musicnet-download");
		_MIMETypeList.put(".mns", "audio/x-musicnet-stream");
		_MIMETypeList.put(".MP1", "audio/mp1");
		_MIMETypeList.put(".mp2", "video/mpeg");
		_MIMETypeList.put(".mp2v", "video/mpeg");
		_MIMETypeList.put(".mp3", "audio/mpeg");
		_MIMETypeList.put(".mp4", "video/mp4");
		_MIMETypeList.put(".mpa", "video/mpeg");
		_MIMETypeList.put(".mpe", "video/mpeg");
		_MIMETypeList.put(".mpeg", "video/mpeg");
		_MIMETypeList.put(".mpf", "application/vnd.ms-mediapackage");
		_MIMETypeList.put(".mpg", "video/mpeg");
		_MIMETypeList.put(".mpg4", "video/mp4");
		_MIMETypeList.put(".mpga", "audio/rn-mpeg");
		_MIMETypeList.put(".mpv2", "video/mpeg");
		_MIMETypeList.put(".NMW", "application/nmwb");
		_MIMETypeList.put(".nws", "message/rfc822");
		_MIMETypeList.put(".odc", "text/x-ms-odc");
		_MIMETypeList.put(".odh", "text/plain");
		_MIMETypeList.put(".odl", "text/plain");
		_MIMETypeList.put(".p10", "application/pkcs10");
		_MIMETypeList.put(".p12", "application/x-pkcs12");
		_MIMETypeList.put(".p7b", "application/x-pkcs7-certificates");
		_MIMETypeList.put(".p7c", "application/pkcs7-mime");
		_MIMETypeList.put(".p7m", "application/pkcs7-mime");
		_MIMETypeList.put(".p7r", "application/x-pkcs7-certreqresp");
		_MIMETypeList.put(".p7s", "application/pkcs7-signature");
		_MIMETypeList.put(".PCT", "image/pict");
		_MIMETypeList.put(".pdf", "application/pdf");
		_MIMETypeList.put(".pdx", "application/vnd.adobe.pdx");
		_MIMETypeList.put(".pfx", "application/x-pkcs12");
		_MIMETypeList.put(".pic", "image/pict");
		_MIMETypeList.put(".PICT", "image/pict");
		_MIMETypeList.put(".pko", "application/vnd.ms-pki.pko");
		_MIMETypeList.put(".png", "image/png");
		_MIMETypeList.put(".pnt", "image/x-macpaint");
		_MIMETypeList.put(".pntg", "image/x-macpaint");
		_MIMETypeList.put(".pot", "application/vnd.ms-powerpoint");
		_MIMETypeList.put(".ppa", "application/vnd.ms-powerpoint");
		_MIMETypeList.put(".pps", "application/vnd.ms-powerpoint");
		_MIMETypeList.put(".ppt", "application/vnd.ms-powerpoint");
		_MIMETypeList.put(".prc", "text/plain");
		_MIMETypeList.put(".prf", "application/pics-rules");
		_MIMETypeList.put(".ps", "application/postscript");
		_MIMETypeList.put(".pub", "application/vnd.ms-publisher");
		_MIMETypeList.put(".pwz", "application/vnd.ms-powerpoint");
		_MIMETypeList.put(".qt", "video/quicktime");
		_MIMETypeList.put(".qti", "image/x-quicktime");
		_MIMETypeList.put(".qtif", "image/x-quicktime");
		_MIMETypeList.put(".qtl", "application/x-quicktimeplayer");
		_MIMETypeList.put(".qup", "application/x-quicktimeupdater");
		_MIMETypeList.put(".r1m", "application/vnd.rn-recording");
		_MIMETypeList.put(".r3t", "text/vnd.rn-realtext3d");
		_MIMETypeList.put(".RA", "audio/vnd.rn-realaudio");
		_MIMETypeList.put(".RAM", "audio/x-pn-realaudio");
		_MIMETypeList.put(".rat", "application/rat-file");
		_MIMETypeList.put(".rc", "text/plain");
		_MIMETypeList.put(".rc2", "text/plain");
		_MIMETypeList.put(".rct", "text/plain");
		_MIMETypeList.put(".rec", "application/vnd.rn-recording");
		_MIMETypeList.put(".rgs", "text/plain");
		_MIMETypeList.put(".rjs", "application/vnd.rn-realsystem-rjs");
		_MIMETypeList.put(".rjt", "application/vnd.rn-realsystem-rjt");
		_MIMETypeList.put(".RM", "application/vnd.rn-realmedia");
		_MIMETypeList.put(".rmf", "application/vnd.adobe.rmf");
		_MIMETypeList.put(".rmi", "audio/mid");
		_MIMETypeList.put(".RMJ", "application/vnd.rn-realsystem-rmj");
		_MIMETypeList.put(".RMM", "audio/x-pn-realaudio");
		_MIMETypeList.put(".rms", "application/vnd.rn-realmedia-secure");
		_MIMETypeList.put(".rmvb", "application/vnd.rn-realmedia-vbr");
		_MIMETypeList.put(".RMX", "application/vnd.rn-realsystem-rmx");
		_MIMETypeList.put(".RNX", "application/vnd.rn-realplayer");
		_MIMETypeList.put(".rp", "image/vnd.rn-realpix");
		_MIMETypeList.put(".RPM", "audio/x-pn-realaudio-plugin");
		_MIMETypeList.put(".rqy", "text/x-ms-rqy");
		_MIMETypeList.put(".rsml", "application/vnd.rn-rsml");
		_MIMETypeList.put(".rt", "text/vnd.rn-realtext");
		_MIMETypeList.put(".rtf", "application/msword");
		_MIMETypeList.put(".rul", "text/plain");
		_MIMETypeList.put(".RV", "video/vnd.rn-realvideo");
		_MIMETypeList.put(".s", "text/plain");
		_MIMETypeList.put(".sc2", "application/schdpl32");
		_MIMETypeList.put(".scd", "application/schdpl32");
		_MIMETypeList.put(".sch", "application/schdpl32");
		_MIMETypeList.put(".sct", "text/scriptlet");
		_MIMETypeList.put(".sd2", "audio/x-sd2");
		_MIMETypeList.put(".sdp", "application/sdp");
		_MIMETypeList.put(".sit", "application/x-stuffit");
		_MIMETypeList.put(".slk", "application/vnd.ms-excel");
		_MIMETypeList.put(".sln", "application/octet-stream");
		_MIMETypeList.put(".SMI", "application/smil");
		_MIMETypeList.put(".smil", "application/smil");
		_MIMETypeList.put(".snd", "audio/basic");
		_MIMETypeList.put(".snp", "application/msaccess");
		_MIMETypeList.put(".spc", "application/x-pkcs7-certificates");
		_MIMETypeList.put(".spl", "application/futuresplash");
		_MIMETypeList.put(".sql", "text/plain");
		_MIMETypeList.put(".srf", "text/plain");
		_MIMETypeList.put(".ssm", "application/streamingmedia");
		_MIMETypeList.put(".sst", "application/vnd.ms-pki.certstore");
		_MIMETypeList.put(".stl", "application/vnd.ms-pki.stl");
		_MIMETypeList.put(".swf", "application/x-shockwave-flash");
		_MIMETypeList.put(".tab", "text/plain");
		_MIMETypeList.put(".tar", "application/x-tar");
		_MIMETypeList.put(".tdl", "text/xml");
		_MIMETypeList.put(".tgz", "application/x-compressed");
		_MIMETypeList.put(".tif", "image/tiff");
		_MIMETypeList.put(".tiff", "image/tiff");
		_MIMETypeList.put(".tlh", "text/plain");
		_MIMETypeList.put(".tli", "text/plain");
		_MIMETypeList.put(".torrent", "application/x-bittorrent");
		_MIMETypeList.put(".trg", "text/plain");
		_MIMETypeList.put(".txt", "text/plain");
		_MIMETypeList.put(".udf", "text/plain");
		_MIMETypeList.put(".udt", "text/plain");
		_MIMETypeList.put(".uls", "text/iuls");
		_MIMETypeList.put(".user", "text/plain");
		_MIMETypeList.put(".usr", "text/plain");
		_MIMETypeList.put(".vb", "text/plain");
		_MIMETypeList.put(".vcf", "text/x-vcard");
		_MIMETypeList.put(".vcproj", "text/plain");
		_MIMETypeList.put(".viw", "text/plain");
		_MIMETypeList.put(".vpg", "application/x-vpeg005");
		_MIMETypeList.put(".vspscc", "text/plain");
		_MIMETypeList.put(".vsscc", "text/plain");
		_MIMETypeList.put(".vssscc", "text/plain");
		_MIMETypeList.put(".wav", "audio/wav");
		_MIMETypeList.put(".wax", "audio/x-ms-wax");
		_MIMETypeList.put(".wbk", "application/msword");
		_MIMETypeList.put(".wiz", "application/msword");
		_MIMETypeList.put(".wm", "video/x-ms-wm");
		_MIMETypeList.put(".wma", "audio/x-ms-wma");
		_MIMETypeList.put(".wmd", "application/x-ms-wmd");
		_MIMETypeList.put(".wmv", "video/x-ms-wmv");
		_MIMETypeList.put(".wmx", "video/x-ms-wmx");
		_MIMETypeList.put(".wmz", "application/x-ms-wmz");
		_MIMETypeList.put(".wpl", "application/vnd.ms-wpl");
		_MIMETypeList.put(".wprj", "application/webzip");
		_MIMETypeList.put(".wsc", "text/scriptlet");
		_MIMETypeList.put(".wvx", "video/x-ms-wvx");
		_MIMETypeList.put(".XBM", "image/x-xbitmap");
		_MIMETypeList.put(".xdp", "application/vnd.adobe.xdp+xml");
		_MIMETypeList.put(".xfd", "application/vnd.adobe.xfd+xml");
		_MIMETypeList.put(".xfdf", "application/vnd.adobe.xfdf");
		_MIMETypeList.put(".xla", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlb", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlc", "application/vnd.ms-excel");
		_MIMETypeList.put(".xld", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlk", "application/vnd.ms-excel");
		_MIMETypeList.put(".xll", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlm", "application/vnd.ms-excel");
		_MIMETypeList.put(".xls", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlt", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlv", "application/vnd.ms-excel");
		_MIMETypeList.put(".xlw", "application/vnd.ms-excel");
		_MIMETypeList.put(".xml", "text/xml");
		_MIMETypeList.put(".xpl", "audio/scpls");
		_MIMETypeList.put(".xsl", "text/xml");
		_MIMETypeList.put(".z", "application/x-compress");
		_MIMETypeList.put(".zip", "application/x-zip-compressed");
	}
}
