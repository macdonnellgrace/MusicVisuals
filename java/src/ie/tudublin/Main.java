package ie.tudublin;

import c21329431.StrawberryBush;
import c21329431.spiral;
import c21329431.tree;

public class Main
{
	

	public static void Tree()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new tree());
    }

	public static void strawberry()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new StrawberryBush());
    }

	public static void Spiral()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new spiral());
    }

	public static void Flower()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new flower());
    }

	public static void vines()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new vines());
    }

	public static void notes()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new notes());
    }

	public static void ocean()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new ocean());
    }

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Hello world");

    	//Tree();
		//Spiral();
		//strawberry();
		//Flower();
		//vines();
		//notes();
		ocean();
	}
	
	
}