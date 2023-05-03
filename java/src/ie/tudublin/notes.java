package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class notes extends Visual{

    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    FFT fft;
    float lerpedY = 0;

    public void settings()
    {
        size(1024, 1000);
    }

    public void setup()
    {
        m = new Minim(this);
        //ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        //ab = ai.mix;
        
        ap = m.loadFile("strawberry_fields_forever.mp3", 1024); //CHANGE
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    
        fft = new FFT(width, ab.size());
    }

    
    public void bubble(float x, float y, float size) 
    {
        strokeWeight(4);
        stroke(130, 60, 255);
        fill(130, 100, 250);
        circle(x, y, size);
        stroke(130, 60, 255);
        fill(130, 60, 255);
        circle(x+20, y-20, size/5);
        circle(x+30, y-5, size/10);    
    }

    float lerpedBuffer[] = new float[1024];
    public void draw()
    {
        background(0);
        colorMode(HSB);
        stroke(255);

        float half = height / 2;
        float halfW = width / 2;
        float size = 100;

        fft.forward(ab);

        int highestIndex = 0;
        for(int i = 0; i < fft.specSize() / 2; i++) {
            if (fft.getBand(i) > fft.getBand(highestIndex)) {
                highestIndex = i;
            }
        }

        float freq = fft.indexToFreq(highestIndex);

        // interpolate y position for smoother movement
        lerpedY = lerp(lerpedY, freq, 0.05f);

        bubble(halfW, -(lerpedY-500), size);
    }
}
