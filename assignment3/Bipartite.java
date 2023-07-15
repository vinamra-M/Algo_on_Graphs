import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        int colorArr[] = new int[adj.length];
        for (int i = 0; i < adj.length; ++i)
            colorArr[i] = -1;

        for (int i = 0; i < adj.length; i++)
            if (colorArr[i] == -1)
                if (isBipartiteUtil(adj, i, colorArr) == false)
                    return 0;

        return 1;
}

public static boolean isBipartiteUtil(ArrayList<Integer>[] adj, int src, int colorArr[])
    {
        colorArr[src] = 1;
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for(int v: adj[u]){
                if(v == u) return false;
            }
            
            for(int v: adj[u]){
                if(colorArr[v] == -1){
                    colorArr[v] = 1 - colorArr[u];
                    q.push(v);
                }
                else if(colorArr[v] == colorArr[u]) 
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}