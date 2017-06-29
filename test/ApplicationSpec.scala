import play.api.libs.json.JsValue
import play.api.test._
import play.api.test.Helpers._
import org.scalatest.WordSpec
import org.scalatest.MustMatchers
import play.api.libs.json.JsValue
import play.api.test._
import play.api.test.Helpers._



/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends WordSpec with MustMatchers {

  
"Application" should {

    "render index template" in new WithApplication {
        val html = views.html.index("Coco")
        contentAsString(html) must include("Coco")
    }

    "render the index page" in new WithApplication{
        val home = route(FakeRequest(GET, "/")).get

        status(home) must be(OK)
        contentType(home) must be(Some("text/html"))
        contentAsString(home) must include("Tweets")
    }

    "search for tweets" in new WithApplication {
        val search = controllers.Tweets.search("typesafe")(FakeRequest())

        status(search) must be(OK)
        contentType(search) must be(Some("application/json"))
        (contentAsJson(search) \ "statuses").as[Seq[JsValue]].length must be > 0
    }

}
 
}
