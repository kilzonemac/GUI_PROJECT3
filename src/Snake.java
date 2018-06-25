import javafx.scene.shape.Rectangle;

public class Snake extends Rectangle {
    private int x;
    private int y;
    private int index;
    private static int index_stat=0;

    public Snake(int x, int y, int szer, int dl){
        super(szer,dl);
        this.x=x;
        this.y=y;
        index=index_stat;
        index_stat++;
    }

    public int getIndex() {
        return index;
    }

    public int getXc() {
        return x;
    }

    public int getYc() {
        return y;
    }
}
