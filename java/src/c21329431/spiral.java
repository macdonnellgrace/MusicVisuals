package c21329431;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class spiral extends PApplet {
    
    Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;
    float ellipseSize = 0;
    float planetAngle = 0;

    public void settings() 
    {
        size(500, 500);

    }

    public void setup() 
    {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        
    }

    public void draw() 
    {
        background(0);
        stroke(255);
        translate(width/2, height/2);  // move origin to center of the screen

        stars();

        sun();
        rotate(frameCount * 0.01f);  // rotate the coordinate system for the planet
        planet();
        moon();
    }    

    public void stars()
{
    // Set the random seed to a fixed value
    randomSeed(1);

    for (int i = 0; i < 100; i++) 
    {
        point(random(-250, 250),random(-250, 250));
    }
}


    public void sun() 
    {
        float angle = 0;
        float radius = 0;
        float delta = TWO_PI / ab.size();  // increment angle based on the size of the buffer
    
        strokeWeight(2);
    
        // loop through the audio buffer and draw a spiral based on the amplitude
        for (int i = 0; i < ab.size(); i++) {
            float amplitude = ab.get(i);
            radius = map(amplitude, 0, 1, 10, 200);  // map the amplitude to a radius
            angle += delta;
    
            // set the stroke color based on the amplitude and map to a nature-inspired color palette
            stroke(map(amplitude, 0, 1, 0, 255), 200, 200);
    
            // calculate the x and y coordinates based on the angle and radius
            float x = radius * cos(angle); // multiply by 2 to scale the spiral
            float y = radius * sin(angle);
    
            // draw a line from the previous coordinates to the new coordinates
            line(x, y, radius * cos(angle - delta), radius * sin(angle - delta));
        }
    }

    public void planet() 
    {
        float amplitude = ab.get(100);  
        float Planetsize = map(amplitude, 0, 1, 10, 100);  
        int planetColor = color( 180, 255,map(amplitude, 200, 1, 0, 255));        
        stroke(planetColor);  
        strokeWeight(2);

        float angle = radians(0);  
        float radius = 150;

        float Planetx = radius * cos(angle); 
        float Planety = radius * sin(angle);

        translate(Planetx, Planety);

        // interpolate between the current size and the new size
        float newSize = lerp(ellipseSize, Planetsize, 0.2f);
        ellipseSize = newSize;

        // draw the planet shape with the interpolated size
        fill(planetColor);
        ellipse(0, 0, newSize * 2, newSize * 2);
        stroke(61,91,100);
        strokeWeight(3);
        line(Planetx/150, (Planety/150)-20, Planetx/150, (Planety/150)+20 );    
    }

    public void moon() 
    {
        float amplitude = ab.get(100);  
        float moonsize = map(amplitude, 0, 1, 10, 100);  
        stroke(150,120,160);  
        strokeWeight(2);

        float angle = radians(frameCount + 100);  
        float radius = 30;

        float Planetx = radius * cos(angle); 
        float Planety = radius * sin(angle);

        translate(Planetx, Planety);

        // interpolate between the current size and the new size
        float newSize = lerp(ellipseSize, moonsize, 0.2f);
        ellipseSize = newSize;

        // draw the planet shape with the interpolated size
        fill(150,120,160);  
        ellipse(0, 0, newSize, newSize);
    }
}