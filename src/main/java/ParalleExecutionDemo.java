import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ParalleExecutionDemo {

	/**
	 * 
	 * parallel give paralle flux for paralle process and we can make sequetional by .sequentional()
	 */
	public static void main(String[] args) {
		
		List<Integer> nonThreadSafeList=new ArrayList<>();
		
		Flux.range(0, 1000)
		.parallel(2) // number of threads what we want for the job
		.runOn(Schedulers.boundedElastic())
		//.sequential() // makes it sequentional instead of parallel
		.subscribe(i->nonThreadSafeList.add(i));
		
		System.out.println(nonThreadSafeList.size()); // size is not 1000 as this is an asyn job order cannot be determined
		System.out.println(nonThreadSafeList);
		
		
	}
}
