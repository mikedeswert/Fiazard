package be.craftsmen.fiazard.managing.resource;

import static be.craftsmen.fiazard.managing.resource.OpeningHourResourceV1.OPENING_HOUR_BASE_URI;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import be.craftsmen.fiazard.main.FiazardApp;
import be.craftsmen.fiazard.main.FiazardConfig;
import be.craftsmen.fiazard.managing.representation.openinghour.OpeningHourR;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class OpeningHourResourceV1IntegrationTest {
	@ClassRule
	public static final DropwizardAppRule<FiazardConfig> appRule =
			new DropwizardAppRule<>(FiazardApp.class,
					"src/main/resources/dev.yml");
	public static final String BASE_URL = "http://localhost:8080";

	@Test
	public void openingHoursAreReturnedAsJSON() throws Exception {
		ClientResponse clientResponse = new Client()
				.resource(BASE_URL)
				.path(OPENING_HOUR_BASE_URI)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.get(ClientResponse.class);

		assertThat(clientResponse.getEntity(OpeningHourR[].class)).isNotEmpty();
	}

}