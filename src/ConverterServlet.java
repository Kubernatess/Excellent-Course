

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/ConverterServlet")
public class ConverterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DiskFileItemFactory factory;
	private ServletFileUpload upload;
	private String uploadPath;
	private String contextPath;

	@Override
	public void init() throws ServletException {
		super.init();
		// 配置上传参数
        factory = new DiskFileItemFactory();
        upload = new ServletFileUpload(factory);        
        // 中文处理
        upload.setHeaderEncoding("UTF-8");
        // 这个路径相对当前应用的目录
        uploadPath = (String) getServletContext().getAttribute("uploadPath");
        contextPath = (String) getServletContext().getAttribute("contextPath");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseID = "";
        String tabIndex = "";
        String columnIndex = "";
        String fileName = "";
        FileItem fileItem = null;
        try {
            // 表单数据处理
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
            	for (FileItem item : formItems) {
	            	if (!item.isFormField()&&!item.getName().equals("")) {
	                	fileName = new File(item.getName()).getName();
	                	fileItem = item;
	                }
	                else{
	                	String field=item.getFieldName();
                        String value=item.getString("utf-8");
                        if(field.equals("courseID")){
                        	courseID = value;
                        }
                        else if(field.equals("tabIndex")){
                        	tabIndex = value;
                        }
                        else if(field.equals("columnIndex")){
                        	columnIndex = value;
                        }
	                }
	            }
            }
            // 对文件名进行hash编码,保留文件后缀名
            int endIndex = fileName.lastIndexOf(".");
            String fileType = fileName.substring(endIndex);
            String hashName = String.valueOf(fileName.substring(0, endIndex).hashCode());
            fileName = hashName+fileType;
            // 保存文件到硬盘
            String filePath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+fileName;
            File storeFile = new File(filePath);
            fileItem.write(storeFile);
            
            
    		// 获得pdf转换器实例
    		ConverterPDFUtil Util = ConverterPDFUtil.getInstance();
    		File inputFile = storeFile;
    		String dirPath = uploadPath+File.separator+courseID+File.separator+tabIndex+File.separator+columnIndex+File.separator+"converter";
    		String outputPath = dirPath+File.separator+hashName+".pdf";
    		File outputFile = new File(outputPath);
    		Util.converter(inputFile, outputFile);
            
            
            // 返回虚拟路径
            String vitualPath = contextPath+"/"+"upload"+"/"+courseID+"/"+tabIndex+"/"+columnIndex+"/"+"converter"+"/"+hashName+".pdf";
            PrintWriter out = response.getWriter();
            out.println(vitualPath);
        }catch (Exception ex) {
	    	request.setAttribute("message","错误信息: " + ex.getMessage());
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
