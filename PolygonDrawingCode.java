package polygonDrawing;

import java.util.Scanner;

public class PolygonDrawingCode {

	public static void main(String[] args) {
		//Declare which lines are what
		String Vertical = "|";
		String Horizontal = "-";
		String DiagonalRight = "/";
		String DiagonalLeft = "\\";

		//Declare the scanner to get user input
		Scanner inputScanner = new Scanner(System.in);

		//Take input from the user for the number of sides of the polygon
		int numberOfSides = takeInput(inputScanner);

		//Draw the polygon based on the number of sides
		drawPolygon(numberOfSides);


	}

	public static int takeInput(Scanner inputScanner) {
		//Take input from the user for the number of sides of the polygon
		int userInput = 0;
		boolean validInput = false;

		while (!validInput) {
			System.out.print("Enter the number of sides for the polygon ");
			if (inputScanner.hasNextInt()) {
				userInput = inputScanner.nextInt();
				if (userInput < 3) //Cant have less than 3 sides
				{
					System.out.println("Invalid input. Please enter a positive integer.");
				} else 
				{
					return userInput;
				}
			} else {
				System.out.println("Invalid input. Please enter a valid integer.");
				inputScanner.next(); // Clear the invalid input
			}
		}

		return userInput;
	}

	public static String[][] drawPolygon(int numberOfSides) {
		//Implement the logic to draw the polygon based on the number of sides
		//This is a placeholder for the actual drawing logic
		System.out.println("Drawing a polygon with " + numberOfSides + " sides.");
		//We need to calculate the angles and lengths of the sides based on the number of sides and then draw it accordingly.
		float angle = (180 * (numberOfSides - 2)) / numberOfSides; // Calculate the angle for each side of the polygon
		System.out.println("Angle for each side: " + angle);
		//Declare the grid size based on the number of sides
		int gridSize = numberOfSides * 2; // This is a simple approximation
		//Make a 2D array to represent the grid
		String[][] grid = new String[gridSize][gridSize];
		//Initialize the grid with spaces
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = "__";
			}
		}

		//This is where we draw onto the grid
		//Angles refrence: (Negative are counter-clockwise, Positive are clockwise)
		// "|" -> "-" = 90 degrees
		// "/" -> "|" = -45 degrees
		// "\" -> "|" = 45 degrees
		// "/" -> "-" = 45 degrees
		// "\" -> "-" = -45 degrees

		//We want to fnd all points of the polygon and then draw lines between them. We can use trigonometry to find the points based on the angle and length of each side.
		//We find the points on the grid based on the number of points / faces

		// Keep the vertices within the grid and center the polygon
		int center = gridSize / 2;
		int radius = center - 1;
		int prevRow = -1;
		int prevColumn = -1;
		int column = -1;
		int row = -1;

		for (int i = 0; i < numberOfSides; i++) {
			//Store the previous column and row to draw lines between the points
			if (column != -1)
			{
				prevColumn = column;
			}
			if (row != -1)
			{
				prevRow = row;
			}
			double currentAngle = 2 * Math.PI * i / numberOfSides;
		    column = center + (int) Math.round(radius * Math.cos(currentAngle));
			row = center + (int) Math.round(radius * Math.sin(currentAngle));
			grid[row][column] = String.valueOf(i);

			if (prevRow != -1 && prevColumn != -1) {
				drawLine(grid, prevRow, prevColumn, row, column); //Draws line between the previous point and the current point
			}
			
		}

		//Draw the grid
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		return grid;
	}

	public static void drawLine(String[][] grid, int startRow, int startCol, int endRow, int endCol) {
		//Implement the logic to draw a line on the grid from (startRow, startCol) to (endRow, endCol)
		//This is a placeholder for the actual line drawing logic
		System.out.println("Drawing a line from (" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ").");
		//Get vertical and horizontal distances
		float verticalDistance = endRow - startRow;
		float horizontalDistance = endCol - startCol;
		System.out.println("Vertical distance: " + verticalDistance + ", Horizontal distance: " + horizontalDistance);
		//Positive vertical distance means we are going down, negative means we are going up
		//Positive horizontal distance means we are going right, negative means we are going left
		//We can use the Bresenham's line algorithm to draw the line between the two points
		float gradient;
		if (horizontalDistance == 0) {
				gradient = 1; // To avoid division by zero, we can set the gradient to 1 for vertical lines
		}
		else
		{
			 gradient = verticalDistance / horizontalDistance; // This will give us the slope of the line
		}
		System.out.println("Gradient: " + gradient);

		

		for (int x = startCol; x <= endCol; x++) {
			int y = startRow + (int) Math.round(gradient * (x - startCol));
			grid[y][x] = "*"; // Draw the line with asterisk
		}
	}

}
