package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class ocean extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    FFT fft;

    float fishX = 0;
    float fishY = 500;
    float fishSpeed = 1;
    float diameter = 75;

    public void settings() {
        size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        fft = new FFT(width, ab.size());

        // background(130, 100, 200);
    }

    public void bubble(float x, float y, float size) {

        strokeWeight(4);
        stroke(130, 60, 255);
        fill(130, 100, 250);
        circle(x, y, size);
        stroke(130, 60, 255);
        fill(130, 60, 255);
        circle(x + 20, y - 20, size / 5);
        circle(x + 30, y - 5, size / 10);
    }

    public void backgroundBub() {
        strokeWeight(3);
        stroke(130, 60, 255);
        fill(130, 100, 250);

        circle(100, 100, 40);
        circle(500, 300, 20);
        circle(300, 500, 20);
        circle(550, 350, 10);
        circle(550, 150, 10);
        circle(550, 500, 20);
        circle(100, 400, 10);
        circle(700, 700, 10);
        circle(900, 600, 30);
        circle(800, 100, 30);
    }

    float lerpedBuffer[] = new float[1024];

    public void draw() {

        float lerpedY = fishY;
        background(130, 100, 240);

        backgroundBub();

        // Get the amplitude from the audio ab
        float amplitude = ab.get(0) * 100;
        // Move the fish horizontally based on the amplitude
        fishX += fishSpeed;
        // Wrap the fish around when it reaches the edge of the screen
        if (fishX > width + diameter) {
            fishX = -diameter;
        }

        // Draw the fish
        fish(fishX, fishY, 20);
        fish(fishX + 100, fishY - 100, 30);
        fish(fishX + 200, fishY + 200, 10);
        fish(fishX - 200, fishY - 300, 0);

        fish(fishX - 400, fishY + 100, 40);

        // fishX += fishSpeed * (1 + amplitude);
        lerpedY = lerp(lerpedY, (float) (500 + amplitude * 10), (float) (0.1));

        // -----------------------------------
        // bubble

        float halfW = width / 2;
        float size = 100;
        float y = -100;

        fft.forward(ab);

        int highestIndex = 0;
        for (int i = 0; i < fft.specSize() / 2; i++) {
            if (fft.getBand(i) > fft.getBand(highestIndex)) {
                highestIndex = i;
            }
        }
        float freq = fft.indexToFreq(highestIndex);

        // interpolate y position for smoother movement
        lerpedY2 = lerp(lerpedY2, freq, 0.005f);
        y += lerpedY2 * size * 0.1f;
        
        bubble(halfW, -(y - 500), size);
        bubble(halfW + 200, -(y - 300), size - 10);
        bubble(halfW + 400, -(y - 400), size - 30);
        bubble(halfW - 400, -(y - 400), size + 10);
        
        bubble(halfW - 200, -(y - 600), size - 10);
        
        bubble(halfW - 200, -(y - 200), size);
    }

    float lerpedY = 0;
    float lerpedY2 = 0;

    public void fish(float x, float y, float c) {

        noStroke();

        fill(c, 170, 255);

        // Draw the tail
        float tailSize = diameter / 3;
        float tailX = x - diameter / 4 - tailSize / 2;
        float tailY = y;
        triangle(tailX, tailY, tailX - tailSize, tailY - tailSize / 2, tailX - tailSize, tailY + tailSize / 2);

        ellipse(x, y, diameter, diameter / 3);

    }
}