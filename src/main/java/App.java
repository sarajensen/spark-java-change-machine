import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/results.vtl");
      Float moneyInput = Float.parseFloat(request.queryParams("monies"));
      ChangeMachine needChange = new ChangeMachine();
      String changeMade = needChange.makeChange(moneyInput);
      model.put("resultSpot", changeMade);
      
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
