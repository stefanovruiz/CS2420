package kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class RectHV {
	private final double xmin, ymin, xmax, ymax;

	   public RectHV(double xmin, double ymin, double xmax, double ymax){   
		   // construct the rectangle [xmin, xmax] x [ymin, ymax] 	                     
		   if (xmax < xmin || ymax < ymin) {
	            throw new IllegalArgumentException();
	        }
		   this.xmin = xmin;
		   this.xmax = xmax;
		   this.ymin = ymin;
		   this.ymax = ymax;
	   }
	   public  double xmin() {
		   // minimum x-coordinate of rectangle 
		   return xmin;
	   }
	   public  double ymin() {
		   // minimum y-coordinate of rectangle
		   return ymin;
	   }
	   public  double xmax() {
		   // maximum x-coordinate of rectangle 
		   return xmax;
	   }
	   public  double ymax(){ 
		   // maximum y-coordinate of rectangle 
		   return ymax;
		   
	   }
	   public boolean contains(Point2D p) {
		   // does this rectangle contain the point p (either inside or on boundary)? 
		   return (p.x() >= xmin) && (p.x() <= xmax)
		            && (p.y() >= ymin) && (p.y() <= ymax);
	   }
	   public boolean intersects(RectHV that){
		   // does this rectangle intersect that rectangle (at one or more points)? 
		   return this.xmax >= that.xmin && this.ymax >= that.ymin
		            && that.xmax >= this.xmin && that.ymax >= this.ymin;
	   }
	   public  double distanceSquaredTo(Point2D p){
		   // square of Euclidean distance from point p to closest point in rectangle 
		   double dx = 0.0, dy = 0.0;
	        if      (p.x() < xmin) dx = p.x() - xmin;
	        else if (p.x() > xmax) dx = p.x() - xmax;
	        if      (p.y() < ymin) dy = p.y() - ymin;
	        else if (p.y() > ymax) dy = p.y() - ymax;
	        return dx*dx + dy*dy;
	   }
	   public boolean equals(Object that) {
		   // does this rectangle equal that object? 
		   if (that == this) return true;
	        if (that == null) return false;
	        if (that.getClass() != this.getClass()) return false;
	        RectHV r = (RectHV) that;
	        if (this.xmin != r.xmin) return false;
	        if (this.ymin != r.ymin) return false;
	        if (this.xmax != r.xmax) return false;
	        if (this.ymax != r.ymax) return false;
	        return true;
	   }
	   public  String toString() {
		   // string representation 
		   return "[" + xmin + ", " + xmax + "] x [" + ymin + ", " + ymax + "]";
	   }
	 public void draw() {
        StdDraw.line(xmin, ymin, xmax, ymin);
        StdDraw.line(xmax, ymin, xmax, ymax);
        StdDraw.line(xmax, ymax, xmin, ymax);
        StdDraw.line(xmin, ymax, xmin, ymin);
    }
	}

