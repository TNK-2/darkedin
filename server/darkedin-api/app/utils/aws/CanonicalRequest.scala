package utils.aws

import java.net.{URI, URLEncoder}
import java.nio.charset.Charset

import org.apache.http.client.utils.{URIBuilder, URLEncodedUtils}
import play.api.Logger
import play.api.libs.ws.WSRequest

import scala.collection.JavaConverters._

/**
  * Canonical Request is described as the first task when signing aws requests (version 4)
  * See <a href="http://docs.aws.amazon.com/general/latest/gr/sigv4-create-canonical-request.html">canonical request documentation</a>
  *
  * In summary, the canonical request is a string formed by the concatenation of the result of the following steps.
  *
  * <ul>
  * <li> Request method, upper case;
  * <li> Uri in a canonical format (absolute path component of the URI);
  * <li> Query string in a canonical format;
  * <li> Headers to sign in a canonical format (key=value&);
  * <li> Concatenation of the headers to sign;
  * <li> Hash of the request payload (hash of empty string if none is found);
  * </ul>
  */
private[aws] object CanonicalRequest {

  private val ignoredHeaders = List("connection", "content-length")
  // val logger = Logger(this.getClass)

  def apply(wsRequest: WSRequest, requestBodyOpt: Option[String]): CanonicalRequest = {
    val method = wsRequest.method
    val uri = canonicalUri(wsRequest)
    val query = canonicalQueryString(wsRequest)
    val headers = canonicalHeaders(wsRequest)
    val signed = signedHeaders(wsRequest)
    val payload = requestBodyOpt.map(hashedPayload(_)).getOrElse("")
    // logger.warn(s"method:${method} ,uri:${uri} ,query:${query} ,headers:${headers} ,signed:${signed} :: ats-core.CanonicalRequest")
    CanonicalRequest(method, uri, query, headers, signed, payload)
  }

  private def canonicalUri(wsRequest: WSRequest): String = {

    val uri = new URIBuilder().setPath(wsRequest.uri.getPath).build().getPath

    val normalizedPath = uri.split("\\?")(0) //using URIBuilder to normalize uri but need to split manually
    normalizedPath
      .split("(?<!/)/(?!/)", -1)
      .map(URLEncoder.encode(_, "utf-8"))
      .mkString(start = "", sep = "/", end = "")
      .replace("*", "%2A")
  }

  private def canonicalQueryString(wsRequest: WSRequest): String = {
    val uri = new URI(wsRequest.url)
    val parameters = URLEncodedUtils.parse(uri, Charset.forName("utf-8"))
    parameters.asScala.sortBy(_.getName).map(h ⇒ s"${h.getName}=${h.getValue}").mkString("&")
  }

  private def canonicalHeaders(wsRequest: WSRequest): String =
    wsRequest
      .headers
      .toSeq
      .sortBy(_._1.toLowerCase)
      .filterNot(h ⇒ ignoredHeaders.contains(h._1.toLowerCase))
      .map(h ⇒ s"${h._1.toLowerCase}:${h._2.map(_.trim).mkString(",").trim}")
      .mkString("\n")

  private def signedHeaders(wsRequest: WSRequest): String =
    wsRequest
      .headers
      .map(_._1.toLowerCase)
      .filterNot(ignoredHeaders.contains(_))
      .toSeq
      .sortBy(_.toLowerCase)
      .mkString(";")

  private def hashedPayload(requestBody: String): String = {
    def hashPayloadString(str: String) = {
      // logger.warn(s"before hashed payload : ${str} :: ats-rds2es.CanonicalRequest")
      val hashedPayload = Aws4Cripto.hash(str)
      Aws4Cripto.hexOf(hashedPayload).toLowerCase()
    }

    hashPayloadString(requestBody)
  }
}

case class CanonicalRequest(
  method: String,
  canonicalUri: String,
  canonicalQueryString: String,
  canonicalHeaders: String,
  signedHeaders: String,
  hashedPayload: String
) {

  override def toString() =
    s"""$method
       |$canonicalUri
       |$canonicalQueryString
       |$canonicalHeaders
       |
       |$signedHeaders
       |$hashedPayload""".stripMargin

  def toHashString() = {
    val canonicalRequestHash = Aws4Cripto.hash(toString)
    Aws4Cripto.hexOf(canonicalRequestHash)
  }
}
