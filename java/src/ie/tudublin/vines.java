package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class vines extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    float halfH = height / 2;
    float halfW = width/2;

    public void settings() {
        size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024); 
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    
        fft = new FFT(width, 1024);
    }

    public void vine(float x, float y) 
    {
        float y1, y2, y3, y4;
        float size1;
        float size2;
        float size3;
        
        y1 = 800;
        y2 = 750;
        y3 = y2-70;
        y4 = y3-60;
        size1 = 80;
        size2 = 60;
        size3 = 30;

        // trunk
        stroke(20, 100, 120);
        strokeWeight(30);
        line(x, y1, x, y2-y);
 
        // leaves
        strokeWeight(10);
        stroke(80, 220, 100);
        fill(80, 220, 100);
        triangle(x-size1, y2-y, x+size1, y2-y,x, y2-size1-y);
        stroke(80, 220, 110);
        triangle(x-size2, y3-y, x+size2, y3-y,x, y3-size2-y);
        stroke(80, 220, 120);
        triangle(x-size3, y4-y, x+size3, y4-y,x, y4-size3-y);

    }

    public void clouds() 
    {

        strokeWeight(20);
        stroke(150, 20, 255);
        fill(150, 20, 255);

        line(200,310, 300,310);
        circle(299,300, 20);
        circle(200,295, 30);
        circle(250,280, 50);

        line(500,610, 600,610);
        circle(599,600, 20);
        circle(500,595, 30);
        circle(550,580, 50);
    }

    float lerpedBuffer[] = new float[1024];
    float totalX = 0;
    float[] totalY = {0, 0, 0, 0, 0, 0};

    public void draw()
    {
        float average = 0;
        float sum = 0;
        int highestIndex = 0;

        background(150,120,160);
        clouds();

        stroke(80, 130, 60);
        strokeWeight(400);
        line(width, height, width - 1024, height);
        
        for(int i = 0 ;i < fft.specSize() / 2 ; i ++)
        {
            if (fft.getBand(i) > fft.getBand(highestIndex))
            {
                highestIndex = i;
            }
        }

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float freq = fft.indexToFreq((int)(smoothedAmplitude * 100000.0f));

        freq = freq / 100;
        System.out.println(freq);


        // generates trees along ground

        if (freq > 150)
        {
            // Increase the second coordinate of y for tree 0
            totalY[5] += freq * 0.01; // Scale rate of change by freq

            totalY[4]--;
            totalY[3]--;
            totalY[2]--;
            totalY[1]--;
            totalY[0]--;
        }
    
        if (freq > 100 && freq <= 150)
        {
            // Increase the second coordinate of y for tree 1
            totalY[4] += freq * 0.01; // Scale rate of change by freq

            totalY[5]--;
            totalY[3]--;
            totalY[2]--;
            totalY[1]--;
            totalY[0]--;
        }
    
        if (freq > 75 && freq <= 100)
        {
            // Increase the second coordinate of y for tree 2
            totalY[3] += freq * 0.01; // Scale rate of change by freq

            totalY[5]--;
            totalY[4]--;
            totalY[2]--;
            totalY[1]--;
            totalY[0]--;
        }
    
        if (freq > 50 && freq <= 75)
        {
            // Increase the second coordinate of y for tree 3
            totalY[2] += freq * 0.01; // Scale rate of change by freq

            totalY[5]--;
            totalY[4]--;
            totalY[3]--;
            totalY[1]--;
            totalY[0]--;
        }
    
        if (freq > 25 && freq <= 50)
        {
            // Increase the second coordinate of y for tree 4
            totalY[1] += freq * 0.01; // Scale rate of change by freq

            totalY[4]--;
            totalY[3]--;
            totalY[2]--;
            totalY[5]--;
            totalY[0]--;
        }
    
        if (freq <= 25)
        {
            // Increase the second coordinate of y for tree 5
            totalY[0] += freq * 0.01; // Scale rate of change by freq

            totalY[4]--;
            totalY[3]--;
            totalY[2]--;
            totalY[1]--;
            totalY[5]--;
        }

        for (int i = 0; i < 6; i++) {

            vine(totalX + (i * 200),totalY[i]); 

            if (totalY[i] < 0){
                totalY[i] = 0;
            }

        }
        
    }
}        

