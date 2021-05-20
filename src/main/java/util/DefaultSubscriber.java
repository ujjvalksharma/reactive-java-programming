package util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object>{

	String subscriberName;
	
	public DefaultSubscriber(String subscriberName){
		this.subscriberName=subscriberName;
	}
	
	
	public DefaultSubscriber(){
		this.subscriberName="default";
	}
	
	
	@Override
	public void onSubscribe(Subscription s) {
		s.request(Long.MAX_VALUE); // just give me the next value
		
	}

	@Override
	public void onNext(Object t) {
		System.out.println(subscriberName+" recieve: "+t);
		
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("Error:"+t.getMessage());
		
	}

	@Override
	public void onComplete() {
		System.out.println("Completed");
		
	}
	

	

}
