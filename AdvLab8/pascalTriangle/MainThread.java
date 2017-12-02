package pascalTriangle;

import java.util.concurrent.ForkJoinPool;

public class MainThread {
	static long standard(long n, long k) {
		if (n == 0 || k == 0 || n == k) {
			return 1;
		}
		long left = standard(n - 1, k - 1);
		long right = standard(n - 1, k);
		return (left + right);
	}
	
	public static void nonFlyWt(int numThreads, long N, long K) {
		ForkJoinPool pool = new ForkJoinPool(numThreads);
		ParallelPascal task = new ParallelPascal(N,K);
		pool.invoke(task);
		System.out.println(task.getResult());
	}
	
	public static void flyWt(int numThreads,long N, long K) {
		ForkJoinPool pool = new ForkJoinPool(numThreads);
		ParallelPascalFlyWt task = ParallelPascalFlyWt.getInstance(N,K);
		pool.invoke(task);
		System.out.println(task.getResult());
	}
	
	static void test1() {
		long N = 2000, K = 5;
		long startTime = System.nanoTime();
		flyWt(1,N,K);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("flyWt 1 Thread - "+totalTime);
		
		long startTime2 = System.nanoTime();
		flyWt(2,N,K);
		long endTime2   = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		System.out.println("flyWt 2 Thread - "+totalTime2);
		
		long startTime3 = System.nanoTime();
		flyWt(3,N,K);
		long endTime3   = System.nanoTime();
		long totalTime3 = endTime3 - startTime3;
		System.out.println("flyWt 3 Thread - "+totalTime3);
	}
	
	static void test2() {
		long N=200, K=5;
		long startTime = System.nanoTime();
		nonFlyWt(1,N,K);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Non flyWt 1 Thread - "+totalTime);
		
		long startTime2 = System.nanoTime();
		nonFlyWt(2,N,K);
		long endTime2   = System.nanoTime();
		long totalTime2 = endTime2 - startTime2;
		System.out.println("Non flyWt 2 Thread - "+totalTime2);
		
		long startTime3 = System.nanoTime();
		nonFlyWt(3,N,K);
		long endTime3   = System.nanoTime();
		long totalTime3 = endTime3 - startTime3;
		System.out.println("Non flyWt 3 Thread - "+totalTime3);
	}
	
	static void test3() {
		long N=200, K=5;
		long startTime = System.nanoTime();
		long ans = standard(N,K);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(ans+"\nNon flyWt non parallelized - "+totalTime);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
		
//		System.out.println(ParallelPascalFlyWt.getInstance(200, 5).getResult());
	}

}
