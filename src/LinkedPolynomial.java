import java.util.LinkedList;

public class LinkedPolynomial {
    private Node first = new Node(0, 0);  // sentinel
    private Node last  = first;

    private static class Node {
        int coef;
        int exp;
        Node next;
        Node(int coef, int exp) {
            this.coef = coef;
            this.exp  = exp;
        }
    }

    // 0    
    private LinkedPolynomial() { }

    // a * x^b
    public LinkedPolynomial(int coef, int exp) {
        last.next = new Node(coef, exp);
        last = last.next;
    }

    // return c = a + b
    public LinkedPolynomial plus(LinkedPolynomial b) {
        LinkedPolynomial a = this;
        LinkedPolynomial c = new LinkedPolynomial();
        Node x = a.first.next;
        Node y = b.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     { t = new Node(y.coef, y.exp);  y = y.next; }
            else if (y == null)     { t = new Node(x.coef, x.exp);  x = x.next; }
            else if (x.exp > y.exp) { t = new Node(x.coef, x.exp);  x = x.next; }
            else if (x.exp < y.exp) { t = new Node(y.coef, y.exp);  y = y.next; }

            else {
                int coef = x.coef + y.coef;
                int exp  = x.exp;
                x = x.next;
                y = y.next;
                if (coef == 0) continue;
                t = new Node(coef, exp);
            }

            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }


    // return c = a + b
    public LinkedPolynomial times(LinkedPolynomial b) {
        LinkedPolynomial a = this;
        LinkedPolynomial c = new LinkedPolynomial();
        for (Node x = a.first.next; x!= null; x = x.next) {
            LinkedPolynomial temp = new LinkedPolynomial();
            for (Node y = b.first.next; y!= null; y = y.next) {
                temp.last.next = new Node(x.coef * y.coef, x.exp + y.exp);
                temp.last = temp.last.next;
            }
            c = c.plus(temp);
        }
        return c;
    }


    // convert to string representation
    public String toString() {
        String s = "";
        for (Node x = first.next; x != null; x = x.next) {
            if      (x.coef > 0) s = s + " + " +   x.coef  + "x^" + x.exp;
            else if (x.coef < 0) s = s + " - " + (-x.coef) + "x^" + x.exp;
        }
        return s;
    }

    // test client
    public static void main(String[] args) {
        LinkedPolynomial zero = new LinkedPolynomial(0, 0);

        LinkedPolynomial p1   = new LinkedPolynomial(4, 3);
        LinkedPolynomial p2   = new LinkedPolynomial(3, 2);
        LinkedPolynomial p3   = new LinkedPolynomial(1, 0);
        LinkedPolynomial p4   = new LinkedPolynomial(2, 1);
        LinkedPolynomial p    = p1.plus(p2).plus(p3).plus(p4);   // 4x^3 + 3x^2 + 1

        LinkedPolynomial q1   = new LinkedPolynomial(3, 2);
        LinkedPolynomial q2   = new LinkedPolynomial(5, 0);
        LinkedPolynomial q    = q1.plus(q2);                     // 3x^2 + 5

        LinkedPolynomial r    = p.plus(q);
        LinkedPolynomial s    = p.times(q);
        System.out.println("zero(x) =     " + zero);
        System.out.println("p(x) =        " + p);
        System.out.println("q(x) =        " + q);
        System.out.println("p(x) + q(x) = " + r);
        System.out.println("p(x) * q(x) = " + s);
//        StdOut.println("0 - p(x)    = " + zero.minus(p));
//        StdOut.println("p(3)        = " + p.evaluate(3));
//        StdOut.println("p'(x)       = " + p.differentiate());
//        StdOut.println("p''(x)      = " + p.differentiate().differentiate());
    }

}