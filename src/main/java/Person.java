import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.Util;

@Getter
@Setter
@ToString
public class Person {

	String name;
	int age;
	
	Person(){
		this.name=Util.getFacker().name().fullName();
		this.age=Util.getFacker().random().nextInt(1, 10);
	}
	
}
