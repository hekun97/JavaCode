package io.gitee.hek97.web.download;

import io.gitee.hek97.web.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过获取请求参数，获取文件名称
        String filename = request.getParameter("filename");
        //2.使用字节输入流加载文件进内存
        //2.1找到文件的真实路径
        ServletContext ServletContext = this.getServletContext();
        String realPath = ServletContext.getRealPath("/img/" + filename);
        //2.2用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //3.设置response的响应头
        //3.1设置响应头类型content-type
        String mimeType = ServletContext.getMimeType(filename);//获取文件的类型
        response.setHeader("content-type",mimeType);
        //解决中文乱码问题
        //1.获取user-agent中的浏览器信息
        String agent = request.getHeader("user-agent");
        //2.使用工具类解决中文乱码问题，返回设置好格式的字符串
        filename = DownLoadUtils.getFileName(agent, filename);
        //3.2设置响应头content-disposition以附件的形式打开，也就是下载
        response.setHeader("content-disposition","attachment;filename="+filename);
        //4.将输出流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len =0;
        while(((len=fis.read(buff))!=-1)){
            sos.write(buff,0,len);
        }
        fis.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
