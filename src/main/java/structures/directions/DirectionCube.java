package structures.directions;

import structures.geometry.Point3D;

public enum DirectionCube {
    f, ft, ftr, fr, fbr, fb, fbl, fl, ftl,
    t, tr, r, br, tl, l, bl, b,
    ntl, nl, nbl, nb, nbr, nr, ntr, nt, n;

    public static DirectionCube opposite(DirectionCube dir){
        var directions = DirectionCube.values();
        return directions[directions.length - dir.ordinal()-1];
    }
    public DirectionCube opposite(){
        return opposite(this);
    }

    public Point3D add(Point3D pt, double m) {
        double dx = 0;
        double dy = 0;
        double dz = 0;
        switch (this){

            case t:
                dy = m;
                break;
            case tr:
                dy = m;
                dx = m;
                break;
            case r:
                dx = m;
                break;
            case br:
                dx = m;
                dy = -m;
                break;
            case b:
                dy = -m;
                break;
            case bl:
                dx = -m;
                dy = -m;
                break;
            case l:
                dx = -m;
                break;
            case tl:
                dx = -m;
                dy = m;
                break;

            case ft:
                dy = m;
                dz = 1;
                break;
            case ftr:
                dy = m;
                dx = m;
                dz = 1;
                break;
            case fr:
                dx = m;
                dz = 1;
                break;
            case fbr:
                dx = m;
                dy = -m;
                dz = 1;
                break;
            case fb:
                dy = -m;
                dz = 1;
                break;
            case fbl:
                dx = -m;
                dy = -m;
                dz = 1;
                break;
            case fl:
                dx = -m;
                dz = 1;
                break;
            case ftl:
                dx = -m;
                dy = m;
                dz = 1;
                break;

            case nt:
                dy = m;
                dz = -1;
                break;
            case ntr:
                dy = m;
                dx = m;
                dz = -1;
                break;
            case nr:
                dx = m;
                dz = -1;
                break;
            case nbr:
                dx = m;
                dy = -m;
                dz = -1;
                break;
            case nb:
                dy = -m;
                dz = -1;
                break;
            case nbl:
                dx = -m;
                dy = -m;
                dz = -1;
                break;
            case nl:
                dx = -m;
                dz = -1;
                break;
            case ntl:
                dx = -m;
                dy = m;
                dz = -1;
                break;

        }

        return new Point3D(dx, dy, dz);
    }
}
