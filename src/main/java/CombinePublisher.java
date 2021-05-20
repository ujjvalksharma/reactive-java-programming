import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import util.Util;

public class CombinePublisher {
	
	public static void main(String[] args) {
		
		
		NameGenrator.generateNames()
		.take(2)
		.subscribe(Util.getSubscriber());
		
		Flux<Integer> flux1=Flux.just(0,1,2);
		Flux<Integer> flux2=Flux.just(3,4,5);
		Flux<Integer> concatFlux=Flux.concat(flux1,flux2);
		Flux<Integer> mergedFlux=Flux.merge(flux1, flux2);
		
		mergedFlux.subscribe(Util.getSubscriber());
		concatFlux.subscribe(Util.getSubscriber());
		
	}
	

}

 class NameGenrator{
	 
	 static List<String> list=new ArrayList<>();
	public static Flux<Object> generateNames(){
		
		return Flux.generate(SynFluskSink->{
			String name=Util.getFacker().name().fullName();
			list.add(name);
			SynFluskSink.next(name);
		}).startWith(getFromCache());// cache is given the first prefernce to fetch element
		
	}
	
	public static Flux<String> getFromCache(){
		return Flux.fromIterable(list);
	}
}
