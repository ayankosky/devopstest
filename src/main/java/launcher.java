
import com.revature.endpoints.Endpoints;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class launcher {

	public static void main(String[] args) {
		Javalin app = Javalin.create().start(3000);

		app.config.addStaticFiles("/views", Location.CLASSPATH);

		Endpoints.init(app);
	}
}
