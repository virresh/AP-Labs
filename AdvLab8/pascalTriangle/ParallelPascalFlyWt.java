package pascalTriangle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelPascalFlyWt extends RecursiveAction {

	private static final long serialVersionUID = -3106665138452717216L;
	private final long n,k;
	private long result;
	static ReentrantLock lock = new ReentrantLock();
	private static Map<String,ParallelPascalFlyWt> instances = new HashMap<String,ParallelPascalFlyWt>();
	private ParallelPascalFlyWt(long N, long K) {
		n = N;
		k = K;
		result = -1;
	}

	public long getResult() {
		return result;
	}

	public static ParallelPascalFlyWt getInstance(long a, long b) {
		String hString = a+","+b;
		ParallelPascalFlyWt ans = null;
		do {
			try {
				if(lock.tryLock(5,TimeUnit.MILLISECONDS)) {
					try {
						if(!instances.containsKey(hString)) {
							instances.put(hString, new ParallelPascalFlyWt(a,b));
						}
						ans = instances.get(hString);
					}finally {
						lock.unlock();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}

		}while(ans==null);
		return instances.get(hString);
	}

	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if (n == 0 || k == 0 || n == k ) {
			this.result = 1;
			return;
		}
		else if(result!=-1) {
			return;
		}

		ParallelPascalFlyWt left = ParallelPascalFlyWt.getInstance(n - 1, k - 1);
		ParallelPascalFlyWt right = ParallelPascalFlyWt.getInstance(n - 1, k);
		left.fork();
		right.compute();
		left.join();
		this.result = left.getResult() + right.getResult();

	}
}
