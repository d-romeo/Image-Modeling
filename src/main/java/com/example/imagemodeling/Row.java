package com.example.imagemodeling;

public class Row implements Runnable{
    private ARGBPixel[] pixels;
    private int length;
    private boolean end;

    public Row(int[] color) {
        this.length = color.length;
        pixels = new ARGBPixel[color.length];
        for(int i=0;i<length;i++){
            pixels[i] = new ARGBPixel(color[i]);
        }
        this.end = false;
    }

    @Override
    public void run() {
        for(int i=0;i<pixels.length;i++){
            int a = pixels[i].getA();
            int r = pixels[i].getR();
            int g = pixels[i].getG();
            int b = pixels[i].getB();
            int avg = (r + g + b) / 3;
            pixels[i] = new ARGBPixel(a,avg, avg, avg);
        }
        this.end = true;
    }

    public int[] ARGBInt(){
        int[] ARGBInt = new int[length];
        for(int i = 0; i<length; i++){
            ARGBInt[i] = pixels[i].toARGBInt();
        }
        return ARGBInt;
    }


    public ARGBPixel[] getPixels() {
        return pixels;
    }

    public ARGBPixel[] getModifyPixels() {
        if(!end){
            throw new RuntimeException("end == false");
        }
        return pixels;
    }
}
