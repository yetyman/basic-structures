package structures.directions;


import structures.geometry.Point2D;

public enum DirectionSquare {
    t, tr, r, br, b, bl, l, tl;


    public static DirectionSquare cplus(DirectionSquare dir, int add){
        var directions = DirectionSquare.values();
        return directions[(dir.ordinal() + add)%directions.length];
    }

    public static DirectionSquare opposite(DirectionSquare dir){
        return cplus(dir, 4);
    }
    public DirectionSquare opposite(){
        return opposite(this);
    }
    public DirectionSquare cplus(int add){
        return cplus(this, add);
    }

    public Point2D add(Point2D pt, double m) {
        double dx = 0;
        double dy = 0;
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
        }

        return new Point2D(dx, dy);
    }
}
