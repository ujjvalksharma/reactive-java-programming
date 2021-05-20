import java.util.stream.Stream;

import reactor.core.publisher.Mono;
import util.Util;

public class MonoJust {

	
public static void main(String[] args) {
	
	//Mono<Integer> mono=Mono.just(1); //publisher
	
	Mono<Integer> mono=Mono.just("Hi")
			.map(str-> str.length()/0);
	
	//mono.subscribe(i->System.out.println(i)); // access mono by subscribing
	
	mono.subscribe(
        i->System.out.println("num:"+i)
		,err->System.out.println(err)
        ,()->System.out.println("completed")
        );

	
	verifyUserName(Util.getFacker().name().firstName())
	.subscribe(  i->System.out.println("num:"+i)
			,err->System.out.println(err)
	        ,()->System.out.println("completed"));
}

private static Mono<String> verifyUserName(String name){
	if(name.length()<5) {
		return Mono.just("ok");
		
	}else if(name.length()==5) {
		return Mono.empty();
	}
	
	 return Mono.error(new Throwable("length is out of range"));
}

}
