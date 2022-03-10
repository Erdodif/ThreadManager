package hu.petrik.crossroad;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position getAbove(){
        return new Position(this.x-1,this.y);
    }

    public Position getBelow(){
        return new Position(this.x+1,this.y);
    }

    public Position getToLeft(){
        return new Position(this.x,this.y-1);
    }

    public Position getToRight(){
        return new Position(this.x,this.y+1);
    }

    public Position getAbove(int height){
        if(x == 0){
            return new Position(height-1,this.y);
        }
        return new Position(this.x-1,this.y);
    }

    public Position getBelow(int height){
        if(x == height){
            return new Position(0,this.y);
        }
        return new Position(this.x+1,this.y);
    }

    public Position getToLeft(int width){
        if(y == 0){
            return new Position(this.x,width-1);
        }
        return new Position(this.x,this.y-1);
    }

    public Position getToRight(int width){
        if(y == width){
            return new Position(this.x,0);
        }
        return new Position(this.x,this.y+1);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
