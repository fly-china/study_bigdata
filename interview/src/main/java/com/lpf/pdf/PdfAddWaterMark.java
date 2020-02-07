package com.lpf.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给曹老师的PDF加水印
 *
 * @author lipengfei
 * @create 2020-02-07 16:14
 **/
public class PdfAddWaterMark {
    public static String TARGET_PATH = "C:\\Users\\fei\\Desktop\\target.pdf";
    public static String ORI_PATH = "C:\\Users\\fei\\Desktop\\生物类第一篇.pdf";
    public static String IMG_PATH = "C:\\Users\\fei\\Desktop\\222.png";

    public static void main(String[] args) throws Exception {
        setWatermark(ORI_PATH, TARGET_PATH);
    }

    /**
     * @throws IOException
     */
    public static void setWatermark(String inputPath, String outputPath)
            throws IOException, DocumentException {

        Image image = Image.getInstance(IMG_PATH);
        PdfReader reader = new PdfReader(inputPath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outputPath)));
        PdfStamper stamper = new PdfStamper(reader, bos);

        // 获取总页数 +1, 下面从1开始遍历
        int total = reader.getNumberOfPages();

        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.5f);

        PdfContentByte content;
        for (int i = 1; i < total + 1; i++) {
            content = stamper.getOverContent(i);// 在内容上方加水印
//            content = stamper.getUnderContent(i);//在内容下方加水印
            content.setGState(gs);
            // 开始设置水印
//            content.beginText();
            try {
                // 设置坐标 绝对位置 X Y
                image.setAbsolutePosition(0, 0);
//                // 设置旋转弧度
//                image.setRotation(30);// 旋转 弧度
//                // 设置旋转角度
//                image.setRotationDegrees(45);// 旋转 角度
//                // 设置等比缩放
//                image.scalePercent(90);// 依照比例缩放
                // image.scaleAbsolute(200,100);//自定义大小
                // 设置透明度
                content.setGState(gs);
                // 添加水印图片
                content.addImage(image);
//                //结束设置
//                content.endText();
//                content.stroke();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stamper.close();

    }
}
