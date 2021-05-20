import reactor.core.publisher.Flux;
import util.Util;

public class FluxCreate {

	public static void main(String[] args) {
	
		// programtically emitting items
		// flusk sink can emit for multiple items
		Flux.create(fluxSink->{
			
			//we can condition to keep adding element to fluxink.next() and condition for calling complete method
			fluxSink.next(1);
			fluxSink.next(2);
			fluxSink.complete();
			fluxSink.next(3); // this will not be recieved as job is completed
			
		}).subscribe(Util.getSubscriber()); // subscribe is a overloaded method that accepts another subscriber too
		
		
		DefaultFluxSink defaultFluxSink=new DefaultFluxSink();
		Flux.create(defaultFluxSink)
		.subscribe(Util.getSubscriber());
		
		// use to take command to limit subscriber and publisher take limit
		Flux.range(0, 10)
		.log()
		.take(3)
		.log()
		.subscribe(Util.getSubscriber("take-subscriber"));
		
	Flux.create(fluxSink->{
			
			//we can condition to keep adding element to fluxink.next() and condition for calling complete method
		if(!fluxSink.isCancelled()){
			fluxSink.next(1);
			fluxSink.next(2);
			fluxSink.complete();
			fluxSink.next(3); // this will not be recieved as job is completed
		}
		}).subscribe(Util.getSubscriber());
		
		//Flux.generate is used for synchronous sink for emiting one item
	
	Flux.generate(synschronousSink->{
		synschronousSink.next(Util.getFacker().name().fullName());
		
		for(int i=0;i<10;i++) {
			if(i<5) {
				synschronousSink.next(i);
			}else {
				synschronousSink.complete();
			}
		}
	})
	.take(2) // limit syn for non-infinite loop
	.subscribe(Util.getSubscriber());
	
	// maintain state in flux generate
	
	Flux.generate(()->1, (counter,sink)->{
		sink.next(counter);
		if(counter==3) {
			sink.complete();
		}
		
		return counter+1;
	});
	
	}
}
