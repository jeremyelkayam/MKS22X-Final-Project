# MKS22X Final Project
### "Stuycrosoft XL"
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
