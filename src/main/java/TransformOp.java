import java.util.function.Function;

import reactor.core.publisher.Flux;
import util.Util;

public class TransformOp {

	public static void main(String[] args) {
		
		getPersons()
           .transform(applyFilterMap())
           .subscribe(Util.getSubscriber());
		
	}
	
	public static Flux<Person> getPersons(){
		return Flux.range(0, 20)
				.map(i-> new Person());
	}
	
    public static Function<Flux<Person>, Flux<Person>> applyFilterMap(){
        return flux -> flux
                            .filter(p -> p.getAge() > 10)
                            .doOnNext(p -> p.setName(p.getName().toUpperCase()))
                            .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
    }
}
