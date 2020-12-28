
package de.ft.ledwall.camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Vector;

public class ImageProcess {
    public BufferedImage input;
    public BufferedImage output;
    public BufferedImage zbuff;
    public BufferedImage savebild;
    public BufferedImage forground;
    public BufferedImage ehemaligerinput=new BufferedImage(512,512 , BufferedImage.TYPE_INT_RGB);
    static int maxx;
    static int maxy;
    static int max;
    double[] xTable;
    double[] yTable;


    URL url;
    public void readImage(BufferedImage _bi){
        input=null;
        input=_bi;
        output=null;
        output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        xTable = new double[input.getWidth()];
        yTable = new double[input.getHeight()];
    }

    public BufferedImage getBufferedImagefromURL(String imageUrl){
        BufferedImage b=new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        try {
            URL url = new URL(imageUrl);
            b = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("mist");
        }

        return b;
    }

    public void readImagefromURL(String imageUrl,String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);


        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();

    }

    public void readImagefromURL(String imageUrl){
        try {
            url = new URL(imageUrl);
            input = ImageIO.read(url);
           // ehemaligerinput=input;
            //ehemaligerinput.setData(input.getRaster());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("mist");
        }

        //xTable = new double[input.getWidth()];
      //  yTable = new double[input.getHeight()];
    }



    public void readImage(String s)
    {
        input=null;
        try{
            input= ImageIO.read(new File(s));
        }catch(IOException e){
            e.printStackTrace();
        }

        output=null;
        output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        xTable = new double[input.getWidth()];
        yTable = new double[input.getHeight()];
    }



    public void negativ(int a, int b){
        for(int x=0;x < input.getWidth();x++){
            for(int y= 0; y < input.getHeight();y++){
                int rgb=a*input.getRGB(x,y)+b;

                input.setRGB(x,y,rgb);
            }
        }
    }


    public int getRed(int x, int y){
        return (input.getRGB(x, y) >> 16) & 0xFF;
    }

    public int getGreen(int x, int y){
        return (input.getRGB(x, y) >> 8) & 0xFF;
    }

    public int getBlue(int x, int y){
        return input.getRGB(x, y) & 0xFF;
    }



    public int readpixel(int x, int y){
        return input.getRGB(x,y);
    }

    public void pixel(int x, int y,int rgb){



        input.setRGB(x,y,rgb);
    }

    public void setpixelcolor(int x, int y,int r,int g,int b){

        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;

        input.setRGB(x,y,rgb);
    }



    public void dilatation(){
        BufferedImage o = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int y=1;y<input.getHeight()-50;y=y+1){
            for(int x=1;x<input.getWidth()-50;x=x+1) {


                if(getRed(x,y)!=0 || getRed(x, y+1) != 0 || getRed(x, y-1) != 0 || getRed(x+1, y) != 0 || getRed(x-1, y) != 0 ||              getRed(x+1, y-1) != 0 || getRed(x-1, y+1) != 0  || getRed(x+1, y+1) != 0  || getRed(x-1, y-1) != 0) {

                    o.setRGB(x, y, 16711680);

                }

                if(getGreen(x,y)!=0 || getGreen(x, y+1) != 0 || getGreen(x, y-1) != 0 || getGreen(x+1, y) != 0 || getGreen(x-1, y) != 0 ||              getGreen(x+1, y-1) != 0 || getGreen(x-1, y+1) != 0  || getGreen(x+1, y+1) != 0  || getGreen(x-1, y-1) != 0) {

                    o.setRGB(x, y, 65280);

                }


            }
        }//




        input=o;


    }




    public void drawRect(int x,int y,int w,int h){
        for(int xx=0;xx<w;xx++){
            for(int yy=0;yy<h;yy++){
                if(xx+x>=0 && xx+x<input.getWidth() &&  yy+y>=0 && yy+y<input.getHeight()) {
                    this.setpixelcolor(xx + x, yy + y, 0, 255, 0);
                }
            }
        }
    }

    public void linie(){
        BufferedImage o = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int y=1;y<input.getHeight()-50;y=y+1){
            for(int x=1;x<input.getWidth()-50;x=x+1) {

                if (getRed(x, y) == 0) {
                    if(getRed(x,y)!=0 || getRed(x, y+1) != 0 || getRed(x, y-1) != 0 || getRed(x+1, y) != 0 || getRed(x-1, y) != 0 ||              getRed(x+1, y-1) != 0 || getRed(x-1, y+1) != 0  || getRed(x+1, y+1) != 0  || getRed(x-1, y-1) != 0) {

                        o.setRGB(x, y, 16711680);

                    }
                }

                if (getGreen(x, y) == 0) {
                    if(getGreen(x,y)!=0 || getGreen(x, y+1) != 0 || getGreen(x, y-1) != 0 || getGreen(x+1, y) != 0 || getRed(x-1, y) != 0 ||              getGreen(x+1, y-1) != 0 || getGreen(x-1, y+1) != 0  || getGreen(x+1, y+1) != 0  || getGreen(x-1, y-1) != 0) {

                        o.setRGB(x, y, 65280);

                    }
                }


            }
        }//





        input=o;


    }



    public void erosion(){

        BufferedImage o = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int y=1;y<input.getHeight()-1;y=y+1){
            for(int x=1;x<input.getWidth()-1;x=x+1) {

                if (getRed(x, y) != 0) {
                    if(getRed(x, y+1) != 0 && getRed(x, y-1) != 0 && getRed(x+1, y) != 0 && getRed(x-1, y) != 0 &&              getRed(x+1, y-1) != 0 && getRed(x-1, y+1) != 0  && getRed(x+1, y+1) != 0  && getRed(x-1, y-1) != 0) {
                        o.setRGB(x, y, 16711680);


                    }

                }
                if (getGreen(x, y) != 0) {
                    if(getGreen(x, y+1) != 0 && getGreen(x, y-1) != 0 && getGreen(x+1, y) != 0 && getGreen(x-1, y) != 0 &&              getGreen(x+1, y-1) != 0 && getGreen(x-1, y+1) != 0  && getGreen(x+1, y+1) != 0  && getGreen(x-1, y-1) != 0) {
                        o.setRGB(x, y, 65280);


                    }}

                    if (getBlue(x, y) != 0) {
                        if(getBlue(x, y+1) != 0 && getBlue(x, y-1) != 0 && getBlue(x+1, y) != 0 && getBlue(x-1, y) != 0 &&              getBlue(x+1, y-1) != 0 && getBlue(x-1, y+1) != 0  && getBlue(x+1, y+1) != 0  && getBlue(x-1, y-1) != 0) {
                            o.setRGB(x, y, 255);


                        }}


            }
        }//
input=o;

    }

    public void luminanz()
    {
        for(int x=0;x<input.getWidth();x++){
            for(int y=0;y<input.getHeight();y++){
                int r=getRed(x,y);
                int g=getGreen(x,y);
                int b=getBlue(x,y);


                int luminanz=(int) (0.229*r+0.587*g+0.114*b);
                setpixelcolor(x,y,luminanz,luminanz,luminanz);
            }
        }
    }

    public void specialluminanz(float rm,float gm,float bm)
    {
        for(int x=0;x<input.getWidth();x++){
            for(int y=0;y<input.getHeight();y++){
                int r=getRed(x,y);
                int g=getGreen(x,y);
                int b=getBlue(x,y);


                int luminanz=(int) (rm*r+gm*g+bm*b);
                setpixelcolor(x,y,luminanz,luminanz,luminanz);
            }
        }
    }

    public void luminanz(int y1,int y2)
    {
        for(int x=0;x<input.getWidth();x++){
            for(int y=input.getHeight()-y2;y<input.getHeight()-y1;y++){
                int r=getRed(x,y);
                int g=getGreen(x,y);
                int b=getBlue(x,y);


                int luminanz=(int) (0.229*r+0.587*g+0.114*b);
                setpixelcolor(x,y,luminanz,luminanz,luminanz);
            }
        }
    }
    public int luminanzof(int x,int y){
        return (int)(0.229*getRed(x,y)+0.587*getGreen(x,y)+0.114*getBlue(x,y));
    }

    public int beschneiden(int alt){
        if(alt>255)
            return 255;
        else if(alt <0)
            return 0;
        else
            return alt;
    }

    public void kanten(int kontrast){
        BufferedImage b = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] filter1 ={
                {-1,0,1},
                {-2,0,2},
                {-1,0,1},
        };

        int[][] filter2 ={
                {1,2,1},
                {0,0,0},
                {-1,-2,-1},
        };
        int grayx=0;
        int grayy=0;
        int gray=0;
        int rgb=0;
        for(int x=1;x<input.getWidth()-1;x++){
            for(int y=1;y<input.getHeight()-1;y++){
                grayx=0;
                grayy=0;
                for(int i=-1;i<=1;i++){
                    for(int j=-1;j<=1;j++){
                        grayx+= Math.round(getRed(x+i,y+j) * filter1[1+i][1+j]);
                        grayy+= Math.round(getRed(x+i,y+j) * filter2[1+i][1+j]);
                    }
                }
                gray = beschneiden((int) Math.sqrt(grayx*grayx  +  grayy*grayy));

                rgb = gray;
                rgb = (rgb << 8) + gray;
                rgb = (rgb << 8) + gray;
                b.setRGB(x, y, rgb);
                if(kontrast!=-1) {
                    if (gray < kontrast) {
                        b.setRGB(x, y, 0);
                    } else {
                        b.setRGB(x, y, -1);
                    }
                }
            }
        }

        input=b;
    }



    public void kantenspezial(int kontrast){
        BufferedImage b = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] filter1 ={

                {-1	,   -1,     -1, 	-1,	-1},
                {-1	,   0,  	0,	    0,	-1,},
                {-1,	0,	    16,	    0,	-1,},
                {-1,   	0,	    0	,   0,	-1,},
                {-1,	-1	,   -1,	    -1,	-1},

        };



        for(int x=5;x<input.getWidth()-5;x++){
            for(int y=5;y<input.getHeight()-5;y++){
                int grayx=0;
                int grayy=0;
                for(int i=-2;i<=2;i++){
                    for(int j=-2;j<=2;j++){
                        grayx+=(int) Math.round(getRed(x+i,y+j) * filter1[2+i][2+j]);
                    }
                    int gray = beschneiden((int)Math.pow(grayx,2)/9);

                    int rgb = gray;
                    rgb = (rgb << 8) + gray;
                    rgb = (rgb << 8) + gray;
                    b.setRGB(x, y, rgb);
                    if(kontrast!=-1) {
                        if (gray < kontrast) {
                            b.setRGB(x, y, 0);
                        } else {
                            b.setRGB(x, y, -1);
                        }
                    }

                }
            }
        }

        input=b;
    }



    public void kanten(int kontrast,int y1,int y2){
        BufferedImage b = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[][] filter1 ={
                {-1,0,1},
                {-2,0,2},
                {-1,0,1},
        };
        int[][] filter2 ={
                {1,2,1},
                {0,0,0},
                {-1,-2,-1},
        };

        for(int x=1;x<input.getWidth()-1;x++){
            for(int y=input.getHeight()-y2;y<input.getHeight()-y1;y++){
                int grayx=0;
                int grayy=0;
                for(int i=-1;i<2;i++){
                    for(int j=-1;j<2;j++){
                        grayx+=(int) Math.round(getRed(x+i,y+j) * filter1[1+i][1+j]);
                        grayy+=(int) Math.round(getRed(x+i,y+j) * filter2[1+i][1+j]);
                    }
                    int gray = beschneiden((int) Math.sqrt(grayx*grayx  +  grayy*grayy));

                    int rgb = gray;
                    rgb = (rgb << 8) + gray;
                    rgb = (rgb << 8) + gray;
                    b.setRGB(x, y, rgb);
                    if(kontrast!=-1) {
                        if (gray < kontrast) {
                            b.setRGB(x, y, 0);
                        } else {
                            b.setRGB(x, y, -1);
                        }
                    }

                }
            }
        }

        input=b;
    }


    public BufferedImage gaveback(){
        return input;
    }










    public void houghTransform(int thetaAxisSize, int rAxisSize)
    {

        zbuff=null;
         zbuff = new BufferedImage(thetaAxisSize, rAxisSize, BufferedImage.TYPE_INT_RGB);

        int width = input.getWidth();
        int height = input.getHeight();
        int maxRadius = (int)Math.ceil(Math.hypot(width, height));
        int halfRAxisSize = rAxisSize >>> 1;


        for (int y = height - 1; y >= 0; y--)
        {
            for (int x = width - 1; x >= 0; x--)
            {
                if (getRed(x,y)>100)
                {
                    for (int theta = thetaAxisSize - 1; theta >= 0; theta--)
                    {
                        double r = Math.cos(theta*Math.PI/thetaAxisSize) * x + Math.sin(theta*Math.PI/thetaAxisSize) * y;
                        int rScaled = (int)Math.round(r * halfRAxisSize / maxRadius) + halfRAxisSize;
                        xTable[theta]=x;
                        yTable[rScaled]=y;
                        accumulate(theta, rScaled, 1);
                    }
                }
            }
        }




        input=zbuff;

    }


    public void drawgerade(int x,int y,int winkel,int r,int g,int b){
        int alpha=winkel;
        int cx;
        int cy;

        for(int i=-1000;i<1000;i++) {
            cx=(int) (Math.cos((alpha)*Math.PI/180)*i)+x;
            cy=(int) (Math.sin((alpha)*Math.PI/180)*i)+y;
            if(cx>0&&cx<input.getWidth()  &&  cy>0&&cy<input.getHeight())
            setpixelcolor(cx, cy, r, g, b);
        }


                    //setpixelcolor(20, y, 0, 255, 255);

    }



    public void bright(){
        maxx=0;
        maxy=0;
        max=0;
        int c=0;
        c=0;
        for(int x=0;x<input.getWidth();x=x+1){
            for(int y=0;y<input.getHeight();y=y+1){
                if(getRed(x,y)+getGreen(x,y)+getBlue(x,y)>max){
                    max=getRed(x,y)+getGreen(x,y)+getBlue(x,y);
                    maxx=x;
                    maxy=y;
                    c=c+1;

                }
            }
        }


        for(int a=0;a<8;a++) {
            for (int b = 0; b < 8; b++) {
                if(maxx+a-a/2>0&&maxx+a-a/2<input.getWidth()  &&  maxy+b-b/2>0&&maxy+b-b/2<input.getHeight())
                setpixelcolor(maxx+a-a/2, maxy+b-b/2, 0, 100, 0);

            }
        }




    }

    public int gethmaxx(){
        //bright();
        return maxx;
    }
    public int gethmaxy(){
        //bright();
        return maxy;
    }
    public int getmax(){
        //bright();
        return max;
    }
    public double getgeradeangle(){
       // bright();
        double a=input.getWidth();
        double b=maxx;
        double c=180f;
        double angle=180f/(a/b);//180f/(input.getWidth()/maxx)
        return angle;
    }

    public int getgeradex(){
        return (int)xTable[gethmaxx()];
    }
    public int getgeradey(){
        return (int)yTable[gethmaxy()];
    }


    private void accumulate(int x, int y, int delta)
    {

        int rgb = zbuff.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        rgb = red + delta;
        rgb = (rgb << 8) + green + delta;
        rgb = (rgb << 8) + blue + delta;
        zbuff.setRGB(x, y, rgb);


    }



    public int findline(int y){
        int ab=1;
        int d=0;
        for(int x=0;x<=input.getWidth()-1;x++) {
            if (getRed(x,input.getHeight()-1-y)>250 || getGreen(x,input.getHeight()-1-y)>250 || getBlue(x,input.getHeight()-1-y)>100) {
                ab = ab + 1;
            }
        }
        if(ab==1){
            return -1;
        }

        int[] a = new int[ab];
        ab=0;
        for(int x=0;x<=input.getWidth()-1;x++) {
            if(getRed(x,input.getHeight()-1-y)>250 || getGreen(x,input.getHeight()-1-y)>250 || getBlue(x,input.getHeight()-1-y)>255){
                a[ab]=x;
                ab++;
            }

        }
        for(int b=0;b<ab;b++){
            d=d+a[b];
        }
        if(ab<=0){

        }else {
            d = d / ab;
        }
        setpixelcolor(d,input.getHeight()-1-y,255,255,255);
        return d;
    }



public void rgbfinden(int ungenauung,int fenster_y1,int fenster_y2,int farbkontrollerot,int farbkontrollegruen,int farbkontrolleblau){
        int abstand=ungenauung;
         BufferedImage image = new BufferedImage(input.getWidth()/abstand, input.getHeight()/abstand, BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=0;x<input.getWidth()-abstand;x=x+abstand) {
            for (int y = input.getHeight()-fenster_y2; y < input.getHeight()-fenster_y1; y = y + abstand) {
                rd=0;
                gd=0;
                bd=0;

                for(int xx=0;xx<abstand;xx=xx+1){
                    for(int yy=0;yy<abstand;yy=yy+1){
                        rd+=getRed(xx+x,yy+y);
                        gd+=getGreen(xx+x,yy+y);
                        bd+=getBlue(xx+x,yy+y);
                    }
                }
                rd=rd/abstand/abstand;
                gd=gd/abstand/abstand;
                bd=bd/abstand/abstand;



                if(rd > farbkontrollerot+gd&& rd>farbkontrollerot+bd ){
                    //BB.setpixelcolor(x/abstand, y/abstand, 255,0,0);
                    image.setRGB(x/abstand,y/abstand,16711680);
                }else
                    if(gd > farbkontrollegruen+rd&& gd>farbkontrollegruen+bd ){
                        image.setRGB(x/abstand,y/abstand,65280);
                    }else
                    if(bd > farbkontrolleblau+gd&& bd>farbkontrolleblau+rd ){
                        image.setRGB(x/abstand,y/abstand,255);
                    }else {
                    int rgb = rd;
                    rgb = (rgb << 8) + gd;
                    rgb = (rgb << 8) + bd;
                    image.setRGB(x/abstand,y/abstand,rgb);
                }


            }
    }

    input=null;
    input=image;

}

    public void rgbfinden(int fenster_x, int fenster_y,int width,int height,int farbkontrollerot,int farbkontrollegruen,int farbkontrolleblau){
        BufferedImage image = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=fenster_x;x<fenster_x+width;x=x+1) {
            for (int y = fenster_y; y < fenster_y+height; y = y + 1) {



                        rd=getRed(x,y);
                        gd=getGreen(x,y);
                        bd=getBlue(x,y);





                if(rd > farbkontrollerot+gd&& rd>farbkontrollerot+bd ){
                    //BB.setpixelcolor(x/abstand, y/abstand, 255,0,0);
                        image.setRGB(x, y, 16711680);
                }else
                if(gd > farbkontrollegruen+rd&& gd>farbkontrollegruen+bd ){
                    image.setRGB(x,y,65280);
                }else
                if(bd > farbkontrolleblau+gd&& bd>farbkontrolleblau+rd ){
                    image.setRGB(x,y,255);
                }else {
                    int rgb = rd;
                    rgb = (rgb << 8) + gd;
                    rgb = (rgb << 8) + bd;
                    image.setRGB(x,y,rgb);
                }
               /*lu if( rd<100) {
                    image.setRGB(x, y, 16711680);
                }*/


                }
        }

        input=null;
        input=image;

    }




    public void Colorfinden(int ungenauung,int fenster_y1,int fenster_y2,int rf,int gf,int bf,int farbabweichung){
        int abstand=ungenauung;
        BufferedImage image = new BufferedImage(input.getWidth()/abstand, input.getHeight()/abstand, BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=0;x<input.getWidth()-abstand;x=x+abstand) {
            for (int y = input.getHeight()-fenster_y2; y < input.getHeight()-fenster_y1; y = y + abstand) {
                rd=0;
                gd=0;
                bd=0;

                for(int xx=0;xx<abstand;xx=xx+1){
                    for(int yy=0;yy<abstand;yy=yy+1){
                        rd+=getRed(xx+x,yy+y);
                        gd+=getGreen(xx+x,yy+y);
                        bd+=getBlue(xx+x,yy+y);
                    }
                }
                rd=rd/abstand/abstand;
                gd=gd/abstand/abstand;
                bd=bd/abstand/abstand;



                if((rd-gd)<(rf-gf)+farbabweichung && (rd-gd)>(rf-gf)-farbabweichung      &&     (rd-bd)<(rf-bf)+farbabweichung && (rd-bd)>(rf-bf)-farbabweichung){
                    //BB.setpixelcolor(x/abstand, y/abstand, 255,0,0);
                    image.setRGB(x/abstand,y/abstand,16711680);
                }else {
                    int rgb = rd;
                    rgb = (rgb << 8) + gd;
                    rgb = (rgb << 8) + bd;
                    image.setRGB(x/abstand,y/abstand,rgb);
                }


            }
        }

        input=null;
        input=image;

    }

    public void ungenauung(int ungenauung){
        int abstand=ungenauung;
        BufferedImage image = new BufferedImage(input.getWidth()/abstand, input.getHeight()/abstand, BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=0;x<input.getWidth()-abstand;x=x+abstand) {
            for (int y = 0; y < input.getHeight()-abstand; y = y + abstand) {
                rd=0;
                gd=0;
                bd=0;

                for(int xx=0;xx<abstand;xx=xx+1){
                    for(int yy=0;yy<abstand;yy=yy+1){
                        rd+=getRed(xx+x,yy+y);
                        gd+=getGreen(xx+x,yy+y);
                        bd+=getBlue(xx+x,yy+y);
                    }
                }
                rd=rd/abstand/abstand;
                gd=gd/abstand/abstand;
                bd=bd/abstand/abstand;

                int rgb = rd;
                rgb = (rgb << 8) + gd;
                rgb = (rgb << 8) + bd;
                image.setRGB(x/abstand,y/abstand,rgb);


    }

        }

        input=null;
        input=image;

    }








    public void setausgangsbild(int ungenauung){
        int abstand=ungenauung;
        BufferedImage image = new BufferedImage(input.getWidth()/abstand, input.getHeight()/abstand, BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=0;x<input.getWidth()-abstand;x=x+abstand) {
            for (int y = 0; y < input.getHeight()-abstand; y = y + abstand) {
                rd=0;
                gd=0;
                bd=0;

                for(int xx=0;xx<abstand;xx=xx+1){
                    for(int yy=0;yy<abstand;yy=yy+1){
                        rd+=getRed(xx+x,yy+y);
                        gd+=getGreen(xx+x,yy+y);
                        bd+=getBlue(xx+x,yy+y);
                    }
                }
                rd=rd/abstand/abstand;
                gd=gd/abstand/abstand;
                bd=bd/abstand/abstand;

                int rgb = rd;
                rgb = (rgb << 8) + gd;
                rgb = (rgb << 8) + bd;
                image.setRGB(x/abstand,y/abstand,rgb);


            }

        }

        input=null;
        savebild=null;
        input=image;
        savebild=image;

    }


    public void unterschiede(int ungenauung,int farbabweichung,String s,String typ){
        int abstand=ungenauung;


        forground=null;
        if(typ=="URL") {
            forground = getBufferedImagefromURL(s);
        }



        BufferedImage image = new BufferedImage(input.getWidth()/abstand, input.getHeight()/abstand, BufferedImage.TYPE_INT_RGB);
        int rd=0;
        int gd=0;
        int bd=0;
        for(int x=0;x<input.getWidth()-abstand;x=x+abstand) {
            for (int y = 0; y < input.getHeight()-abstand; y = y + abstand) {
                rd=0;
                gd=0;
                bd=0;

                for(int xx=0;xx<abstand;xx=xx+1){
                    for(int yy=0;yy<abstand;yy=yy+1){
                        rd+=getRed(xx+x,yy+y);
                        gd+=getGreen(xx+x,yy+y);
                        bd+=getBlue(xx+x,yy+y);
                    }
                }
                rd=rd/abstand/abstand;
                gd=gd/abstand/abstand;
                bd=bd/abstand/abstand;

                int rgb = rd;
                rgb = (rgb << 8) + gd;
                rgb = (rgb << 8) + bd;
                image.setRGB(x/abstand,y/abstand,rgb);

                int rrgb = savebild.getRGB(x/abstand, y/abstand);
                int red = (rrgb >> 16) & 0xFF;
                int green = (rrgb >> 8) & 0xFF;
                int blue = rrgb & 0xFF;
                Color c = new Color(red,green,blue,1);
                if(rd>red-20 && rd<red+farbabweichung  &&   gd>green-farbabweichung && gd<green+farbabweichung   &&    bd>blue-farbabweichung && bd<blue+farbabweichung){
                    image.setRGB(x/abstand,y/abstand,forground.getRGB(x/abstand,y/abstand));

                }


            }

        }

        input=null;
        input=image;
    }







    public void writeImage(String s,String typ) throws IOException {
        File output = new File(s);
        ImageIO.write(input,typ,output);
    }




}
