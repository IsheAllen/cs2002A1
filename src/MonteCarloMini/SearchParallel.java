package MonteCarloMini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class SearchParallel extends RecursiveTask<ArrayList<Integer>> {
    int start, stop;
    final int CUTOFF = 20000;
    Search [] searches;

    public SearchParallel(int startingPoint, int endPoint, Search [] searches) {
        this.start = startingPoint;
        this.stop = endPoint;
        this.searches = searches;
    }

    public ArrayList<Integer> minTwo(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        if (list1.get(0) < list2.get(0)) return list1;
        return list2;
    } 

    public ArrayList<Integer> compute() {
        if (stop - start < CUTOFF) {
            //all searches
    	int min=Integer.MAX_VALUE;
    	int local_min=Integer.MAX_VALUE;
    	int finder =-1;
            for (int i = start; i < stop; i++) {
                local_min = searches[i].find_valleys();
                if ((!searches[i].isStopped()) && (local_min < min)) { // don't look at those who stopped because hit
                                                                       // exisiting path
                    min = local_min;
                    finder = i; // keep track of who found it
                }
            }
            return new ArrayList<Integer>(Arrays.asList(min, finder));
        }
        int mid = (stop - start) / 2;
        SearchParallel searching1 = new SearchParallel(start, mid, searches);
        searching1.fork();
        SearchParallel searching2 = new SearchParallel(mid, stop, searches);
        searching2.fork();
        
        ArrayList<Integer> result1 = searching1.join();
        ArrayList<Integer> result2 = searching2.join();
        return minTwo(result1, result2);
    }

}