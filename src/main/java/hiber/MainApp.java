package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Lexus LX", 460);
      Car car2 = new Car("ВАЗ 2105", 5);


      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car1));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car2));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car1));

      List<User> users1 = userService.listUsers();
      List<User> users2 = userService.getUsersByCar("Lexus LX", 460);

      System.out.println("----------listUsers------------");
      printUsersList(users1);

      System.out.println();
      System.out.println("----------getUsersByCar------------");
      printUsersList(users2);

      context.close();
   }

   private static void printUsersList(List <User> users) {
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getUserCar());
         System.out.println();
      }
   }
}
