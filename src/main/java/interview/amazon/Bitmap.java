package interview.amazon;
// Imagine you are tasked with writing a bitmap editing application. The first you need to write
// is the bitmap component itself.
// bitmap: 2d image grid, where each point on the grid has a given color
// getColor method, and a setColor method
// implement a method called Flood Fill. takes in a position, and a color, and paints the
// given position of this color, and all adjacent positions until it reaches a different color

public class Bitmap {
    private int w;
    private int h;
    private RBGColor[][] rbgColor;//solution 1
    //private Map<RBGColor, List<Grid>> = new HashMap<>();
    public Bitmap (int w, int h){
        this.w = w;
        this.h = h;
        rbgColor = new RBGColor[w][h];
    }

    public RBGColor getColor(int i, int j) throws Exception {
        if(invalidGrid(i, j) ) {
            throw new Exception("Invalid grid!");
        }
        return rbgColor[i][j];
    }
    public void setColor(int i, int j, RBGColor rbgColor) throws Exception {
        if(invalidGrid(i, j) ) {
            throw new Exception("Invalid grid!");
        }
        this.rbgColor[i][j] = rbgColor;
    }

    private boolean invalidGrid(int i, int j) {
        return i < 0 || i >= w || j < 0 || j >= h;
    }

    public void paint(int i, int j, RBGColor newColor) throws Exception {
        RBGColor rbgColor = getColor(i,j);
        if(!invalidGrid(i, j) && !rbgColor.equals(newColor)) {
            paint(i, j, rbgColor, newColor);
        }
    }

    public void paint(int i, int j, RBGColor originalColor, RBGColor newColor) throws Exception {
        if(!invalidGrid(i, j) && getColor(i,j).equals(originalColor)) {
            setColor(i, j, newColor);
            paint(i-1, j, originalColor, newColor);
            paint(i+1, j, originalColor, newColor);
            paint(i, j-1, originalColor, newColor);
            paint(i, j+1, originalColor, newColor);
        }

    }

}

class RBGColor {
    int red, green, blue;
    //constructor with 3 eles
}

class Grid {
    private int i;
    private int j;
}