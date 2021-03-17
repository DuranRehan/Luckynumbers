package g56055.luckynumbers.model;

/**
 *
 * @author Duran Rehan g56055
 *
 * This class aims to test the implemented methods
 */
public class MainForTest {

    public static void main(String[] args) {
        Board b1 = new Board();
//        Position p1 = new Position(0, 3);
//        var t1 = new Tile(5);
//        b1.put(t1, p1);
//        System.out.println(" Size : " + b1.getSize());
//        System.out.println("getTile :  " + b1.getTile(p1));
//        Position p2 = new Position(3, 4);
//        System.out.println("isInside : " + b1.isInside(p2));
//        System.out.println("Board to String : " + b1.toString());
//        System.out.println(b1.isFull());
        var t1 = new Tile(1);
        var t2 = new Tile(2);
        var t3 = new Tile(3);
        var t4 = new Tile(5);
        b1.put(t1, new Position(0, 0));
        b1.put(t2, new Position(0, 1));
        b1.put(t3, new Position(0, 2));
        b1.put(t4, new Position(0, 3));
        System.out.println(b1.toString());
        var pos = new Position(0, 1);
        System.out.println(b1.canBePut(t4, pos));
    }
}
