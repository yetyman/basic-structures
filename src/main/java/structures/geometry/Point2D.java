package structures.geometry;

import org.apache.commons.math3.util.FastMath;

import java.util.Objects;

public class Point2D {
    public final double x;
    public final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D add(double x, double y){
        return new Point2D(this.x+x, this.y+y);
    }
    public Point2D add(Point2D other){
        return new Point2D(this.x+other.x, this.y+other.y);
    }
    public Point2D subtract(double x, double y){
        return new Point2D(this.x-x, this.y-y);
    }
    public Point2D subtract(Point2D other){
        return new Point2D(this.x-other.x, this.y-other.y);
    }
    public Point2D multiply(double scalar){
        return new Point2D(this.x*scalar, this.y*scalar);
    }
    public Point2D divide(double scalar){
        return new Point2D(this.x/scalar, this.y/scalar);
    }
    public double dot(double ox, double oy){
        return x*ox+y*oy;
    }
    public Point2D normalize(){
        return divide(length());
    }
    public double length(){
        return FastMath.sqrt(lengthSquared());
    }
    public double lengthSquared(){
        return x*x+y*y;
    }
    public double angle(Point2D other){
        return FastMath.acos(dot(other)/(length()*other.length()));
    }

    private double dot(Point2D other) {
        return dot(other.x, other.y);
    }

    public double angle(double ox, double oy){
        return FastMath.acos(dot(ox, oy)/(length()*Math.sqrt(ox*ox+oy*oy)));
    }
    public Point2D lerp(Point2D other, double t){
        return add(other.subtract(this).multiply(t));
    }
    public Point2D lerp(double ox, double oy, double t){
        return add(ox-x, oy-y).multiply(t);
    }
    public Point2D slerp(Point2D other, double t){
        double angle = angle(other);
        return add(other.subtract(this).multiply(Math.sin(t*angle)/Math.sin(angle)));
    }
    public Point2D slerp(double ox, double oy, double t){
        double angle = angle(ox, oy);
        return add(ox-x, oy-y).multiply(Math.sin(t*angle)/Math.sin(angle));
    }
    public Point2D nlerp(Point2D other, double t){
        return lerp(other, t).normalize();
    }
    public Point2D nlerp(double ox, double oy, double t){
        return lerp(ox, oy, t).normalize();
    }
    public Point2D slerpUnclamped(Point2D other, double t){
        double angle = angle(other);
        return add(other.subtract(this).multiply(Math.sin(t*angle)/Math.sin(angle)));
    }
    public Point2D slerpUnclamped(double ox, double oy, double t){
        double angle = angle(ox, oy);
        return add(ox-x, oy-y).multiply(Math.sin(t*angle)/Math.sin(angle));
    }
    public Point2D nlerpUnclamped(Point2D other, double t){
        return slerpUnclamped(other, t).normalize();
    }
    public Point2D nlerpUnclamped(double ox, double oy, double t){
        return slerpUnclamped(ox, oy, t).normalize();
    }
    public Point2D reflect(Point2D normal){
        return subtract(normal.multiply(2).multiply(dot(normal)));
    }
    public Point2D reflect(double nx, double ny){
        return subtract(nx*2*dot(nx, ny), ny*2*dot(nx, ny));
    }
    public Point2D project(Point2D other){
        return other.multiply(dot(other)/other.lengthSquared());
    }
    public Point2D project(double ox, double oy){
        double scalar = dot(ox, oy)/Math.pow(ox*ox+oy*oy, 2);
        return new Point2D(ox*scalar, oy*scalar);
    }
    public Point2D reject(Point2D other){
        return subtract(project(other));
    }
    public Point2D reject(double ox, double oy){
        return subtract(project(ox, oy));
    }
    public Point2D midpoint(Point2D other){
        return lerp(other, 0.5);
    }
    public Point2D midpoint(double ox, double oy){
        return lerp(ox, oy, 0.5);
    }
    public Point2D barycentric(Point2D other, double t){
        return lerp(other, t);
    }
    public Point2D barycentric(double ox, double oy, double t){
        return lerp(ox, oy, t);
    }
    public double  cross(Point2D other){
        return x*other.y-y*other.x;
    }

    public double  cross(double ox, double oy){
        return x*oy-y*ox;
    }
    public double distance(Point2D other){
        return FastMath.sqrt(distanceSquared(other));
    }
    public double distance(double ox, double oy){
        return FastMath.sqrt(distanceSquared(ox, oy));
    }
    public double distanceSquared(Point2D other){
        return FastMath.pow(x-other.x, 2)+FastMath.pow(y-other.y, 2);
    }
    public double distanceSquared(double ox, double oy){
        return FastMath.pow(x-ox, 2)+FastMath.pow(y-oy, 2);
    }
    public Point2D rotate(double angle){
        double sin = FastMath.sin(angle);
        double cos = FastMath.cos(angle);
        return new Point2D(x*cos-y*sin, x*sin+y*cos);
    }
    public Point2D rotate(double angle, Point2D center){
        double sin = FastMath.sin(angle);
        double cos = FastMath.cos(angle);
        return new Point2D((x-center.x)*cos-(y-center.y)*sin+center.x, (x-center.x)*sin+(y-center.y)*cos+center.y);
    }
    public Point2D rotate(double angle, double cx, double cy){
        double sin = FastMath.sin(angle);
        double cos = FastMath.cos(angle);
        return new Point2D((x-cx)*cos-(y-cy)*sin+cx, (x-cx)*sin+(y-cy)*cos+cy);
    }
    public Point2D rotateAround(Point2D point, double angle){
        double sin = FastMath.sin(angle);
        double cos = FastMath.cos(angle);
        return new Point2D((x-point.x)*cos-(y-point.y)*sin+point.x, (x-point.x)*sin+(y-point.y)*cos+point.y);
    }
    public Point2D rotateAround(double px, double py, double angle){
        double sin = FastMath.sin(angle);
        double cos = FastMath.cos(angle);
        return new Point2D((x-px)*cos-(y-py)*sin+px, (x-px)*sin+(y-py)*cos+py);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Point2D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
                Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D[" +
                "x=" + x + ", " +
                "y=" + y + ']';
    }

}
