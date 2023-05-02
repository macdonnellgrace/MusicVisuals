package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class flower extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        fft = new FFT(width, 1024);
        
    }

    public void sinFlower(float h, float w, float vol) {

        strokeWeight(0);

        // Calculate the color based on the volume
        float c = map(vol, 0, 1, 200, 500);
        fill(c, 200, 220);

        circle(h + 50, w + 10, 75); // works
        circle(h - 50, w - 10, 75);
        circle(h - 10, w + 50, 75);
        circle(h + 10, w - 50, 75);

        fill(c, 200, 255);

        circle(h + 30, w + 20, 50); 
        circle(h - 30, w - 20, 50);
        circle(h - 20, w + 30, 50);
        circle(h + 20, w - 30, 50);

        fill(30, 255, 255);
        circle(h, w, 50);

    }


    float lerpedBuffer[] = new float[1024];

    public void draw() {
        background(80, 190, 150);
        float halfH = height / 2;
        float halfW = width / 2;
        float sum = 0;

        fft.forward(ab);
        float highestFrequency = 0;
        int highestIndex = 0;

        for(int i = 0 ;i < fft.specSize() / 2 ; i ++)
        {
            line(i * 2.0f, height, i * 2.0f, height - fft.getBand(i) * 5.0f);

            if (fft.getBand(i) > highestFrequency)
            {
                highestIndex = i;
                highestFrequency = fft.getBand(i);
            }
            

        }

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {

            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }

        // Calculate the overall volume of the song
        float c = sum / (float) ab.size();
        c = c / 2;

        // Pass the overall volume to the sinFlower method
        sinFlower(halfH, halfW, c); // middle flower
        sinFlower(height/4, width/4, c);
        sinFlower(height-300, width/3, c);

        sinFlower(halfH+300, halfW+300, c);
        sinFlower(halfH-350, halfW+50, c);
        sinFlower(height/5, width-200, c);


    }}
