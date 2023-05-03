# 🍓 Music Visualiser Project

- Name: Sean Tighe
- Student Number: C21329431


- Name: Grace MacDonnell
- Student Number: C21307546

## Instructions
- Fork this repository and use it a starter project for your assignment
- Create a new package named your student number and put all your code in this package.
- You should start by creating a subclass of ie.tudublin.Visual
- There is an example visualiser called MyVisual in the example package
- Check out the WaveForm and AudioBandsVisual for examples of how to call the Processing functions from other classes that are not subclasses of PApplet

# Description of the assignment

Our assignment is an audio visualiser based on the song Strawberry Fields Forever by The Beatles

![A different image](https://i.scdn.co/image/ab67616d0000b273692d9189b2bd75525893f0c1)

The visualiser is mainly based off the lyrics to the song with natural imagery and other visuals that are inspired by the psychedelic sounds of the music.

### Visual 1 - Sean
A green bush with strawberries on it and the strabwerries grow to a certain size they fall off the bush and the loop

### Visual 1 - Sean
A solar system that orbits a sun solar flaring at the rate of the music, with planets orbitting the sun.
Inspired by the "dissonances and eerie space-age sounds" - [The Times](https://en.wikipedia.org/wiki/Strawberry_Fields_Forever#Critical_reception)

### Visual 1 - Sean
A binary fractal tree that infinitely expands to the next branch to the next, with the branches swaying to the music.

![A different image](https://upload.wikimedia.org/wikipedia/en/thumb/2/2e/Beatles_in_%22Strawberry_Fields_Forever%22_music_video.png/220px-Beatles_in_%22Strawberry_Fields_Forever%22_music_video.png)

### Visual 1 - Grace
This visual is inspired by the lyrics and fields, showing flowers that change colour based on the prominant frequency. The flowers are also inspired by the 70s fashion and the floral clothing at the time of the song's writing.

### Visual 1 - Grace
Pines trees that grow with the different frequencies of music on a sky backdrop

### Visual 1 - Grace
A ocean with fish cycling along the water with bubbles that bubble up and down based on overall frequency level

# Instructions

To play the visuals you run the main file, making sure that the song is in the data directory

# How it works

The youtube link to our project:

[![YouTube](http://img.youtube.com/vi/J2kHSSFA4NU/0.jpg)](https://www.youtube.com/watch?v=J2kHSSFA4NU)

# What I am most proud of in the assignment

# Markdown Tutorial

This is *emphasis*

This is a bulleted list

- Item
- Item

This is a numbered list

1. Item
1. Item

This is a [hyperlink](http://bryanduggan.org)

# Headings
## Headings
#### Headings
##### Headings

This is code:

```Java
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```

So is this without specifying the language:

```
public void render()
{
	ui.noFill();
	ui.stroke(255);
	ui.rect(x, y, width, height);
	ui.textAlign(PApplet.CENTER, PApplet.CENTER);
	ui.text(text, x + width * 0.5f, y + height * 0.5f);
}
```
