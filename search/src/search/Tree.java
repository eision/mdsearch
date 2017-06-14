package search;

import java.util.List;

/**
 *
 * @author eishin
 */
public final class Tree {

    public static Node createTreeObject(List<String> treePathList) {

        Node root = new Node(null, null);

        for (String treePath : treePathList) {
            //treePathを/で区切る
            String[] nodeNameList = treePath.split("/");

            //木の根を定義する
            String parentNodeName = null;
            //深さ方向に構造木を作り出す
            for (String nodeName : nodeNameList) {

                if (nodeName == null || "".equals(nodeName)) {
                    continue;
                }

                if (parentNodeName == null) {
                    //木の根に対する操作
                    boolean exists = false;
                    for (Node child : root.children) {
                        if (nodeName.equals(child.name)) {
                            exists = true;
                        }
                    }
                    if (!exists) {
                        Node child = new Node(nodeName, root);
                        root.add(child);
                    }
                } else {
                    //親Nodeがある時に、子供を直下に追加する。 
                    appendNodeAsChild(parentNodeName, nodeName, root);
                }

                parentNodeName = nodeName;
            }
        }
        return root;
    }

    //parentNodeにnodeを子供として受け入れるメソッド
    private static void appendNodeAsChild(String parentNodeName, String nodeName, Node node) {
        //目的のparentNodeに来た時と来ていない時
        if (node.name != null && parentNodeName.equals(node.name)) {
            //Node#nameがnullならrootなのでここでは対象外
            boolean exists = false;
            //parentNodeが受け入れるchildが既にあったら追加しない
            for (Node child : node.children) {
                if (nodeName.equals(child.name)) {
                    exists = true;
                }
            }
            if (!exists) {
                Node child = new Node(nodeName, node);
                node.add(child);
            }

        } else {
            //目的のparentNodeではなかったので幅優先探索で他のnodeを探す
            for (Node child : node.children) {
                appendNodeAsChild(parentNodeName, nodeName, child);
            }
        }
    }
}

