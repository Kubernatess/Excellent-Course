import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 * 
 */
public class ConverterPDFUtil {

	private static ConverterPDFUtil Util;

	/**
	 * 获取Doc2HtmlUtil实例
	 */
	public static synchronized ConverterPDFUtil getInstance() {
		if (Util == null) {
			Util = new ConverterPDFUtil();
		}
		return Util;
	}
	
	/**
	 * 转换文件成pdf
	 */
	public void converter(File inputFile, File outputFile) throws IOException {
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
		try {
			connection.connect();
		} catch (ConnectException e) {
			System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
		}
		// convert
		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
		converter.convert(inputFile, outputFile);
		connection.disconnect();
	}

}
