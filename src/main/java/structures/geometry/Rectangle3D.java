package structures.geometry;

import java.util.Objects;

public class Rectangle3D {
    public final double minX;
    public final double minY;
    public final double minZ;
    public final double maxX;
    public final double maxY;
    public final double maxZ;
    public final double width;
    public final double height;
    public final double depth;
    public final double volume;

    public Rectangle3D(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;

        width = maxX - minX;
        height = maxY - minY;
        depth = maxZ - minZ;
        volume = width * height * depth;
    }
    public Rectangle3D(Rectangle3D r){
        this(r.minX, r.minY, r.minZ, r.maxX, r.maxY, r.maxZ);
    }

    public boolean  contains(Point3D p) {
        return (p.x >= minX && p.x <= maxX) &&
                (p.y >= minY && p.y <= maxY) &&
                (p.z >= minZ && p.z <= maxZ);
    }
    public boolean  contains(Rectangle3D r) {
        return (r.minX >= minX && r.maxX <= maxX) &&
                (r.minY >= minY && r.maxY <= maxY) &&
                (r.minZ >= minZ && r.maxZ <= maxZ);
    }
    public boolean  intersects(Rectangle3D r) {
        return (r.minX <= maxX && r.maxX >= minX) &&
                (r.minY <= maxY && r.maxY >= minY) &&
                (r.minZ <= maxZ && r.maxZ >= minZ);
    }
    public Rectangle3D intersection(Rectangle3D r) {
        double minX = Math.max(this.minX, r.minX);
        double minY = Math.max(this.minY, r.minY);
        double minZ = Math.max(this.minZ, r.minZ);
        double maxX = Math.min(this.maxX, r.maxX);
        double maxY = Math.min(this.maxY, r.maxY);
        double maxZ = Math.min(this.maxZ, r.maxZ);
        return new Rectangle3D(minX, minY, minZ, maxX, maxY, maxZ);
    }
    public Rectangle3D union(Rectangle3D r) {
        double minX = Math.min(this.minX, r.minX);
        double minY = Math.min(this.minY, r.minY);
        double minZ = Math.min(this.minZ, r.minZ);
        double maxX = Math.max(this.maxX, r.maxX);
        double maxY = Math.max(this.maxY, r.maxY);
        double maxZ = Math.max(this.maxZ, r.maxZ);
        return new Rectangle3D(minX, minY, minZ, maxX, maxY, maxZ);
    }
    public Rectangle3D inflate(double amount) {
        return new Rectangle3D(minX - amount, minY - amount, minZ - amount, maxX + amount, maxY + amount, maxZ + amount);
    }
    public Rectangle3D deflate(double amount) {
        return new Rectangle3D(minX + amount, minY + amount, minZ + amount, maxX - amount, maxY - amount, maxZ - amount);
    }
    public Rectangle3D translate(double dx, double dy, double dz) {
        return new Rectangle3D(minX + dx, minY + dy, minZ + dz, maxX + dx, maxY + dy, maxZ + dz);
    }
    public Rectangle3D scale(double sx, double sy, double sz) {
        return new Rectangle3D(minX * sx, minY * sy, minZ * sz, maxX * sx, maxY * sy, maxZ * sz);
    }
    public Rectangle3D scale(double s) {
        return new Rectangle3D(minX * s, minY * s, minZ * s, maxX * s, maxY * s, maxZ * s);
    }
    public Rectangle3D scale(double sx, double sy, double sz, Point3D center) {
        return new Rectangle3D(minX * sx + center.x * (1 - sx), minY * sy + center.y * (1 - sy), minZ * sz + center.z * (1 - sz), maxX * sx + center.x * (1 - sx), maxY * sy + center.y * (1 - sy), maxZ * sz + center.z * (1 - sz));
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Rectangle3D) obj;
        return Double.doubleToLongBits(this.minX) == Double.doubleToLongBits(that.minX) &&
                Double.doubleToLongBits(this.minY) == Double.doubleToLongBits(that.minY) &&
                Double.doubleToLongBits(this.minZ) == Double.doubleToLongBits(that.minZ) &&
                Double.doubleToLongBits(this.maxX) == Double.doubleToLongBits(that.maxX) &&
                Double.doubleToLongBits(this.maxY) == Double.doubleToLongBits(that.maxY) &&
                Double.doubleToLongBits(this.maxZ) == Double.doubleToLongBits(that.maxZ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @Override
    public String toString() {
        return "Rectangle3D[" +
                "minX=" + minX + ", " +
                "minY=" + minY + ", " +
                "minZ=" + minZ + ", " +
                "maxX=" + maxX + ", " +
                "maxY=" + maxY + ", " +
                "maxZ=" + maxZ + ']';
    }

}
