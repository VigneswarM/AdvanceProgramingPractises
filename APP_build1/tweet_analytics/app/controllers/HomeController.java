/**
 * @author Simarpreet Singh
 */
package controllers;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.mvc.*;

import play.twirl.api.Html;
import views.html.*;
import views.html.home;

/**
 * @author Prasanth, Vaishnavi, Anil, Simarpreet
 *
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * Renders the home page asynchronously and returns a {@link CompletionStage} object
     * with the Result embbeded in it.
     *
     * @return A Response of type CompletionStage<Result> with the rendered html home page (Result)
     * available as a Future Object
     */
    public CompletionStage<Result> index() {
        return getFutureResult().thenApplyAsync(Results::ok);
    }

    /**
     * Renders the home page asynchronously and returns a {@link CompletionStage} object with HTML object available as
     * a future object.
     *
     * @return A Response of type CompletionStage<Html>
     */
    private CompletionStage<Html> getFutureResult() {
        return CompletableFuture.supplyAsync(() -> home.render());
    }
}
