package de.ft.ledwall.nativeapps.tetris;

public class FormTemplate {
    private int relativePositionPoints[][];
    private int r,g,b;
    public FormTemplate(int x1,int y1,int x2,int y2, int x3,int y3,int x4,int y4,int r, int g, int b) {
        relativePositionPoints = new int[4][3];

        relativePositionPoints[x1][y1] = 1;
        relativePositionPoints[x2][y2] = 1;
        relativePositionPoints[x3][y3] = 1;
        relativePositionPoints[x4][y4] = 1;

     this.r = r;
     this.g = g;
     this.b = b;


    }


}
