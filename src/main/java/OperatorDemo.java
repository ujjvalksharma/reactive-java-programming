import reactor.core.publisher.Flux;
import util.Util;

public class OperatorDemo {
	
	/*
	 * Execution order of the pipeline is from bottom to too on request, but subscription happens from top to bottom
	 */
	public static void main(String[] args) {
		
		// what is double consumer?
		Flux.range(0, 20)
		.handle((num,synSink)->{
			
			if(num%2==0) {
				
				synSink.next(num);
			}else {
				synSink.complete();
				
			}
			
		})
		.doOnComplete(()->System.out.println("doOncomplete")) // more doOn methods are there too
		.subscribe(Util.getSubscriber());
	}

}
