package io.gitee.hek97.loginTest.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //1.创建对象，在内存中存图片（验证码）对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        //2.美化图片
        //2.1填充背景色
        Graphics g = image.getGraphics();//获取画笔对象
        g.setColor(Color.PINK);//设置画笔颜色
        g.fillRect(0,0,width,height);//设置填充范围
        //2.2画边框
        g.setColor(Color.BLUE);//设置画笔颜色
        g.drawRect(0,0,width-1,height-1);//设置边框范围

        String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        //生成随机角标
        Random ran = new Random();//获取随机数对象
        char[] chars = new char[4];//char数组
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());//生成随机数
            char ch = str.charAt(index);//获取相应位置的字符
            //2.3写验证码
            g.drawString(ch+"",width/5*i,height/2);
            //放入chars数组
            chars[i-1]=ch;
        }
        String s = new String(chars);//将字符数组转为字符串
        HttpSession session = request.getSession();//获取session对象
        session.setAttribute("jude",s);//存储session
        //2.4画干扰线
        g.setColor(Color.GREEN);//设置画笔颜色
        //画10条干扰线
        for (int i = 0; i < 10; i++) {
            //随机生成坐标点
            int x1=ran.nextInt(width);
            int x2=ran.nextInt(width);
            int y1=ran.nextInt(height);
            int y2=ran.nextInt(height);
            //画1条干扰线
            g.drawLine(x1,x2,y1,y2);

        }
        //3.将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
