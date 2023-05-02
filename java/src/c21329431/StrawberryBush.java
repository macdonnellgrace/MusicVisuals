package c21329431;

import processing.core.PApplet;
import processing.core.PImage;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class StrawberryBush extends PApplet {

    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;
    PImage photo;
    PImage photo2;

    Strawberry[] strawberries = new Strawberry[20];

    public void settings() {
        size(500, 500);

    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
    
        for (int i = 0; i < strawberries.length; i++) {
            float x = random(width - 50) + 25; // Only spawn within screen borders
            float y = random(height - 50) + 25;
            float size = random(20, 50); // Randomize size
            strawberries[i] = new Strawberry(x, y, size);
        }

        photo = loadImage("strawberry.png");
        photo2 = loadImage("bush.png");
    }
    
    public void draw() {
        background(photo2);

        for (int i = 0; i < strawberries.length; i++) {
            Strawberry s = strawberries[i];
            float amplitude = ab.level();
            s.updateSize(amplitude);
            s.display();

            if (s.getY() >= height) {
                float x = random(width - 50) + 25;
                float y = random(height - 50) + 25;
                float size = random(20, 50);
                strawberries[i] = new Strawberry(x, y, size);
            }
        }
    }

    public class Strawberry {
        private float x;
        private float y;
        private float size;
        private boolean isMaxSize;
    
        public Strawberry(float x, float y, float size) {
            this.x = x-30;
            this.y = y-30;
            this.size = size;
            this.isMaxSize = false;
        }
    
        public void updateSize(float amplitude) {
            float growthFactor = map(amplitude, 0, 1, 0, 5);
            size += growthFactor;
            size = constrain(size, 20, 150); // Increase max size
            if (size == 150) {
                isMaxSize = true;
            }
        }
    
        public void display() {
            if (isMaxSize) {
                y += 10;
            }
            image(photo, x, y, size, size);
        }
    
        public float getY() {
            return y;
        }
    }
}