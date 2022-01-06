package frc.robot.SubSystems;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.*;
import org.opencv.core.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import frc.robot.Util.Monitor;
public class Camera {
public static JFrame frame;
public static JLabel label;
public static int hMin = 160;
public Mat cam_frame;
public static int hMax = 170;
public static int sMin = 10;
public static int sMax = 100;
public static int vMin = 70;
public static int vMax = 100;
public static ArrayList xVals;
public static ArrayList yVals;
public static boolean[][] mat;
public static int width;
public static int height;
public static int frames;
public static boolean isNull;
public static Timer timer;
public static int spiderScale = 10;
public static int testScale = 40;
	public UsbCamera USBcamera;
    private CvSink cam_sink;
    public BufferedImage image;
    private CvSource filtered;
    public Monitor monitor;

public Camera () {
    monitor = new Monitor(Thread.currentThread(), 100);
    Thread thread = new Thread(monitor, "MonitorThread");
    thread.setDaemon(true);
    thread.start();

timer = new Timer();
RemindTask task = RemindTask.getInstance();
    Thread timer = new Thread(new timerThread(), "Thread - timer");
    timer.start();
    USBcamera = CameraServer.getInstance().startAutomaticCapture(Constants.cameraPort);
			//send to smartdash board
			if(USBcamera != null){
				USBcamera.setResolution(Constants.cWidth, Constants.cHeight);
				USBcamera.setFPS(30);
                cam_sink = CameraServer.getInstance().getVideo();
                filtered = CameraServer.getInstance().putVideo("filtered", 1, 1);


            }
    xVals = new ArrayList();
    yVals = new ArrayList<String>();
        /*System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String filePath = "C:\\Users\\bpsuser\\Desktop\\Project\\Good.mp4";
        if (!Paths.get(filePath).toFile().exists()){
             System.out.println("File " + filePath + " does not exist!");
             return;
        }

        VideoCapture camera = new VideoCapture(filePath);

        if (!camera.isOpened()) {
            System.out.println("Error! Camera can't be opened!");
            return;
        }*/
        cam_frame = new Mat();
       
       
}
        public void update() throws IOException {
            monitor.reset();
            long result = cam_sink.grabFrameNoTimeout(cam_frame);

            
            
            //BufferedImage bufferedImage = matToBufferedImage(cam_frame);
           // showWindow(bufferedImage);
            //checkPix(cam_frame);     
            filtered.putFrame(drawBox(box(erode(mat)), drawCenter(checkPix(cam_frame), getCenter()[0], getCenter()[1])));
            //updateImg(drawBox(box(erode(mat)), drawFromArray(mat)));
        }
       
    
    public static Mat drawCenter(Mat img, int x, int y) {
    for(int o = 0; o < img.height(); o++) {
    img.put(x, o, 0,255,0);
    }
    if(y<img.height()) {
    for(int n = 0; n < img.width(); n++) {    
    img.put(n, y, 0,255,0);
    }
    }
    return img;
    }
    public static double[] RGBtoHSB(double r, double g, double b){

        double h, s, v;

        double min, max, delta;

        min = Math.min(Math.min(r, g), b);
        max = Math.max(Math.max(r, g), b);

        // V
        v = (double) Color.RGBtoHSB((int)r,(int) g,(int) b, null)[2];

         delta = max - min;

        // S
         if( max != 0 )
            s = delta / max;
         else {
            s = 0;
            h = -1;
            return new double[]{h,s,v};
         }

        // H
         if( r == max )
            h = ( g - b ) / delta; // between yellow & magenta
         else if( g == max )
            h = 2 + ( b - r ) / delta; // between cyan & yellow
         else
            h = 4 + ( r - g ) / delta; // between magenta & cyan

         h *= 60;    // degrees

        if( h < 0 )
            h += 360;

        return new double[]{h,s,v};
    }
    public static Mat BufferedImage2Mat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
    }

    private static BufferedImage matToBufferedImage(Mat frame) {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    public static void showWindow(BufferedImage img) {
        frame = new JFrame();
        label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured");
        frame.setVisible(true);
    }
    private static boolean checkHSV(int r, int g, int b) {
    double[] hsb = {0,0,0};
    hsb = RGBtoHSB(r,g,b);
    if(hMin <= hsb[0] && hsb[0] <= hMax) {
    if(sMin <= hsb[1]*100 && sMax >= hsb[1]*100) {
    if(vMin <= hsb[2]*100 && hsb[2]*100<= vMax) {
    return true;
    }else {
    return false;
    }
    }else {
    return false;
    }
    }else {
    return false;
    }
    }
    private static Mat checkPix(Mat img) {
    xVals.clear();
    yVals.clear();
    int r = 0;
    int g = 0;
    int b = 0;
    boolean loop = false;
    boolean loop2 = false;
    boolean loop3 = false;
    boolean loop4 = false;
    height = img.height();
    width = img.width();
    mat = new boolean[width][height];
    for(int y = 0; y < height; y = y+ 1 * testScale) {
    for(int x = 0; x < width; x=x + 1 * testScale) {
              //Retrieving contents of a pixel
                double[] pixel = {0,0,0};
                pixel = img.get(x,y);
                //Creating a Color object from pixel value
                //Retrieving the R G B values
                
                try{
                r = (int) pixel[0];
                g = (int) pixel[1];
                b = (int) pixel[2];
                }
                catch(Exception e){

                    break;
                }

                if(checkHSV(r,g,b)) {
                img.put(x, y, 255,255,255);
                loop2 = true;
                loop = true;
                int y1 = y;
                while(loop2){
                int x1 = x;
            y1 = y1 - 1* spiderScale;
            if(x1<width && x1>0 && y1 < height && y1 >0) {
                    pixel = img.get(x,y1);
                    //Retrieving the R G B values
                    r = (int) pixel[0];
                    g = (int) pixel[1];
                    b = (int) pixel[2];
            if(checkHSV(r,g,b)) {
                    img.put(x, y1, 255,255,255);
                    dataMan(x,y1);
                    loop = true;
                    while(loop) {
                    x1 = x1 + 1*spiderScale;
                    if(x1<width && x1>0 && y1 < height && y1 >0) {
                            pixel = img.get(x1,y1);
                            //Retrieving the R G B values
                            r = (int) pixel[0];
                            g = (int) pixel[1];
                            b = (int) pixel[2];
                    if(checkHSV(r,g,b)) {
                            img.put(x1, y1, 255,255,255);
                            dataMan(x1,y1);
                    }else {
                    loop = false;
                    }
                    }else {
                    break;
                    }
                    loop4 = true;
                    int x2 = x;
                        while(loop4) {
                        x2 = x2 - 1*spiderScale;
                        if(x2<width && x2>0 && y1 < height && y1 >0) {
                                pixel = img.get(x2,y1);
                                //Retrieving the R G B values
                                r = (int) pixel[0];
                                g = (int) pixel[1];
                                b = (int) pixel[2];
                        if(checkHSV(r,g,b)) {
                                img.put(x2, y1, 255,255,255);
                                dataMan(x2,y1);

                        }else {
                        loop4 = false;
                        }
                        }else {
                        break;
                        }
                        }
                    }
            }

            else {
            loop2 = false;
            }
            }else {
            break;
            }
           
               
                }
                int y2 = y;
                while(loop3){
                   
                    int x2 = x;
                y2 = y2 - 1*spiderScale;
                if(x2<width && x2>0 && y2 < height && y2 >0) {
                        pixel = img.get(x,y2);
                        //Retrieving the R G B values
                        r = (int) pixel[0];
                        g = (int) pixel[1];
                        b = (int) pixel[2];
                if(checkHSV(r,g,b)) {
                        dataMan(x,y2);
                }

                else {
                loop3 = false;
                }
                }else {
                break;
                }
               
                   
                    }
           
               
           
               
               
               
                }else {
                if(r!=255 && g!=255 && b!=255) {
                img.put(x, y, 0,0,0);
                }
                }
    }
    }
    return img;
   
    }
    private static void updateImg(BufferedImage img) {
        frame.getContentPane().remove(label);
        label = new JLabel(new ImageIcon(img));
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured");
        frame.setVisible(true);
    }
    private static int[] getCenter() {
    int x = 0;
    for(int k = 0; k < xVals.size(); k++) {
    x= x + (int) xVals.get(k);
    }
    if(xVals.size() != 0) {
    x = x/xVals.size();
    }
    int y = 0;
    for(int k = 0; k < yVals.size(); k++) {
    y= y + (int) yVals.get(k);
    }
    if(yVals.size() !=0) {
    y = y/yVals.size();
    }
int[] bruhmoment = {x, y};
    return bruhmoment;
    }
    private static void dataMan(int x, int y) {
    xVals.add(x);
    yVals.add(y);
    mat[x][y] = true;
    }
    private static boolean[][] erode(boolean[][] img) {
   
    for(int o =0; o < width; o++) {
    int erosion = 0;
        for(int k =0; k < height; k++) {
        if(img[o][k] == true) {
        erosion++;
        img[o][k] = false;
        }
        if(erosion == 2) {
        break;
        }
       
        }
    }
    return img;

    }
private static int[] box(boolean[][] img) {
int l = 0;
int r = 0;
int u = 0;
int d = 0;
for(int o = 0; o < width; o++) {
for(int k = 0; k < height; k++) {
if(img[o][k]) {
l = o;
}
}
}
for(int o = width-1; o > 0; o--) {
for(int k = 0; k < height; k++) {
if(img[o][k]) {
r = o;
}
}
}
for(int o = height-1; o > 0; o--) {
for(int k = width-1; k > 0; k--) {
if(img[k][o]) {
d = o;
}
}
}
for(int o = 0; o < height; o++) {
for(int k = 0; k < width; k++) {
if(img[k][o]) {
u = o;
}
}
}
int [] box = {l,r,u,d};
return box;

}
private static Mat drawBox(int [] box, Mat img) {
for(int i = box[1]; i < box[0]; i++) {
img.put(i, box[2], 255,0,0);
img.put(i, box[3], 255,0,0);
}
for(int i = box[3]; i < box[2]; i++) {
img.put(box[0], i,255,0,0);
img.put(box[1], i, 255,0,0);
}
return img;
}
private static BufferedImage drawFromArray(boolean[][] mat) {
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
for(int x = 0; x < width; x++) {
for(int y = 0; y < height; y++) {
if(mat[x][y]==true) {
image.setRGB(x,y, new Color(255,255,255).getRGB());
}else {
image.setRGB(x,y, new Color(0,0,0).getRGB());

}
}
}
return image;

}

   

   
private static class timerThread implements Runnable{
public void run() {
RemindTask task = RemindTask.getInstance();
timer.schedule(task, 0, 1000);
}

}

}
class RemindTask extends TimerTask {
static RemindTask task;
int frames = 0;
static RemindTask getInstance(){
if(task == null) {
return task = new RemindTask();
}else {
return task;
}
}
RemindTask(){
}
void framesInc(){
frames++;
}
    public void run() {
        //System.out.println(frames + " FPS");
        frames = 0;
    }
}
