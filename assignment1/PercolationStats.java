import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation perco;
    private double mean;
    private double s;
    private int t;
    public PercolationStats(int n, int trials){

        double[] x = new double[trials];
        for(int i = 0; i < trials; ++i){
            perco = new Percolation(n);
            while(!perco.percolates()){ // when not percolate
                perco.open(StdRandom.uniform(n)+1, StdRandom.uniform(n)+1);
            }
            x[i] = (double)perco.numberOfOpenSites()/(n*n);
        }
        mean = StdStats.mean(x);
        s = StdStats.stddev(x);
        t = trials;

    }    // perform trials independent experiments on an n-by-n grid
    public double mean(){
        return mean;
    }
    public double stddev(){
        return s;
    }
    public double confidenceLo(){
        return mean-1.96*s/Math.sqrt(t);
    }
    public double confidenceHi(){
        return mean+1.96*s/Math.sqrt(t);
    }
 
    public static void main(String[] args){
        PercolationStats percoStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("mean\t= %1$f\n", percoStats.mean());
        System.out.printf("stddev\t= %1$f\n", percoStats.stddev());
        System.out.printf("95%% confidence interval\t= [%1$f, %2$f]\n", percoStats.confidenceLo(),percoStats.confidenceHi());
    }
 }