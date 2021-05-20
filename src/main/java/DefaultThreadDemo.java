import reactor.core.publisher.Flux;

public class DefaultThreadDemo {
	
	public static void main(String[] args) {
		
		Flux<Object> flux=Flux.create(fluxSink->{
			
			printThreadMain("create");
			fluxSink.next(1);
		}).doOnNext(i->printThreadMain("next"+i));
		
		flux.subscribe(v->printThreadMain("sub"+v));
	}
	private static void printThreadMain(String msg) {
		System.out.println(Thread.currentThread().getName()+"- msg: "+msg);
	}

}
