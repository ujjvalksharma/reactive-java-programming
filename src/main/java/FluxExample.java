import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import util.Util;

public class FluxExample {

	public static void main(String[] args) {
		
		Flux<Integer> fluxInt=Flux.just(0,1,2,3)
				.filter(num->num>=0);
		fluxInt.subscribe(i-> System.out.println(i)
				, err->System.out.println(err)
				,()->System.out.println("completed"));
		
	// flux out of list
		
		List<Integer> listOfNum=Arrays.asList(1,2,3,4,5);
		
		Flux<Integer> fluxIntList=Flux.fromIterable(listOfNum);
		
		fluxIntList.subscribe(i-> System.out.println(i)
				, err->System.out.println(err)
				,()->System.out.println("completed"));
		
		// flux out of array
		
		
		String ArrayOfNum[]=new String[] {"1","2","3","4","5"};
		
		Flux<String> fluxIntArr=Flux.fromArray(ArrayOfNum); // works for string array
		
		fluxIntArr.subscribe(i-> System.out.println(i)
				, err->System.out.println(err)
				,()->System.out.println("completed"));
		
		// flux of stream
		
		List<Integer> listOfNum1=Arrays.asList(1,2,3,4,5);
		
		Stream<Integer> streamOfNum=listOfNum1.stream();
		
		Flux<Integer> fluxIntList1=Flux.fromStream(()->streamOfNum);// no error on calling same stream multiple times as we have created a reference to the stream
		
		// Flux<Integer> fluxIntList1=Flux.fromStream(()->streamOfNum); // error as stream can be used only once
		
		fluxIntList1.subscribe(Util.onNext(), Util.onError(),Util.onComplete());
		fluxIntList1.subscribe(Util.onNext(), Util.onError(),Util.onComplete()); 
		
		// flux using range
		
		Flux.range(0, 20)
		.subscribe(Util.onNext());
		
		// flux using log
		
	}
	
}
