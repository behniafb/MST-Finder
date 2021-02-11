/* این کد، یک گراف را از طریق یال هایش (وزن و دو سر یال) به عنوان ورودی می گیرد
 و کمترین (یا بیش ترین) درخت پوشا را به همین صورت به عنوان خروجی بر می گرداند.
  گراف ورودی حتماً باید همبند و وزن دار باشد!*/


import java.util.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<Edge> edges = new ArrayList<>(); // مجموعه ی تشکیل دهنده ی گراف ورودی
        ArrayList<Integer> verticesOfMST = new ArrayList<>(); // مجموعه ی رئوس درخت پوشا
        TreeSet<Edge> mst = new TreeSet<>(new EdgeComparator());// مجموعه یال های درخت پوشا

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of the graph's edges :");
        int numberOfEdges = sc.nextInt();
        System.out.println("Enter weight, starting vertex & ending vertex for (e1) :");

        // مقدار دهی مجموعه ی یال های گراف
        for (int i = 1; i <= numberOfEdges; i++) {
            int weight = sc.nextInt();
            int start = sc.nextInt();
            int end = sc.nextInt();
            Edge e = new Edge(weight, start, end);
            edges.add(e);
            System.out.println(i < numberOfEdges ? ("next (e" + (i + 1) + "):") : "");
        }

        edges.sort(new EdgeComparator());  /* مجموعه ی یال ها باید (به صورت صعودی) مرتب شوند
         تا بتوانیم (کمترین) درخت پوشا را بدست آوریم                                  */

        // کوچکترین یال را به درخت اضافه می کنیم
        mst.add(edges.get(0));
        verticesOfMST.add(edges.get(0).startVertex);
        verticesOfMST.add(edges.get(0).endVertex);

        // انتخاب بقیه ی یال ها در اینجا صورت می گیرد
        for (int i = 1; i <= numberOfEdges - 1 ; i++) {
            // این خط بررسی می کند که آیا رئوس انتهایی یال i در درخت وجود دارند یا نه؟
            if (verticesOfMST.contains(edges.get(i).startVertex) && verticesOfMST.contains(edges.get(i).endVertex))
                continue; // اگر وجود داشتند، این یال را کنار می گذارد
            else {
                mst.add(edges.get(i));
                verticesOfMST.add(edges.get(i).startVertex);
                verticesOfMST.add(edges.get(i).endVertex);
            }
        }

        // چاپ اطلاعات یال های درخت
        System.out.println("~~ Information of the Tree ~~");
        System.out.println("Weight\tEdge");
        for (Edge edge : mst) {
            System.out.println(edge.weight + "\t\t" + edge.startVertex + "" + edge.endVertex);
        }
    }
}


class Edge {
    int weight;
    int startVertex;
    int endVertex;

    public Edge(int weight, int startVertex, int endVertex) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
    }
}

// مقایسه کننده ی یال ها
class EdgeComparator implements Comparator<Edge> {
    @Override
    public int compare(Edge e1, Edge e2) {
        return e1.weight - e2.weight; /* اگر می خواهیم بیش ترین درخت پوشا رو بدست آوریم،
        فقط کافی است جای e1 و e2 رو با هم عوض کنیم.اکنون کمترین را می دهد.                                 */
    }
}

/*
    Author = BehniaFarahbod;
*/
