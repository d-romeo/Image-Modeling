package com.example.imagemodeling;

public class ARGBPixel {
    private int a, r, g, b;

    public ARGBPixel(int color) {
        this.a = (color >> 24) & 0xff;
        this.r = (color >> 16) & 0xff;
        this.g = (color >> 8) & 0xff;
        this.b = color & 0xff;
    }

    public ARGBPixel(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;

    }

    public int toARGBInt(){
        return ((a << 24) | (r << 16) | (g << 8) | b);
    }


    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }


}
