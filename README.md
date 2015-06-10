# MKS22X Final Project
**_The Hackstreet ~~Boys~~: Jeremy Elkayam & Tina Zhang_**

![alt text](https://raw.githubusercontent.com/jeremyelkayam/MKS22X-Final-Project/Jeremy/StuycrosoftXL.png)

A spreadsheet program not at all based on Microsoft Excel

### Roadmap
- Create an internal representation of the spreadsheet (Create a Data object and make a 2D array of it)
- Storing data (strings, ints, floats)
- Create an interactive interface using Java's GUI (Swing)
- Saving/loading spreadsheets
- Functions (sum, difference, multiplication, division, integer division, modulus)
- Tables & charts

### Goals
- Interface using GUI
- Internal representation of spreadsheet
- Functions - add/subtract/multiply/divide 
- Additional functions

### Changelog
(Remember to update this every time you make changes... otherwise, face the wrath of the brutish taskmaster Mr. K!!)

#### 6/9
- Jeremy: Refactoring of function checks for elegance and efficiency
- Added max & min functions
#### 6/8
- Jeremy: Now numbers can be passed into functions in addition to cells
- Added SUMSQ() function
#### 6/7
- Jeremy(10:30 pm): Fixed but where functions didn't use floating-point math
- Added average function, count function
- Jeremy(12:30 am): Stayed up late playing video games and then decided to work on project
- Fixed bug where functions wouldn't evaluate when loading files.

#### 6/6
- Jeremy: After an entire freaking year I learn that javac is stupid and doesn't actually compile all your required classes unless you tell it what you need compiled. I was messing with my code and it was doing nothing until I realized that javac was being dumb and not compiling all the classes I needed.
- AND ALSO GOT FUNCTIONS WORKING!!!!!!!!!!!!! THIS IS THE GREATEST THING THAT THIS PROJECT HAS EVER SEEN!!!!!

#### 6/5
- Jeremy: setData() now ensmallens the sheet if you set it to an empty string and there is no data in the other sheets.
- ~~Didn't test anything in class because apparently java 6 doesn't do strings with the switch statement. (these machines SUCK)~~ It is now tested and WORKING GREAT!!
- This feature doesn't really do much for the user - it's really just there to make the .csv files as small as possible.
- Added a splash screen. See it by running Interface using the following command: `java -splash:StuycrosoftXL.png Interface`

#### 6/4
- Jeremy: setData() now embiggens the sheet when you try to set a cell that the sheet does not contain. 

#### 6/3
- Everyone went to jprom & didn't do anything school-related

#### 6/2
- Jeremy: Added ability to load sheet from csv file

#### 6/1
- Jeremy: Added temp interface without swing because I have no idea how swing works
- Can put data in cells (but not functions) & enlarge the spreadsheet horizontally or vertically
- Added option to save spreadsheet as .csv file (no support for loading files yet)

#### 5/31
- Jeremy: Added GetCell() methods to Sheet class for different formatting of cell location

#### 5/29
- Jeremy: Finished =SUM(), currently works on numbers only - cannot access other cells

#### 5/28
- Jeremy: Added methods to convert between base-26 numbers and base-10 numbers.
- Began working on functions - started sum, did not finish.

#### 5/26
- Jeremy: Updated readme, added setData(number) method in Cell class
- added new Cell constructor for empty cell
- added method to check if empty cell
- deleted MathCell & added framework for it in Cell
- Tina: worked on the interface - added File Menu and Edit Menu

### Stable Versions
Due 6/1, 6/8, 6/15

#### Release 6/1/2015
##### Instructions:
Switch to branch "6/1" and compile/run TempInterface.java
