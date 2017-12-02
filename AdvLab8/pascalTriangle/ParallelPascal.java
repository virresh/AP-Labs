package pascalTriangle;

import java.util.concurrent.RecursiveAction;

public class ParallelPascal extends RecursiveAction {
	
	private static final long serialVersionUID = 2892201041359481917L;
	long n,k,result;
	public ParallelPascal(long N, long K) {n=N; k=K;}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if (n == 0 || k == 0 || n == k) {
			this.result = 1;
			return;
		}
		
		ParallelPascal left = new ParallelPascal(n - 1, k - 1);
		ParallelPascal right = new ParallelPascal(n - 1, k);
		left.fork();
		right.compute();
		left.join();
		this.result = left.getResult() + right.getResult();
	}
	
	public long getResult() {
		return result;
	}

}
