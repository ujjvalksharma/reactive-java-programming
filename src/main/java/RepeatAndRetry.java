import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.util.retry.RetrySpec;
import util.Util;

public class RepeatAndRetry {
	
	public static void main(String args[]) {
		
		getIntegers()
		//.repeat(2) // 2 more times get data from publisher
		.repeat(2, ()->1==1) //repeat on the basis of condition (true/false)
		.retry(2) // retry two times on error
		.retryWhen(RetrySpec.fixedDelay(2, Duration.ofMillis(1)))
		.subscribe(Util.getSubscriber());
		
		Util.sleepSeconds(10);
		
	}
	
	public static Flux<Integer> getIntegers(){
		return Flux.range(1, 3)
				.doOnSubscribe((s)->System.out.println("--subscribed"))
				.doOnComplete(()->System.out.println("--completed"));
	}

}
