package dev.helis.registration.rest;

import static io.restassured.RestAssured.given;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.cdi.api.DBRider;

import dev.helis.registration.RegistrationTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, alwaysCleanBefore = true)
@QuarkusTest
@QuarkusTestResource(RegistrationTestLifecycleManager.class)
@Tag("integration")
@Tag("restaurant-feature")
class RestaurantResourcesIT {

    @Test
    @DataSet(value = "/RestaurantResourcesIT/restaurant-scenario-1.yml")
    @Transactional
    void shouldFindAllRestaurants() {

        String response = given()
                .when().get(ResourcePaths.RESTAURANTS)
                .then()
                .statusCode(200)
                .extract().asString();

        JsonApprovals.verifyJson(response);
    }

}
