package li.ren.util;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传工具类
 */
public class UploadUtil {
    public Map<String, String> upload(HttpServletRequest request) throws UnsupportedEncodingException {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        Map<String,String> map = new HashMap<>();
        request.setCharacterEncoding("utf-8");
        //使用工具类去解析request  表单所有的数据都在这这个集合中
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (fileItem.isFormField()) {
                    byte[] bytes = fileItem.getString().getBytes("ISO-8859-1");
                    String string = new String(bytes,"UTF-8");
                    map.put(fileItem.getFieldName(),string);
                } else {
                    //文件
                    //文件应该以什么样的格式保存  文件名包含文件后缀
                    String fileName = fileItem.getName();
                    int i = fileName.lastIndexOf("\\");

                    if (i != -1) {
                        //截取字符串
                        fileName = fileName.substring(i + 1);
                    }
                    //文件内容
                    try {
                        InputStream inputStream = fileItem.getInputStream();
                        String path = request.getServletContext().getRealPath("/upload");
                        File file = new File(path);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        fileName =System.currentTimeMillis() + fileName;
                        map.put("fileName",fileName);
                        //将文件保存到服务器的本地
                        FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator +  fileName);
                        //将文件输入流信息， 读取到一个字节数组中
                        int len;
                        byte[] bytes = new byte[1024];
                        while ((len = (inputStream.read(bytes))) != -1) {
                            fileOutputStream.write(bytes, 0, len);
                            fileOutputStream.flush();
                        }
                        fileOutputStream.close();
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return map;
    }



}

