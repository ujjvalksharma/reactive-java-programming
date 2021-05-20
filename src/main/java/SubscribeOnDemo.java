import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class SubscribeOnDemo {

	
	/**
	 * 
	 * boundedElastic() -- has 10X thread like 4 core cpu has 40 thread, while parallel has thread equal to CPU
	 * 
	 * If we have multiple subscribeOn, then the closest one to the job will do the tast
	 */
	public static void main(String[] args) {
		
		Flux<Object> flux=Flux.create(fluxSink->{
			
			printThreadMain("create");
			fluxSink.next(1);
		}).doOnNext(i->printThreadMain("next"+i));
		
		flux.doFirst(()->printThreadMain("first1"))
		.publishOn(Schedulers.parallel())
		.subscribeOn(Schedulers.boundedElastic())
		.doFirst(()->printThreadMain("first2"))
		.subscribe(v->printThreadMain("sub"+v));
	}
	private static void printThreadMain(String msg) {
		System.out.println(Thread.currentThread().getName()+"- msg: "+msg);
	}
	
}
