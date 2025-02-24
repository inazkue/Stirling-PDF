package stirling.software.SPDF.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
	    String version = getClass().getPackage().getImplementationVersion();
	    if (version == null) {
	        Properties props = new Properties();
	        try (InputStream input = getClass().getClassLoader().getResourceAsStream("version.properties")) {
	            props.load(input);
	            version = props.getProperty("version");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            version = "1.0.0"; // default version if all else fails
	        }
	    }

	    return new OpenAPI().components(new Components()).info(
	            new Info().title("Stirling PDF API").version(version).description("API documentation for all Server-Side processing.\nPlease note some functionality might be UI only and missing from here."));
	}


}
