
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;



/*
 * 图片生成在D盘根目录下
 * */

public class BinCode {

     private static final int BLACK = 0xFF000000;
        private static final int WHITE = 0xFFFFFFFF;
        String name;
        String eduID;
       public BinCode(String name, String eduID ){
        	this.name=name;
        	this.eduID=eduID;
        	
        }
       public BinCode( ){
     
       }
        
        public void createCode() throws WriterException, IOException{
        	
             String path = "";
             
             String webSite="162.105.67.14:80/sign.jsp";
             String text = this.name+this.eduID + webSite ;
             int width = 300;
             int height = 300;
             // 二维码图片格式
             String format = "gif";
             // 设置编码，防止中文乱码
             Hashtable<EncodeHintType, Object> ht = new Hashtable<EncodeHintType, Object> ();
             ht.put (EncodeHintType.CHARACTER_SET, "UTF-8");
             // 设置二维码参数(编码内容，编码类型，图片宽度，图片高度, 编码格式)
             BitMatrix bitMatrix = new MultiFormatWriter ().encode (text, BarcodeFormat.QR_CODE, width, height, ht);
             // 生成二维码(定义二维码输出服务器路径)
             File outputFile = new File (path);
             if (!outputFile.exists ())
             {
                 //创建文件夹
                 outputFile.mkdir ();
             }
             int b_width = bitMatrix.getWidth ();
             int b_height = bitMatrix.getHeight ();
             // 建立图像缓冲器
             BufferedImage image = new BufferedImage (b_width, b_height, BufferedImage.TYPE_3BYTE_BGR);
             for ( int x = 0; x < b_width; x++ )
             {
                 for ( int y = 0; y < b_height; y++ )
                 {
                     image.setRGB (x, y, bitMatrix.get (x, y) ? BLACK : WHITE);
                 }
             }
             // 生成二维码
             File file=new File(path + "/qrcode.gif");
             if (file.exists()){
                 file.delete();
             }
             else{
             ImageIO.write (image, format, new File (path + "/qrcode." + format));
             }
        }
     
   
        /** 
         * 解析图像 
         */  
         
        public void testDecode() {  
            String filePath = "D://zxing.png";  
            BufferedImage image;  
            try {  
                image = ImageIO.read(new File(filePath));  
                LuminanceSource source = new BufferedImageLuminanceSource(image);  
                Binarizer binarizer = new HybridBinarizer(source);  
                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
                Map <DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
                Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
                JSONObject content = JSONObject.parseObject(result.getText());  
                System.out.println("图片中内容：  ");  
                System.out.println("author： " + content.getString("author"));  
                System.out.println("zxing：  " + content.getString("zxing"));  
                System.out.println("图片中格式：  ");  
                System.out.println("encode： " + result.getBarcodeFormat());  
            } catch (IOException e) {  
                e.printStackTrace();  
            } catch (NotFoundException e) {  
                e.printStackTrace();  
            }  
        }  
        
}
