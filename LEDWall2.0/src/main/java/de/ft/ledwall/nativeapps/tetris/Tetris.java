package de.ft.ledwall.nativeapps.tetris;

import de.ft.ledwall.SystemInterface;

public class Tetris {
    private int [][] MESH;
    private int score = 0;
    private boolean ingame =true;

    FormTemplate i;
    FormTemplate j;
    FormTemplate l;
    FormTemplate o;
    FormTemplate s;
    FormTemplate t;
    FormTemplate z;

    public Tetris() {
        MESH = new int[SystemInterface.table.getHeight()][SystemInterface.table.getWidth()];
        i = new FormTemplate(0,0,0,1,0,2,0,3,0,255,0);
        j = new FormTemplate(0,0,1,0,1,1,1,2,255,255,0);
        l = new FormTemplate(0,0,0,1,0,2,1,0,255,0,100);
        o = new FormTemplate(0,0,1,0,0,1,1,1,255,0,0);
        s = new FormTemplate(0,0,1,0,1,1,1,2,1, 254, 253);
        t = new FormTemplate(0,1,1,1,1,0,2,1,0,0,255);
        z = new FormTemplate(0,1,1,1,1,0,2,0,1, 254, 253);



    }

}
