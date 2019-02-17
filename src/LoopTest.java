public class LoopTest {
    private static class Node {
        Node next;
    }
    public static void main(String[] args) {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();
        Node d = new Node();
        Node e = new Node();
        Node f = new Node();
        Node g = new Node();
        Node h = new Node();
        Node i = new Node();
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = d;

        if(hasLoop(a)){
            System.out.println("Цикл существует!");
        }
        else {
            System.out.println("Цикла не существует!");
        }
    }

    public static boolean hasLoop(Node first) {

        if(first == null)
            return false;

        Node cherepaha, krolik;
        cherepaha = krolik = first;

        while(true) {

            cherepaha = cherepaha.next; // черепаха совершает 1 прыжок

            if(krolik.next != null)
                krolik = krolik.next.next; // кролик совершает 2 прыжка
            else
                return false;

            if(cherepaha == null || krolik == null) // если черепаха и кролик попали в null - цикла нет
                return false;

            if(cherepaha == krolik) // если же они встретятся, то цикл есть!
                return true;
        }
    }
}
