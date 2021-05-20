import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import util.Util;

public class ResilientPipeline {

	public static void main(String[] args) {
	/*	
		Flux.range(0, 20)
		.log()
		.map(num->num/0)
	//	.onErrorReturn(-1) // return an integer value on error, pipeline stops
	//	.onErrorResume(e->fallBack()) // call a function on error, pipeline stops
		.onErrorContinue((err,obj)->{
			System.out.println("Error Ocurred:"+obj);
			
			//return Flux.just(-1); // no need to return anything, this function itself is a return
		})
		.subscribe(Util.getSubscriber());
		*/
	//	System.out.println("---------------------------------------");
		
		// time out
		getOrderId()
		.filter(i->i>1000)
		.defaultIfEmpty(10000) // if item is not present
		.timeout(Duration.ofSeconds(2),fallBack()) //if a service is taking to much time
		.subscribe(Util.getSubscriber());
		
		Util.sleepSeconds(6);
		
	}
	
	private static Mono<Integer> fallBack(){
		return Mono.just(-1);
	}
	
	
	private static Flux<Integer> getOrderId(){
		return Flux.range(0, 20)
				.delayElements(Duration.ofSeconds(5));
	}
}
