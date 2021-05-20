package util;

import java.util.function.Consumer;

import org.reactivestreams.Subscriber;

import com.github.javafaker.Faker;

public class Util {

  private final static Faker faker = Faker.instance();
   
   public static Faker getFacker() {
	   return faker;
   }

   
   public static  Consumer<Object> onNext() {
	   return o->System.out.println("Recieved:"+o);
	    
   }
   
   public static  Consumer<Throwable> onError() {
	   return o->System.out.println("Error:"+o.getMessage());
	    
   }
   
   public static  Runnable onComplete() {
	   return ()->System.out.println("Completed");
	    
   }
   
	public static void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds*100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	public static Subscriber<Object> getSubscriber(){
		return new DefaultSubscriber();
	}
	public static Subscriber<Object> getSubscriber(String name){
		return new DefaultSubscriber(name);
	}
}
