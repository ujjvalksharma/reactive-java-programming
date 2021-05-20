import java.util.stream.Stream;

public class MonoExample {
	
public static void main(String[] args) {
	
	Stream<Integer> streams=Stream.of(1,2,3,4)
			.map(num->{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return num*2;
			});
	
	streams.forEach(System.out::println); //terminal operator
}
	

}
