import java.util.function.Supplier;

import reactor.core.publisher.Mono;
import util.Util;

public class MonoFromSuplier {

	public static void main(String[] args) {
		
		// use Mono.just if we know out data value is fixed and
		//not dependent on a particular service or database
		
		// getFirstName is invoked even if there is no subscriber to that publisher
		Mono<String> mono1=Mono.just(getFirstName()); 
		
		//fromSupplier takes empty function argument and perform the task that we tell it to.
		Supplier<String> getNameSupplier=()->getFirstName();
		// getFirstName will be called when there is a subscriber (lazy)
		Mono<String> mono2=Mono.fromSupplier(getNameSupplier);
		
		//System.out.println(mono1);
		
	}

	private static String getFirstName() {
		
		System.out.println("getFirstName is called..");
		return Util.getFacker().name().fullName();
	}
}
