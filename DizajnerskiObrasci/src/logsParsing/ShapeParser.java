package logsParsing;

import java.io.Serializable;
import java.util.HashMap;

import drawing.Circle;
import drawing.HexagonAdapter;
import drawing.Line;
import drawing.Point;
import drawing.Rectangle;
import drawing.Shape;
import hexagon.Hexagon;
import mvc.DrawingFrame;
import mvc.DrawingModel;


public class ShapeParser implements Serializable {
	 
		private static final long serialVersionUID = -418593081440280081L;
		private static ShapeParser instance;

		private ShapeParser() {
		}

		/**
		 * Will parse given string, build shape based on it and return shape with
		 * properties parsed from string
		 * 
		 * @param s
		 *            - String from LOG file
		 * 
		 * @return Shape - shape that is parsed from given string
		 * @throws Exception 
		 * @throws NumberFormatException 
		 */
		public Shape parse(String s, DrawingModel model, DrawingFrame frame) throws NumberFormatException, Exception {
			String shapeType = parseType(s);
			HashMap<String, String> properties = parseShapeProperties(s); // Get all Shape properties
			switch (shapeType) {
			case "point": {
				// Return new point that is created from properties above
				Point pt = new Point(Integer.parseInt(properties.get("x")), Integer.parseInt(properties.get("y")),
						LogParserUtils.createColorFromString(properties.get("color")));
			
				return pt;
			}
			case "line": {
				Point startPt = new Point(Integer.parseInt(properties.get("startX")),
						Integer.parseInt(properties.get("startY")));
				Point endPt = new Point(Integer.parseInt(properties.get("endX")), Integer.parseInt(properties.get("endY")));
				Line line = new Line(startPt, endPt, LogParserUtils.createColorFromString(properties.get("color")));
//				line.setObserver(new ShapeObserver(frame, model)); mozda da stavim propertyChange kao kod flaga
				return line;
			}
			case "circle": {
				Point centerPt = new Point(Integer.parseInt(properties.get("X")), Integer.parseInt(properties.get("Y")));
				Circle circle = new Circle(centerPt, Integer.parseInt(properties.get("r")),
						LogParserUtils.createColorFromString(properties.get("outercolor")),
						LogParserUtils.createColorFromString(properties.get("innercolor")));
//				circle.setObserver(new ShapeObserver(frame, model));
				return circle;
			}
			
			case "rectangle": {
				Point upperLeftPt = new Point(Integer.parseInt(properties.get("UpperX")),
						Integer.parseInt(properties.get("UpperY")));
				Rectangle rectangle = new Rectangle(upperLeftPt, Integer.parseInt(properties.get("height")),
						Integer.parseInt(properties.get("width")),
						LogParserUtils.createColorFromString(properties.get("outercolor")),
						LogParserUtils.createColorFromString(properties.get("innercolor")));
//				rectangle.setObserver(new ShapeObserver(frame, model));
				return rectangle;
			}
			case "hexagon": {
				Hexagon hexa = new Hexagon(Integer.parseInt(properties.get("X")), Integer.parseInt(properties.get("Y")),
						Integer.parseInt(properties.get("r")));
//				HexagonAdapter hexagonAdapter = new HexagonAdapter(hexa,
//						LogParserUtils.createColorFromString(properties.get("outercolor")),
//						LogParserUtils.createColorFromString(properties.get("innercolor")));
//				hexagonAdapter.setObserver(new ShapeObserver(frame, model));
				
				HexagonAdapter hexagonAdapter = new HexagonAdapter(hexa.getX(),hexa.getY(),hexa.getR(),hexa.getAreaColor(),hexa.getBorderColor());
				return hexagonAdapter;
			}
			}
			return null;
		}

		/**
		 * Will parse given string, build shape based(with observer in it) on it and
		 * return shape with properties parsed from string
		 * 
		 * @param s
		 * @param model
		 * @param frame
		 * @return
		 * @throws Exception 
		 * @throws NumberFormatException 
		 */
		public Shape parse(String s) throws NumberFormatException, Exception {
			String shapeType = parseType(s);
			HashMap<String, String> properties = parseShapeProperties(s); // Get all Shape properties
			switch (shapeType) {
			case "point": {
				// Return new point that is created from properties above
				Point pt = new Point(Integer.parseInt(properties.get("x")), Integer.parseInt(properties.get("y")),
						LogParserUtils.createColorFromString(properties.get("color")));
				return pt;
			}
			case "line": {
				Point startPt = new Point(Integer.parseInt(properties.get("startX")),
						Integer.parseInt(properties.get("startY")));
				Point endPt = new Point(Integer.parseInt(properties.get("endX")), Integer.parseInt(properties.get("endY")));
				Line line = new Line(startPt, endPt, LogParserUtils.createColorFromString(properties.get("color")));
				return line;
			}
			case "circle": {
				Point centerPt = new Point(Integer.parseInt(properties.get("X")), Integer.parseInt(properties.get("Y")));
				Circle circle = new Circle(centerPt, Integer.parseInt(properties.get("r")),
						LogParserUtils.createColorFromString(properties.get("outercolor")),
						LogParserUtils.createColorFromString(properties.get("innercolor")));
				return circle;
			}
			
			case "rectangle": {
				Point upperLeftPt = new Point(Integer.parseInt(properties.get("UpperX")),
						Integer.parseInt(properties.get("UpperY")));
				Rectangle rectangle = new Rectangle(upperLeftPt, Integer.parseInt(properties.get("height")),
						Integer.parseInt(properties.get("width")),
						LogParserUtils.createColorFromString(properties.get("outercolor")),
						LogParserUtils.createColorFromString(properties.get("innercolor")));
				return rectangle;
			}
			case "hexagon": {
				Hexagon hexa = new Hexagon(Integer.parseInt(properties.get("X")), Integer.parseInt(properties.get("Y")),
						Integer.parseInt(properties.get("r")));
//				HexagonAdapter hexagonAdapter = new HexagonAdapter(hexa,
//						LogParserUtils.createColorFromString(properties.get("outercolor")),
//						LogParserUtils.createColorFromString(properties.get("innercolor")));
//				
				HexagonAdapter hexagonAdapter = new HexagonAdapter(hexa.getX(),hexa.getY(),hexa.getR(),LogParserUtils.createColorFromString(properties.get("outercolor")),LogParserUtils.createColorFromString(properties.get("innercolor")));
				
				return hexagonAdapter;
			}
			}
			return null;
		}

		/**
		 * Will return String with shape type
		 * 
		 * @param s
		 * @return
		 */
		public String parseType(String s) {
			String prefix = s.split("\\(")[0]; // Will get everything before Shape properties
			String[] prefixArray = prefix.split("_");
			String type = prefixArray[prefixArray.length - 1].toLowerCase(); // Get Shape type
			return type;
		}

		/**
		 * Will return HashMap with Shape properties from given string
		 * 
		 * @param s
		 * @return
		 */
		public HashMap<String, String> parseShapeProperties(String s) {
			String propertiesString = s.split("\\(")[1]; // Gets shape properties within parentheses
			propertiesString = propertiesString.substring(0, propertiesString.length() - 1); // Remove closing ) from
																								// original string
			HashMap<String, String> properties = new HashMap<String, String>();
			// Split string to get each property
			for (String property : propertiesString.split(",")) {

				String[] keyValue = property.split("="); // Split key value pair
				properties.put(keyValue[0], keyValue[1]);

			}
			return properties;
		}

		/**
		 * Return Thread safe singleton object also using Lazy Loading
		 * 
		 * @return
		 */
		public static ShapeParser getInstance() {
			if (instance == null) {
				synchronized (ShapeParser.class) {
					if (instance == null) {
						instance = new ShapeParser();
					}
				}
			}
			return instance;
		}
}
