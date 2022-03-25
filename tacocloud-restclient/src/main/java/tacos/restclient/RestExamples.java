package tacos.restclient;

import java.net.URI;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Taco;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

  public static void main(String[] args) {
    SpringApplication.run(RestExamples.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
    return args -> {
      System.out.println("----------------------- GET -------------------------");
      System.out.println("GETTING INGREDIENT BY IDE");
      System.out.println("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"));
      System.out.println("GETTING ALL INGREDIENTS");
      List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
      System.out.println("All ingredients:");
      for (Ingredient ingredient : ingredients) {
        System.out.println("   - " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      System.out.println("----------------------- PUT -------------------------");
      Ingredient before = tacoCloudClient.getIngredientById("LETC");
      System.out.println("BEFORE:  " + before);
      tacoCloudClient.updateIngredient(new Ingredient("LETC", "Shredded Lettuce", Ingredient.Type.VEGGIES));
      Ingredient after = tacoCloudClient.getIngredientById("LETC");
      System.out.println("AFTER:  " + after);
    };
  }
  
  @Bean
  public CommandLineRunner addAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      System.out.println("----------------------- POST -------------------------");
      Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
      Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
      System.out.println("AFTER=1:  " + chixAfter);
//      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
//      URI uri = tacoCloudClient.createIngredient(beefFajita);
//      log.info("AFTER-2:  " + uri);      
//      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
//      Ingredient shrimpAfter = tacoCloudClient.createIngredient(shrimp);
//      log.info("AFTER-3:  " + shrimpAfter);      
    };
  }

  
  @Bean
  public CommandLineRunner deleteAnIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      System.out.println("----------------------- DELETE -------------------------");
      // start by adding a few ingredients so that we can delete them later...
      Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
      tacoCloudClient.createIngredient(beefFajita);
      Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
      tacoCloudClient.createIngredient(shrimp);

      
      Ingredient before = tacoCloudClient.getIngredientById("CHIX");
      System.out.println("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      Ingredient after = tacoCloudClient.getIngredientById("CHIX");
      System.out.println("AFTER:  " + after);
      before = tacoCloudClient.getIngredientById("BFFJ");
      System.out.println("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      after = tacoCloudClient.getIngredientById("BFFJ");
      System.out.println("AFTER:  " + after);
      before = tacoCloudClient.getIngredientById("SHMP");
      System.out.println("BEFORE:  " + before);
      tacoCloudClient.deleteIngredient(before);
      after = tacoCloudClient.getIngredientById("SHMP");
      System.out.println("AFTER:  " + after);
    };
  }
  
  //
  // Traverson examples
  //
  
  @Bean
  public Traverson traverson() {
    Traverson traverson = new Traverson(
        URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
    return traverson;
  }
  
  @Bean
  public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient) {
    return args -> {
      Iterable<Ingredient> ingredients = tacoCloudClient.getAllIngredientsWithTraverson();
      System.out.println("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
      for (Ingredient ingredient : ingredients) {
        System.out.println("   -  " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner traversonSaveIngredient(TacoCloudClient tacoCloudClient) {
    return args -> {
      Ingredient pico = tacoCloudClient.addIngredient(
          new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
      List<Ingredient> allIngredients = tacoCloudClient.getAllIngredients();
      System.out.println("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
      for (Ingredient ingredient : allIngredients) {
        System.out.println("   -  " + ingredient);
      }
      tacoCloudClient.deleteIngredient(pico);
    };
  }
  
  @Bean
  public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
    return args -> {
      Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
      System.out.println("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------");
      for (Taco taco : recentTacos) {
        System.out.println("   -  " + taco);
      }
    };
  }

}
