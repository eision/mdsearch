package search;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author eishin
 */
public class search {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //初期化
        List<String> treePathList = new ArrayList<String>();
        String oomidashi = "";
        String tyumidashi = "";
        String komidashi = "";       
        Node finder = new Node(null, null);
        int len = args.length;
       
        //検索するファイルの読み取りと構文木の作成
        try {
            File file = new File("src/search/portfolio.md");
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String str = scan.nextLine();
                if(str.length() > 2){
                if (str.charAt(2) == '#') {
                    //小見出し
                    komidashi = str.substring(4);
                    treePathList.add("/" + oomidashi + "/" + tyumidashi + "/" + komidashi);
                } else if (str.charAt(1) == '#') {
                    //中見出し
                    tyumidashi = str.substring(3);
                    treePathList.add("/" + oomidashi + "/" + tyumidashi);
                } else if (str.charAt(0) == '#') {
                    //大見出し
                    oomidashi = str.substring(2);
                    treePathList.add("/" + oomidashi);
                } else {
                    //本文
                    treePathList.add("/" + oomidashi + "/" + tyumidashi + "/" + komidashi + "/" + str);
                }
                } else {
                if(!str.equals("/t")){
                	treePathList.add("/" + oomidashi + "/" + tyumidashi + "/" + komidashi + "/" + str);
                }
                }
            }
            //木を作成したものがroot
            Node root = Tree.createTreeObject(treePathList);
            
            //検索（argsの配列の長さによって引数を変える）
            if(3 <= len){
            finder = root.find(args[0],args[1],args[2]);
            } else if(len == 2){
            finder = root.find(args[0],args[1],null);
            } else if(len == 1){ 
            finder = root.find(args[0],null,null);
            } else { 
            finder = root.find(null,null,null);
            }
            
            //検索結果を出力
            for(int k=0;k<finder.children.size();k++){
            System.out.println(finder.children.get(k).name);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } 
    }

}
