package g56055.luckynumbers.model;

/**
 *
 * @author Duran Rehan g56055,
 *
 * This class aims to test the implemented methods
 */
public class MainForTest {

    public static void main(String[] args) {
        Board b1 = new Board();
        Position p1 = new Position(0, 0);
        var t1 = new Tile(5);
        b1.put(t1, p1);
        System.out.println(" Size : " + b1.getSize());
        System.out.println("getTile :  " + b1.getTile(p1));
        Position p2 = new Position(3, 4);
        System.out.println("isInside : " + b1.isInside(p2));
        System.out.println("Board to String : " + b1.toString());
        //System.out.println(b1.isFull());
    }
}
