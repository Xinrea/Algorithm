import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int N;
    private int openNum;
    private boolean[] openArray;
    public Percolation(int n){
        if(n <= 0)throw new IndexOutOfBoundsException();
        uf = new WeightedQuickUnionUF(n*n+2);
        openArray = new boolean[n*n];
        N = n;
        openNum = 0;
    }
    public void open(int row, int col){
        if(row-1 < 0||row-1 >= N)throw new IndexOutOfBoundsException();
        if(col-1 < 0||col-1 >= N)throw new IndexOutOfBoundsException();
        if(isOpen(row, col))return;
        if(row-1>0){
            if(isOpen(row-1, col))
                uf.union((row-1-1)*N+col-1,(row-1)*N+col-1);
        }
        else uf.union(N*N,(row-1)*N+col-1);
        if(row-1<N-1){
            if(isOpen(row+1, col))
                uf.union((row-1+1)*N+col-1,(row-1)*N+col-1);
        }
        else uf.union(N*N+1,(row-1)*N+col-1);
        if(col-1>0){
            if(isOpen(row, col-1))
                uf.union((row-1)*N+col-1-1,(row-1)*N+col-1);
        }
        if(col-1<N-1){
            if(isOpen(row, col+1))
                uf.union((row-1)*N+col+1-1,(row-1)*N+col-1);
        }
        openArray[(row-1)*N+col-1]=true;
        openNum++;
    }
    public boolean isOpen(int row, int col){
        if(row-1 < 0||row-1 >= N)throw new IndexOutOfBoundsException();
        if(col-1 < 0||col-1 >= N)throw new IndexOutOfBoundsException();
        return openArray[(row-1)*N+col-1];
    }
    public boolean isFull(int row, int col){
        if(row < 1||row > N)throw new IndexOutOfBoundsException();
        if(col < 1||col > N)throw new IndexOutOfBoundsException();
        return uf.connected((row-1)*N+col-1,N*N);
    }
    public int numberOfOpenSites(){
        return openNum;
    }
    public boolean percolates(){
        return uf.connected(N*N,N*N+1);
    }

    public static void main(String[] args){
        Percolation perco = new Percolation(20);
        while(!perco.percolates()){
            perco.open(StdRandom.uniform(20)+1, StdRandom.uniform(20)+1);
        }
        System.out.println(perco.numberOfOpenSites());
    }
}