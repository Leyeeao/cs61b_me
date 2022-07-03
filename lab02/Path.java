/** A class that represents a path via pursuit curves. */
public class Path {

    // TODO
    public Point curr;
    public Point next;
    public Path(double x, double y){
        curr = new Point(0.0,0.0);
        next = new Point(x, y);

    }
    public double getCurrX() {
        return this.curr.getX();
    }
    public double getCurrY() {
        return this.curr.getY();
    }

    public double getNextX() {
        return this.next.getX();
    }

    public double getNextY() {
        return this.next.getY();
    }

    public Point getCurrentPoint(){
        return this.curr;
    }

    public void setCurrentPoint(Point point) {
        this.curr.setX(point.getX());
        this.curr.setY(point.getY());
    }

    public void iterate(double dx, double dy) {
        this.setCurrentPoint(next);
        this.next.setX(this.curr.getX() + dx);
        this.next.setY(this.curr.getY() + dy);
    }
}
