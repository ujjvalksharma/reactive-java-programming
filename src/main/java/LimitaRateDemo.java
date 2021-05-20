import reactor.core.publisher.Flux;
import util.Util;

public class LimitaRateDemo {

	public static void main(String[] args) {
		
		Flux.range(0, 100)
		.log()
		.limitRate(10,70) // send request to pipline when 70% of the data is empty in pipeline
		.subscribe(Util.getSubscriber());
	}
}
