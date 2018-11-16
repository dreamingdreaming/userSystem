package li.ren.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码的制作与存储
 */
public class ImageUtil {
    /*图片的宽度*/
    private static int width = 120;
    /*图片的高度*//*图片的高度*/
    private static int height = 26;
    /*字体高度*/
    private static  int fontHeight =18;
    /*验证码的位数*/
    private static  int codeCount = 4;
    /*用于生成随机验证码的数组*/
    private static char[] codeSequence = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'
    ,'O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    private static int xx = 14;
    private static int codeY=15;

    public static Map<String,Object> getImagePic(){
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = buffImg.getGraphics();

        /*产生随机数*/
        Random random = new Random();

        /*填充图像*/
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,width,height);
        /*指定字体大小，类型*/
        Font font = new Font("Fixedsys",Font.BOLD,fontHeight);
        /*设置字体大小，类型*/
        graphics.setFont(font);
        /**/
        graphics.drawRect(0,0,width-1,height-1);
        /*制作干扰线*/
        graphics.setColor(Color.BLACK);
        for (int i = 0;i<15;i++){
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(18);
            int y2 = random.nextInt(18);
            graphics.drawLine(x1,y1,x2,y2);
        }


        /*randCode 保存随机产生的验证码*/
        StringBuffer randCode = new StringBuffer();
        /*red,green,blue ------用于产生随机颜色  */
        int red = 0 , green = 0 , blue = 0 ;
        for (int i = 0;i<codeCount ; i++){
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            graphics.setColor(new Color(red,green,blue));
            /*将字符串写入图片对象*/
            graphics.drawString(code,(i+1)*xx,codeY);
            /*将验证组合一起保存在randCode中，方便存储，拿出使用*/
            randCode.append(code);
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",randCode.toString());
        map.put("codePic",buffImg);
        return map;
    }


}
