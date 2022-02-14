/*
 * Name: Gaoying Wang
 * PID:  A16131629
 */

import sun.awt.image.ImageWatched;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Search Engine implementation.
 * 
 * @author Gaoying Wang
 * @since  ${2022-0211}
 */
public class SearchEngine {

    /**
     * Populate BSTrees from a file
     * 
     * @param movieTree  - BST to be populated with actors
     * @param studioTree - BST to be populated with studios
     * @param ratingTree - BST to be populated with ratings
     * @param fileName   - name of the input file
     * @returns false if file not found, true otherwise
     */
    public static boolean populateSearchTrees(
            BSTree<String> movieTree, BSTree<String> studioTree,
            BSTree<String> ratingTree, String fileName
    ) {
        // open and read file
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                // read 5 lines per batch:
                // movie, cast, studios, rating, trailing hyphen
                String movie = scanner.nextLine().trim();
                String cast[] = scanner.nextLine().split(" ");
                String studios[] = scanner.nextLine().split(" ");
                String rating = scanner.nextLine().trim();
                scanner.nextLine();

                for (int i = 0; i < cast.length; i++){

                    movieTree.insert(cast[i].toLowerCase());
                    if(movieTree.findDataList(cast[i].toLowerCase()).isEmpty()){
                        movieTree.insertData(cast[i].toLowerCase(), movie.toLowerCase());
                    }else {
                        if (!movieTree.findDataList(cast[i].toLowerCase()).contains(movie.toLowerCase())) {
                            movieTree.insertData(cast[i].toLowerCase(), movie.toLowerCase());
                        }
                    }
                    ratingTree.insert(cast[i].toLowerCase());
                    if (!ratingTree.findDataList(cast[i].toLowerCase()).contains(rating)) {
                        ratingTree.insertData(cast[i].toLowerCase(), rating);
                    }
                }
                for (int n = 0; n < studios.length; n++){
                    studioTree.insert(studios[n].toLowerCase());
                    studioTree.insertData(studios[n].toLowerCase(), movie.toLowerCase());
                }


            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Search a query in a BST
     *
     * @param searchTree - BST to be searched
     * @param query      - query string
     */
    public static void searchMyQuery(BSTree<String> searchTree, String query) {

        // process query
        String[] keys = query.toLowerCase().split(" ");

        // search and output intersection results
        // hint: list's addAll() and retainAll() methods could be helpful
       if (keys.length>=2){
           LinkedList<String> result=new LinkedList<String>();
           result.addAll(searchTree.findDataList(keys[0]));
           for (int i=1;i<keys.length;i++){
               result.retainAll(searchTree.findDataList(keys[i]));
           }
           SearchEngine.print(query,result);
           LinkedList<String> indresult= new LinkedList<String>();
           for (int i=0;i<keys.length;i++){
               indresult.addAll(searchTree.findDataList(keys[i]));
               for (int j=0;j<result.size();j++){
                   indresult.remove(result.get(j));
               }
               if (indresult.size()>0){
                   SearchEngine.print(keys[i].toLowerCase(),indresult);
               }
               indresult.clear();
           }
       }

        // search and output individual results
        // hint: list's addAll() and removeAll() methods could be helpful
        else{
           LinkedList<String> result=new LinkedList<String>();
           result.addAll(searchTree.findDataList(keys[0]));
           SearchEngine.print(query,result);
       }
    }

    /**
     * Print output of query
     * 
     * @param query     Query used to search tree
     * @param documents Output of documents from query
     */
    public static void print(String query, LinkedList<String> documents) {
        if (documents == null || documents.isEmpty())
            System.out.println("The search yielded no results for " + query);
        else {
            Object[] converted = documents.toArray();
            Arrays.sort(converted);
            System.out.println("Documents related to " + query
                    + " are: " + Arrays.toString(converted));
        }
    }

    /**
     * Main method that processes and query the given arguments
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {

        BSTree movie=new BSTree();
        BSTree rating=new BSTree();
        BSTree studio=new BSTree();
        // initialize search trees

        // process command line arguments
        String fileName = args[0];
        int searchKind = Integer.parseInt(args[1]);

        // populate search trees
        populateSearchTrees(movie,studio,rating,fileName);
        // choose the right tree to query
        String input = null;
        for (int i=2;i < args.length; i++){
            input=input+args[i]+" ";
        }
        input=input.substring(4,input.length()-1);
        if (args[1].compareTo("0")==0){
            SearchEngine.searchMyQuery(movie,input);
        } else if (args[1].compareTo("1")==0){
            SearchEngine.searchMyQuery(studio,input);
        } else if (args[1].compareTo("2")==0){
            SearchEngine.searchMyQuery(rating,input);
        }
    }
}
