package org.harker.robotics.vision;
import georegression.struct.point.Point2D_I32;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import boofcv.alg.feature.detect.edge.CannyEdge;
import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.Contour;
import boofcv.alg.shapes.ShapeFittingOps;
import boofcv.factory.feature.detect.edge.FactoryEdgeDetectors;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.feature.VisualizeShapes;
import boofcv.gui.image.ImagePanel;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.ConnectRule;
import boofcv.struct.PointIndex_I32;
import boofcv.struct.image.ImageFloat32;
import boofcv.struct.image.ImageUInt8;

/**
 * Processes a video feed and tracks points using KLT
 *
 * @author joelmanning
 */
public class WebCamMain {
//
//    //Weights taken from a simple logistic classification in WEKA
//    private static final double[] weights = { 26.76, 4.71, 0.0, 1.14 };
//    //Optimal width/height for the tape
//    private static final double WH_RATIO = 1.66666667;
//    //Optimal percent of the tape takes up of the smallest cube around it
//    private static final double PERCENT_AREA = 0.31428571428;
//    //Minimum sum of rgb values to be counted in a shape
//    public static final int MIN_L = -269/*550*/;
//    public static final int MIN_G = 249;
//    //A display for testing the program
//    static ListDisplayPanel gui = new ListDisplayPanel();
//    //Settings for polygon display
//    static double splitFraction = 0.05;
//    static double minimumSideFraction = 0.1;
//    //Minimum height and width to be considered as the tape
//    private static final int MIN_HEIGHT = 10;
//    private static final int MIN_WIDTH = 10;
//    //private static JTextField GSlide = new JTextField("1", 10/*0, 255*/);
//    //private static JSlider LSlide = new JSlider(-510, 255);
//    //private static JPanel sliders = new JPanel();
//    //static JPanel panel = new JPanel();
//    public static void main(String[] args) {
//        // Open a webcam at a resolution close to 640x480
//        Webcam webcam = UtilWebcamCapture.openDefault(640,480);
//        // specify the target's initial location and initialize with the first frame
//        //tracker.initialize(frame,location);
//        // Create the panel used to display the image and feature tracks
//        ImagePanel gui = new ImagePanel();
//        gui.setPreferredSize(webcam.getViewSize());
//        BufferedImage bi = new BufferedImage(I, height, imageType)
//        ShowImages.showWindow(gui,"KLT Tracker",true);
//        Point2D_I32[] past = null;
//        //int minimumTracks = 100;
//        while( true ) {
//            BufferedImage image = webcam.getImage();
//            //turns it into a black and white image where black are the light pixels and everything else is white
//            BufferedImage lImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
//            for(int x = 0; x < lImage.getWidth(); x++){
//                for(int y = 0; y < lImage.getHeight(); y++){
//                    int[] rgb = getRGBFromPixel(image.getRGB(x, y));
//                    int newColor;
//                    int l = rgb[1] - rgb[2] - rgb[0];
//                    if(l > L() && rgb[1] > G()){
//                        newColor = 0;
//                    } else {
//                        newColor = 255;
//                    }
//                    lImage.setRGB(x, y, toRGB(newColor));
//                }
//            }
//            ImageFloat32 lConverted = ConvertBufferedImage.convertFromSingle(lImage,
//                    null, ImageFloat32.class);
//            //adds the black and white image to the gui
//            BufferedImage displayImage = new BufferedImage(lConverted.width,
//                    lConverted.height, BufferedImage.TYPE_INT_RGB);
//            ImageUInt8 binary = new ImageUInt8(lConverted.width, lConverted.height);
//
//            // Finds edges inside the image
//            CannyEdge<ImageFloat32, ImageFloat32> canny = FactoryEdgeDetectors
//                    .canny(2, false, true, ImageFloat32.class, ImageFloat32.class);
//
//            canny.process(lConverted, 0.1f, 0.3f, binary);
//
//            List<Contour> contours = BinaryImageOps.contour(binary,
//                    ConnectRule.EIGHT, null);
//
//            Graphics2D g2 = lImage.createGraphics();
//            g2.setStroke(new BasicStroke(2));
//
//            //keeps track of the current set of corners and the error value associated with it
//            Point2D_I32[] currentCorners = null;
//            double currentError = Integer.MAX_VALUE;
//            //int[] currentIndices = null;
//            TreeMap<Double, Contour> tm = new TreeMap<Double, Contour>();
//            System.out.println("There were " + contours.size() + " contours");
//            for (Contour c : contours)
//            {
//                //System.out.println("Found shape with " + c.external.size() + " vertices");
//                int size = polygonArea(c.external);
//                Point2D_I32[] corners = fourCorners(c.external);
//                //System.out.println("Found a contour with corners: " + c.external.toString());
//                int cornerSize = polygonArea(corners);
//                int height = (corners[0].y + corners[1].y - corners[2].y - corners[3].y)/2;
//                int width = (corners[0].x + corners[3].x - corners[1].x - corners[2].x)/2;
//                if(height > MIN_HEIGHT && width > MIN_WIDTH && cornerSize != 0/* && size > minSize*/){
//                    double error = 0;
//                    //adds error if the ratio of width to height is different from expected
//                    error += Math.abs((double)1.0 - ((double)width/height)/WH_RATIO) * whWeight();
//                    //adds error if it is taking up a different percent of expected cube area
//                    error += Math.abs((double)1.0 - ((double)size/cornerSize)/PERCENT_AREA) * apWeight();
//                    //adds error if the right corners have different x values
//                    error += Math.abs((double)corners[0].x - corners[3].x)/width * sideVerticalWeight();
//                    //adds error if the left corners have different x values
//                    error += Math.abs((double)corners[1].x - corners[2].x)/width * sideVerticalWeight();
//                    //adds error if there is empty space inside the shape
//                    if(!c.internal.isEmpty()){
//                        error += 0.1 * noInternalWeight();
//                    }
//                    if(past != null){
//                        for(int i = 0; i < 4; i++){
//                            error += (Math.abs(past[i].x - corners[i].x) + Math.abs(past[i].y - corners[i].y)) * stayWeight();
//                        }
//                    }
//                    /*List<Point2D_I32> top = between(c.external, corners[0], corners[1]);
//                    List<Point2D_I32> bottom = between(c.external, corners[2], corners[3]);
//                    List<Point2D_I32> right = between(c.external, corners[0], corners[3]);
//                    List<Point2D_I32> left = between(c.external, , corners[2], 4);*/
//                    //if this has less error than the current least error set current corners to this
//                    if(error < currentError){
//                        currentError = error;
//                        currentCorners = corners;
//                    }
//                    System.out.println("I had an error of " + error);
//                    if(tm.put(new Double(error), c) != null){
//                        System.out.println("overwritten");
//                    }
//                }
//                //List<PointIndex_I32> vertexes = ShapeFittingOps.fitPolygon(
//                //        c.external, true, splitFraction, minimumSideFraction, 100);
//
//                //g2.setColor(new Color(rand.nextInt()));
//                //draws this shape
//                //VisualizeShapes.drawPolygon(vertexes, true, g2);
//            }
//            past = currentCorners;
//            List<Point2D_I32> l = new ArrayList<Point2D_I32>();
//            if(currentCorners != null){
//                for(int i = 0; i < 4; i++){
//                    l.add(currentCorners[i]);
//                }
//                System.out.println("Corners are: " + Arrays.toString(currentCorners));
//            } else {
//                System.out.println("Current corners was null");
//            }
//            Entry<Double, Contour> current = tm.firstEntry();
//            int g = 255;
//            int r = 0;       
//            while(true){
//                if(current == null){
//                    break;
//                }
//                current = tm.higherEntry(current.getKey());
//                if(current == null){
//                    break;
//                }
//                Color c = new Color(r, g, 0);
//                g2.setColor(c);
//                VisualizeShapes.drawPolygon(current.getValue().external, true, g2);
//                g = Math.max(0, g - 30);
//                r = Math.min(255, r + 30);
//            }
//            List<PointIndex_I32> vertexes = ShapeFittingOps.fitPolygon(
//                    l, true, splitFraction, minimumSideFraction, 100);
//            g2.setColor(Color.CYAN);
//            //draws the final conclusion of the program in cyan, then prints it out
//            VisualizeShapes.drawPolygon(vertexes, true, g2);
//            gui.setBufferedImageSafe(lImage);
//        }
//    }
//    private static Point2D_I32[] fourCorners(List<Point2D_I32> points){
//        Point2D_I32[] ps = new Point2D_I32[4];
//        for(Point2D_I32 p: points){
//            if(ps[0] == null || ps[0].x + ps[0].y < p.x + p.y){
//                ps[0] = p;
//            }
//            if(ps[1] == null || - ps[1].x + ps[1].y < - p.x + p.y){
//                ps[1] = p;
//            }
//            if(ps[2] == null || - ps[2].x - ps[2].y < - p.x - p.y){
//                ps[2] = p;
//            }
//            if(ps[3] == null || ps[3].x - ps[3].y < p.x - p.y){
//                ps[3] = p;
//            }
//        }
//        return ps;
//    }
//    /**
//     * returns the area of a polygon made up of points
//     */
//    private static int polygonArea(List<Point2D_I32> points) 
//    { 
//        int area = 0;         // Accumulates area in the loop
//        int j = points.size() - 1;  // The last vertex is the 'previous' one to the first
//
//        for (int i=0; i < points.size(); i++)
//        {
//            area = area +  (points.get(j).x + points.get(i).x) * (points.get(j).y - points.get(i).y); 
//            j = i;  //j is previous vertex to i
//        }
//        return area/2;
//    }
//    /**
//     * returns the area of a polygon made up of points
//     */
//    private static int polygonArea(Point2D_I32[] points) 
//    { 
//        int area = 0;         // Accumulates area in the loop
//        int j = points.length - 1;  // The last vertex is the 'previous' one to the first
//
//        for (int i=0; i < points.length; i++)
//        {
//            area = area +  (points[j].x + points[i].x) * (points[j].y - points[i].y); 
//            j = i;  //j is previous vertex to i
//        }
//        return area/2;
//    }
//    /**
//     * returns an array {r, g, b} from the integer value
//     */
//    public static int[] getRGBFromPixel(int pixel){
//        int b = pixel & 0xFF;
//        int g = (pixel >> 8) & 0xFF;
//        int r = (pixel >> 16) & 0xFF;
//        return new int[]{r, g, b};
//    }
//    /**
//     * gives an rgb value with all of r, g, and b as l
//     */
//    public static int toRGB(int l){
//        int n = l;
//        if(n >= 256){
//            n = 255;
//        }
//        return n + (n << 8) + (n << 16);
//    }
//    /**
//     * takes in a list and returns a list of all points between first and last
//     */
//    public static List<Point2D_I32> between(List<Point2D_I32> list, Point2D_I32 first, Point2D_I32 last){
//        List<Point2D_I32> b = new ArrayList<Point2D_I32>();
//        int fi = list.indexOf(first);
//        int li = list.indexOf(last);
//        if(fi == -1 || li == -1){
//            return null;
//        }
//        for(int i = fi + 1; i != li; i++){
//            if(i == list.size()){
//                i = 0;
//            }
//            b.add(list.get(i));
//        }
//        return b;
//    }
//    private static int G(){
//        return MIN_G;
//    }
//    private static int L(){
//        return MIN_L;
//    }
//    private static double whWeight(){
//        return weights[0];
//    }
//    private static double apWeight(){
//        return weights[1];
//    }
//    private static double sideVerticalWeight(){
//        return weights[2];
//    }
//    private static double noInternalWeight(){
//        return weights[3];
//    }
//    private static int stayWeight(){
//        return 0;
//    }
}