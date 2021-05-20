import java.time.Duration;
import java.util.stream.Stream;

import reactor.core.publisher.Flux;
import util.Util;

public class HotAndColdPublisher {

	public static void main(String[] args) {
		
		// cold publisher
		/*
		Flux<String> fluxOfScenes= Flux.fromStream(()->getMovieScenes())
		.delayElements(Duration.ofMillis(2));
		
		fluxOfScenes.subscribe(Util.getSubscriber("sam"));
		
		Util.sleepSeconds(5);
		
		fluxOfScenes.subscribe(Util.getSubscriber("mike"));
		
		Util.sleepSeconds(60);
		*/
		
		//hot publisher
	/*	Flux<String> fluxOfScenes= Flux.fromStream(()->getMovieScenes())
				.delayElements(Duration.ofMillis(2))
				.share(); // share function makes publisher hot
				
				fluxOfScenes.subscribe(Util.getSubscriber("sam"));
				
				Util.sleepSeconds(5);
				
				fluxOfScenes.subscribe(Util.getSubscriber("mike"));
				
				Util.sleepSeconds(60);
				*/
				// we use ref count so that the publisher will publish when it has minimum subscriber
		
		
		Flux<String> fluxOfScenes= Flux.fromStream(()->getMovieScenes())
				.delayElements(Duration.ofMillis(2))
				.publish()
				.refCount(2)// minimum needed subscriber
				.cache(); // give missing value to new subscriber who missed particular data. it stores like a cache
				
				fluxOfScenes.subscribe(Util.getSubscriber("sam"));
				
				Util.sleepSeconds(5);
				
				fluxOfScenes.subscribe(Util.getSubscriber("mike"));
				
				Util.sleepSeconds(60);
	}
	
	public static Stream<String> getMovieScenes(){
		return Stream.of("scene1"
				,"scene-2"
				,"scene-3"
				,"scene-4"
				,"scene-5"
				,"scene-6"
				
				);
	}
}
