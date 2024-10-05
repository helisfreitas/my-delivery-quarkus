package dev.helis.registration.rest;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.approvaltests.Approvals;
import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;

import dev.helis.registration.RegistrationTestLifecycleManager;
import dev.helis.registration.entity.Category;
import dev.helis.registration.rest.dto.DishRequest;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, alwaysCleanBefore = true)
@QuarkusTest
@QuarkusTestResource(RegistrationTestLifecycleManager.class)
@Tag("integration")
@Tag("dish-feature")
class DishResourcesIT {

    @Test
    @DataSet(value = "/DishResourcesIT/dishes-scenario-2.yml")
    @Transactional
    void shouldFindAllDishesFromRestaurant() {

        String response = given()
                .when().get(ResourcePaths.DISHES, 1)
                .then()
                .statusCode(200)
                .extract().asString();

        JsonApprovals.verifyJson(response);
    }

    @Test
    @DataSet(value = "/DishResourcesIT/dishes-scenario-2.yml")
    @Transactional
    void shouldFindByIdDishesFromRestaurant() {

        String response = given()
                .when().get(ResourcePaths.DISHES + "/{id}", 1, 23)
                .then()
                .statusCode(200)
                .extract().asString();

        JsonApprovals.verifyJson(response);
    }

    @Test
    @DataSet(value = "/DishResourcesIT/dishes-scenario-2.yml")
    @ExpectedDataSet(value = "/DishResourcesIT/dishes-scenario-1.yml")
    @Transactional
    void shouldDeleteByIdDishesFromRestaurant() {

        given()
                .when().delete(ResourcePaths.DISHES + "/{id}", 1, 1)
                .then()
                .statusCode(204);

    }

    @Test
    @DataSet(value = "/DishResourcesIT/dishes-scenario-1.yml")
    @ExpectedDataSet(value = "/DishResourcesIT/dishes-scenario-2.yml")
    @Transactional
    void shouldCreateDishesFromRestaurant() {
        DishRequest request = new DishRequest();
        request.setName("X-Burguer");
        request.setPrice(new BigDecimal("9.99"));
        request.setCategory(Category.MAIN_COURSE);
        request.setIsAvailable(Boolean.TRUE);

        String response = given()
                .when().contentType("application/json").body(request)
                .post(ResourcePaths.DISHES, 1)
                .then()
                .statusCode(201)
                .extract().header("location");

        Approvals.verify(response);

    }

    @Test
    @Transactional
    void shouldShowErrorOnCreateDishesFromRestaurantWithoutName() {

        String json = "{\"category\":\"MAIN_COURSE\",\"isAvailable\":true,\"price\":9.99}";

        String response = given()
                .when().contentType("application/json").body(json)
                .post(ResourcePaths.DISHES, 1)
                .then()
                .statusCode(400)
                .extract().response().body().asString();

        JsonApprovals.verifyJson(response);

    }

    @Test
    @DataSet(value = "/DishResourcesIT/dishes-scenario-2.yml")
    @ExpectedDataSet(value = "/DishResourcesIT/dishes-scenario-2-modified.yml")
    @Transactional
    void shouldUpdateNameDishesFromRestaurant() {
        DishRequest request = new DishRequest();
        request.setName("X-Burguer Duo");
        request.setPrice(new BigDecimal("9.99"));
        request.setCategory(Category.MAIN_COURSE);
        request.setIsAvailable(Boolean.TRUE);

        given()
                .when().contentType("application/json").body(request)
                .put(ResourcePaths.DISHES + "/{id}", 1, 1)
                .then()
                .statusCode(204);

    }

}
