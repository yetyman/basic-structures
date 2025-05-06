package structures.geometry;

import java.util.Objects;

public class Rectangle2D {
    public final double minX;
    public final double minY;
    public final double maxX;
    public final double maxY;
    public final double width;
    public final double height;
    public final double area;

    public Rectangle2D(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;

        width = maxX - minX;
        height = maxY - minY;
        area = width * height;
    }

    public boolean contains(double x, double y) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    public boolean contains(Point2D pt) {
        return contains(pt.x, pt.y);
    }

    public boolean contains(Rectangle2D r) {
        return contains(r.minX, r.minY) && contains(r.maxX, r.maxY);
    }

    public boolean intersects(Rectangle2D r) {
        // Check if one rectangle is completely to the left of the other
        if (this.maxX < r.minX || r.maxX < this.minX) {
            return false;
        }

        // Check if one rectangle is completely above the other
        if (this.maxY < r.minY || r.maxY < this.minY) {
            return false;
        }

        // If neither of the above conditions is true, the rectangles must intersect
        return true;
    }
    public boolean intersects(double minX, double minY, double maxX, double maxY) {
        // Check if one rectangle is completely to the left of the other
        if (this.maxX < minX || maxX < this.minX) {
            return false;
        }

        // Check if one rectangle is completely above the other
        if (this.maxY < minY || maxY < this.minY) {
            return false;
        }

        // If neither of the above conditions is true, the rectangles must intersect
        return true;
    }


    public boolean intersects(Rectangle2D r, double epsilon) {
        return contains(r.minX - epsilon, r.minY - epsilon) || contains(r.maxX + epsilon, r.maxY + epsilon) || r.contains(minX - epsilon, minY - epsilon) || r.contains(maxX + epsilon, maxY + epsilon);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rectangle2D) obj;
        return Double.doubleToLongBits(this.minX) == Double.doubleToLongBits(that.minX) &&
                Double.doubleToLongBits(this.minY) == Double.doubleToLongBits(that.minY) &&
                Double.doubleToLongBits(this.maxX) == Double.doubleToLongBits(that.maxX) &&
                Double.doubleToLongBits(this.maxY) == Double.doubleToLongBits(that.maxY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, minY, maxX, maxY);
    }

    @Override
    public String toString() {
        return "Rectangle2D[" +
                "minX=" + minX + ", " +
                "minY=" + minY + ", " +
                "maxX=" + maxX + ", " +
                "maxY=" + maxY + ']';
    }

}
