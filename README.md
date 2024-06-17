# Paint
  Paint is a Web Application that used to create different shapes and the editing them.
  
## Table of Contents

- [Installation](#Installation) 🌐
- [Features](#Features)
- [Design Patterns](#Design-Patterns)
- [User Guide](#User-Guide)
- [User Interface](#User-Interface)
- [Tutorial](#Tutorial)
- [Contributers](#Contributers)

## Installation

To use the tools and examples provided in this repository, you need to have npm installed on your system. You can install the necessary dependencies by cloning the repository and running the installation command.

1. Clone the repository to your local machine.
2. Navigate to the repository directory.
3. Install the required dependencies.

## Features
- Shapes: Line Segment, Circle, Ellipse, Triangle, Rectangle and Square
- Colouring
- Resizing
- Erasing / Deleting
- Clearing whole board
- Copying a shape
- Moving and Dragging
- Undo-ing
- Redo-ing
- Saving in JSON or XML file
- Loading previous saved JSON or XML file
- Rotating shape
- Saving board as PNG image
- Changing the mode of the page to Pink or Blue.

## Design-Patterns
1. We have applied Factory design pattern such that when the user wants to create a shape, the shape factory:
  a. Creates it for him as we send the shapeType to the backend
  b. We create the required shape according to the shapeType given from frontend, choosing the right class that satisfies the required shape and sending to frontend the shape we have created with its default values.

2. We have applied Prototype design pattern such that when the user wants to copy a shape:
  a. we send the shape we want to copy to the backend
  b. Backend creates a shape from the same class sent from Frontend
  c. It gives its attributes the same values as the sent shape
  d. Save it in the Backend and send it to the frontend.

## User-Guide
    - Upon opening the application the drawing board is fully white and empty
    - To create a shape, just choose it from the menu and press where you want to put it on the board.
    - The fill, erase, and copy buttons must be selected and deselected to be activated or not, it doesn't get activated or deactivated by itself.
    - To change the shape color :
      a. You choose any color you want from the pallet
      b. You MUST activate the fill button
      c. Then choose the shape you want to apply the color to
    - You can change the position of a shape by dragging it
    - When you click on a shape, it gets selected and then you can resize and rotate it
    - To erase a drawn shape you activate the erase button and then choose the shape you want to delete
    - By pressing the clear button, all the shapes on the board will be deleted
    - To make a copy from a shape you activate the copy button and then choose the shape you want to copy (please note that a copied shape is generated with a default offset so if you copied the same shape multiple times the copies will be completely overlapped so you will have to start moving one to see the copy under it)
    - The undo and the redo buttons simply work by pressing on them
    - Once a file is loaded, you cannot undo the load and you cannot undo previous work on that load but you can add/create shapes, make changes, and undo them normally.
    - There is a menu on the right that contains the save and load buttons
    - To save the current file you enter the folder path separated from the folder name and then choose the required extension to save with
    - To load a file you copy the file as a path and enter it as input in the load text box
    - You can export your drawing and save it to your device as an image by pressing the (save as image) button
    - We made a button on the right that activates our one and only PINK MODE :)

## User-Interface
- Blue Mode
![image](https://github.com/MostafaElKaranshawy/Paint/assets/110842052/4c1e7dda-8613-49e7-b91f-c35f3195d4ff)
- Pink mode 
![image](https://github.com/MostafaElKaranshawy/Paint/assets/110842052/4ed2977f-8e34-45c8-be8e-78195d6f6e07)

## Tutorial



https://github.com/MostafaElKaranshawy/Paint/assets/110842052/91f02468-f595-4ab6-80ad-854841b06559




## Contributers
### Front-End
- [Mustafa ElKaranshawy](https://github.com/MostafaElKaranshawy)
- [Rowan Gamal](https://github.com/rowanxgamal)
### Back-End
- [Youssef Mahmoud](https://github.com/Youssef-Mahmoud0)
- [Youmna Yasser](https://github.com/yomnay888)
