public class Mnogochlen {
    private Node first = new Node(0, 0);
    private Node last  = first;

    private static class Node {
        int coef;
        int power;
        Node next;
        Node(int coef, int power) {
            this.coef = coef;
            this.power  = power;
        }
    }

    private Mnogochlen() { }

    // a * x^b
    public Mnogochlen(int coef, int power) {
        last.next = new Node(coef, power);
        last = last.next;
    }

    // y = a + b
    public Mnogochlen slojenie(Mnogochlen b) {
        Mnogochlen a = this;
        Mnogochlen c = new Mnogochlen();
        Node x = a.first.next;
        Node y = b.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     { t = new Node(y.coef, y.power);  y = y.next; }
            else if (y == null)     { t = new Node(x.coef, x.power);  x = x.next; }
            else if (x.power > y.power) { t = new Node(x.coef, x.power);  x = x.next; }
            else if (x.power < y.power) { t = new Node(y.coef, y.power);  y = y.next; }

            else {
                int coef = x.coef + y.coef;
                int power  = x.power;
                x = x.next;
                y = y.next;
                if (coef == 0) continue;
                t = new Node(coef, power);
            }

            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }


    // y = a * b
    public Mnogochlen umnojenie(Mnogochlen b) {
        Mnogochlen a = this;
        Mnogochlen c = new Mnogochlen();
        for (Node x = a.first.next; x!= null; x = x.next) {
            Mnogochlen temp = new Mnogochlen();
            for (Node y = b.first.next; y!= null; y = y.next) {
                temp.last.next = new Node(x.coef * y.coef, x.power + y.power);
                temp.last = temp.last.next;
            }
            c = c.slojenie(temp);
        }
        return c;
    }


    public String toString() {
        String s = "";
        for (Node x = first.next; x != null; x = x.next) {
            if      (x.coef > 0) s = s + " + " +   x.coef  + "x^" + x.power;
            else if (x.coef < 0) s = s + " - " + (-x.coef) + "x^" + x.power;
        }
        return s;
    }


    public static void main(String[] args) {

        Mnogochlen f1   = new Mnogochlen(5, 2);
        Mnogochlen f2   = new Mnogochlen(4, 1);
        Mnogochlen f3   = new Mnogochlen(2, 0);
        Mnogochlen f    = f1.slojenie(f2).slojenie(f3);   // 5x^2 + 4x^1 + 2x^0

        Mnogochlen y1   = new Mnogochlen(5, 1);
        Mnogochlen y2   = new Mnogochlen(5, 0);
        Mnogochlen y    = y1.slojenie(y2);                // 5x^1 + 5x^0

        Mnogochlen j    = f.slojenie(y);
        Mnogochlen i    = f.umnojenie(y);
        System.out.println("Значения по условию:");
        System.out.println("f(x) = " + f);
        System.out.println("y(x) = " + y);
        System.out.println();
        System.out.println("Результат сложения:");
        System.out.println("f(x) + y(x) = " + j);
        System.out.println();
        System.out.println("Результат умножения:");
        System.out.println("f(x) * y(x) = " + i);
    }
}