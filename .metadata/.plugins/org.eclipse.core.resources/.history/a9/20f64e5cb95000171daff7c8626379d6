package search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eishin
 */
@SuppressWarnings("serial")
public class Node implements Serializable {

    public String name;
    public Node parent;
    public List<Node> children = new ArrayList<Node>();

    public Node() {
    }

    //ノードに名前と親をもたせます。
    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
    }

    //子クラスを加えるメソッド
    public void add(Node child) {
        this.children.add(child);
    }

    //見出しや本文を見つけるメソッドです。
    public Node find(String oomidashi, String tyumidashi, String komidashi) {
        Node pre = new Node(null, null);
        boolean exits = false;
        if (oomidashi == null) {
            return pre;
        } else {
            for (int i = 0; i < this.children.size(); i++) {
                if (this.children.get(i).name.equals(oomidashi)) {
                    pre = this.children.get(i);
                    exits = true;
                }
            }

            if (exits == false) {
                return new Node(null, null);
            }
            exits = false;

            if (tyumidashi != null) {
                for (int j = 0; j < pre.children.size(); j++) {
                    if (pre.children.get(j).name.equals(tyumidashi)) {
                        pre = pre.children.get(j);
                        exits = true;
                    }
                }
                if (exits == false) {
                    return new Node(null, null);
                }
                exits = false;

                if (komidashi != null) {
                    for (int k = 0; k < pre.children.size(); k++) {
                        if (pre.children.get(k).name.equals(komidashi)) {
                            pre = pre.children.get(k);
                            exits = true;
                        }
                    }
                    if (exits == false) {
                        return new Node(null, null);
                    }
                    exits = false;
                }
            }
            return pre;
        }
    }
}
