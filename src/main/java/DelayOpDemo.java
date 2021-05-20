import java.time.Duration;

import reactor.core.publisher.Flux;
import util.Util;

public class DelayOpDemo {

	public static void main(String[] args) {
		
		Flux.range(0, 100)
		.log()
		.delayElements(Duration.ofSeconds(1)) // what does this do? by default it will request for 32 elements, which can be changed at the system
		.subscribe(Util.getSubscriber());
	}
}
