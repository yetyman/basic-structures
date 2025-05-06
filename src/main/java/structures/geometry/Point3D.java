package structures.geometry;

import org.apache.commons.math3.util.FastMath;

import java.util.Objects;

public class Point3D {
    public final double x;
    public final double y;
    public final double z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distance(Point3D other){
        return FastMath.sqrt(Math.pow(x-other.x,2)+Math.pow(y-other.y,2)+Math.pow(z-other.z,2));
    }

    public double distance(double ox, double oy, double oz){
        return FastMath.sqrt(Math.pow(x-ox, 2)+Math.pow(y-oy, 2)+Math.pow(z-oz, 2));
    }

    public Point3D add(Point3D other){
        return new Point3D(x+other.x, y+other.y, z+other.z);
    }
    public Point3D add(double ox, double oy, double oz){
        return new Point3D(x+ox, y+oy, z+oz);
    }

    public Point3D subtract(Point3D other){
        return new Point3D(x-other.x, y-other.y, z-other.z);
    }
    public Point3D subtract(double ox, double oy, double oz){
        return new Point3D(x-ox, y-oy, z-oz);
    }
    public Point3D multiply(double scalar){
        return new Point3D(x*scalar, y*scalar, z*scalar);
    }
    public Point3D divide(double scalar){
        return new Point3D(x/scalar, y/scalar, z/scalar);
    }
    public Point3D cross(Point3D other){
        return new Point3D(y*other.z-z*other.y, z*other.x-x*other.z, x*other.y-y*other.x);
    }
    public Point3D cross(double ox, double oy, double oz){
        return new Point3D(y*oz-z*oy, z*ox-x*oz, x*oy-y*ox);
    }
    public double dot(Point3D other){
        return x*other.x+y*other.y+z*other.z;
    }
    public double dot(double ox, double oy, double oz){
        return x*ox+y*oy+z*oz;
    }
    public Point3D normalize(){
        return divide(length());
    }
    public double length(){
        return FastMath.sqrt(lengthSquared());
    }
    public double lengthSquared(){
        return x*x+y*y+z*z;
    }
    public Point3D rotate(double angle, Point3D axis){
        double sinHalfAngle = FastMath.sin(angle/2);
        double cosHalfAngle = FastMath.cos(angle/2);

        double rx = axis.x * sinHalfAngle;
        double ry = axis.y * sinHalfAngle;
        double rz = axis.z * sinHalfAngle;
        double rw = cosHalfAngle;

        double x = this.x;
        double y = this.y;
        double z = this.z;

        double qx = rx * rw + rw * x + ry * z - rz * y;
        double qy = ry * rw + rw * y + rz * x - rx * z;
        double qz = rz * rw + rw * z + rx * y - ry * x;
        double qw = rw * rw - rx * x - ry * y - rz * z;

        return new Point3D(
                qx * rw + qw * -rx + qy * -rz - qz * -ry,
                qy * rw + qw * -ry + qz * -rx - qx * -rz,
                qz * rw + qw * -rz + qx * -ry - qy * -rx);
    }
    public Point3D rotate(double angle, double x, double y, double z){
        return rotate(angle, new Point3D(x,y,z));
    }
    public double angle(Point3D other){
        return FastMath.acos(dot(other)/(length()*other.length()));
    }
    public double angle(double ox, double oy, double oz){
        return FastMath.acos(dot(ox, oy, oz)/(length()*Math.sqrt(ox*ox+oy*oy+oz*oz)));
    }
    public Point3D lerp(Point3D other, double t){
        return add(other.subtract(this).multiply(t));
    }
    public Point3D lerp(double ox, double oy, double oz, double t){
        return add(ox-x, oy-y, oz-z).multiply(t);
    }
    public Point3D slerp(Point3D other, double t){
        double angle = angle(other);
        return add(other.subtract(this).multiply(Math.sin(t*angle)/Math.sin(angle)));
    }
    public Point3D slerp(double ox, double oy, double oz, double t){
        double angle = angle(ox, oy, oz);
        return add(ox-x, oy-y, oz-z).multiply(Math.sin(t*angle)/Math.sin(angle));
    }
    public Point3D nlerp(Point3D other, double t){
        return lerp(other, t).normalize();
    }
    public Point3D nlerp(double ox, double oy, double oz, double t){
        return lerp(ox, oy, oz, t).normalize();
    }
    public Point3D slerpUnclamped(Point3D other, double t){
        double angle = angle(other);
        return add(other.subtract(this).multiply(Math.sin(t*angle)/Math.sin(angle)));
    }
    public Point3D slerpUnclamped(double ox, double oy, double oz, double t){
        double angle = angle(ox, oy, oz);
        return add(ox-x, oy-y, oz-z).multiply(Math.sin(t*angle)/Math.sin(angle));
    }
    public Point3D nlerpUnclamped(Point3D other, double t){
        return slerpUnclamped(other, t).normalize();
    }
    public Point3D nlerpUnclamped(double ox, double oy, double oz, double t){
        return slerpUnclamped(ox, oy, oz, t).normalize();
    }
    public Point3D reflect(Point3D normal){
        return subtract(normal.multiply(2).multiply(dot(normal)));
    }
    public Point3D reflect(double nx, double ny, double nz){
        return subtract(nx*2*dot(nx, ny, nz), ny*2*dot(nx, ny, nz), nz*2*dot(nx, ny, nz));
    }
    public Point3D project(Point3D other){
        return other.multiply(dot(other)/other.lengthSquared());
    }
    public Point3D project(double ox, double oy, double oz){
        double scalar = dot(ox, oy, oz)/Math.pow(ox*ox+oy*oy+oz*oz, 2);
        return new Point3D(ox*scalar, oy*scalar, oz*scalar);
    }
    public Point3D reject(Point3D other){
        return subtract(project(other));
    }
    public Point3D reject(double ox, double oy, double oz){
        return subtract(project(ox, oy, oz));
    }
    public Point3D midpoint(Point3D other){
        return lerp(other, 0.5);
    }
    public Point3D midpoint(double ox, double oy, double oz){
        return lerp(ox, oy, oz, 0.5);
    }
    public Point3D barycentric(Point3D other, double t){
        return lerp(other, t);
    }
    public Point3D barycentric(double ox, double oy, double oz, double t){
        return lerp(ox, oy, oz, t);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Point3D) obj;
        return Double.doubleToLongBits(this.x) == Double.doubleToLongBits(that.x) &&
                Double.doubleToLongBits(this.y) == Double.doubleToLongBits(that.y) &&
                Double.doubleToLongBits(this.z) == Double.doubleToLongBits(that.z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Point3D[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ']';
    }

}
