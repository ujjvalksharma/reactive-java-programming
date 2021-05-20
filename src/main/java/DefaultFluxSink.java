import java.util.function.Consumer;

import reactor.core.publisher.FluxSink;
import util.Util;

public class DefaultFluxSink implements Consumer<FluxSink<String>> {

	FluxSink fluxSink;
	@Override
	public void accept(FluxSink<String> t) {
		this.fluxSink=t;
		
	}
	
	public void produce() {
		this.fluxSink.next(Util.getFacker().name().firstName());
	}

}
