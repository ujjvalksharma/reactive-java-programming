import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import util.Util;

public class BackPressureAndOverFlow {
	
	public static void main(String args[]) {
		
		Flux.create(fluxSink->{
			
			for(int i=0;i<500;i++) {
				fluxSink.next(i);
				System.out.println("pushed:"+i);
			//	Util.sleepSeconds(10);
			}
			fluxSink.complete();
		})
	//	.onBackpressureBuffer() used to buffer
	//	.onBackpressureDrop() // drop extra values if queue size is full, we can call a consumer so that some can have a stragregy to save drop values like save in files or database or etc and fetch it later
	//	.onBackpressureLatest() //lastest is just like  drop
	//	.onBackpressureError() throw error
		.onBackpressureBuffer(20)// sets queue size for buffer
		.publishOn(Schedulers.boundedElastic())
		.doOnNext(i->{
			//Util.sleepSeconds(10); // the problem is if thing sleep the main function is completed as the job is resumed to main function and one thread is working
		}).subscribe(Util.getSubscriber());
		
	//	Util.sleepSeconds(60);
	}

}
