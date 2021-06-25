package utils.aws

import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}

import play.api.Logger
import play.api.libs.ws.WSRequest
import software.amazon.awssdk.auth.credentials.{AwsCredentials, AwsCredentialsProvider, AwsCredentialsProviderChain, AwsSessionCredentials, DefaultCredentialsProvider}
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain

/**
  * AWS request signer (version 4) that follows the documentation given by amazon
  * See <a href="http://docs.aws.amazon.com/general/latest/gr/sigv4_signing.html">request signing documentation</a>
  *
  */
object Aws4RequestSigner {
  private val provider: AwsCredentialsProvider = AwsCredentialsProviderChain.builder.credentialsProviders(DefaultCredentialsProvider.create()).build
  private val region: Region = new DefaultAwsRegionProviderChain().getRegion
  private val service: String = "es"
  require(provider.resolveCredentials != null, "AWS Credentials are mandatory. AwsCredentialsProvider provided none.")

  val dateHeader = "X-Amz-Date"
  val authHeader = "Authorization"
  val securityTokenHeader = "X-Amz-Security-Token"
  // val logger = Logger(this.getClass)

  def withAws4HeadersForESS(wsRequest: WSRequest, requestBodyOpt: Option[String] = None): WSRequest = {

    val credentials = provider.resolveCredentials

    val (date, dateTime) = buildDateAndDateTime()

    val tempWsRequest = setHostHeader(wsRequest)
      .addHttpHeaders((dateHeader, dateTime))

    val canonicalRequest = CanonicalRequest(tempWsRequest, requestBodyOpt)

    val stringToSign = StringToSign(service, region, canonicalRequest, date, dateTime)

    val authHeaderValue = buildAuthenticationHeader(canonicalRequest, stringToSign, credentials)

    val signedWsRequest = credentials match {
      case c: AwsSessionCredentials => tempWsRequest
        .addHttpHeaders((authHeader, authHeaderValue))
        .addHttpHeaders((securityTokenHeader, c.sessionToken))
      case _ => tempWsRequest
        .addHttpHeaders((authHeader, authHeaderValue))
    }

//    logger.warn(s"created CanonicalUri : ${canonicalRequest.canonicalUri}")
//    logger.warn(s"created CanonicalRequest : ${canonicalRequest.toString()}")
//    logger.warn(s"created wsRequestHeader : ${signedWsRequest.headers.toString}")
//    logger.warn(s"created wsRequestUrl : ${signedWsRequest.url}")

    signedWsRequest
  }

  /* Build date and dateTime in a protected method so it is possible to override it in tests */
  protected def buildDateAndDateTime(): (String, String) = {
    val now = ZonedDateTime.now(ZoneOffset.UTC)
    val dateTime = now.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"))
    val date = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    (date, dateTime)
  }

  private def buildAuthenticationHeader(
    canonicalRequest: CanonicalRequest,
    stringToSign: StringToSign,
    credentials: AwsCredentials
  ) = {
    val credentialStr = s"Credential=${credentials.accessKeyId}/${stringToSign.credentialsScope}"
    val signedHeadersStr = s"SignedHeaders=${canonicalRequest.signedHeaders}"
    val signatureStr = s"Signature=${buildSignature(stringToSign, credentials)}"
    s"${Aws4Cripto.Algorithm} $credentialStr, $signedHeadersStr, $signatureStr"
  }

  private def buildSignature(stringToSign: StringToSign, credentials: AwsCredentials) = {
    val signatureKey = buildKeyToSign(stringToSign.date, credentials)
    val signature = Aws4Cripto.sign(stringToSign.toString, signatureKey)
    Aws4Cripto.hexOf(signature).toLowerCase
  }

  private def buildKeyToSign(dateStr: String, credentials: AwsCredentials): Array[Byte] = {
    val kSecret = ("AWS4" + credentials.secretAccessKey).getBytes("utf-8")
    val dateKey = Aws4Cripto.sign(dateStr, kSecret)
    val regionKey = Aws4Cripto.sign(region.id, dateKey)
    val serviceKey = Aws4Cripto.sign(service, regionKey)
    Aws4Cripto.sign("aws4_request", serviceKey)
  }

  /* If host header is not found: should create new  Host header. Currently could not retrieve host from Apache HttpRequest.*/
  private def setHostHeader: WSRequest => WSRequest = {
    val found = (headerValue: String) => headerValue.replaceFirst(":[0-9]+", "")
    setHeader("Host", found)
  }

  private def setHeader(h: String, f: String => String)(request: WSRequest): WSRequest = {
    request.headers.find(_._1 == h) match {
      case Some(header) ⇒ header._2.map(headerValue =>
        request.addHttpHeaders(
          (header._1, f(headerValue))
        )
      )
      case _ ⇒
    }
    request
  }

  /**
    * String to sign is described as the second task when signing aws requests (version 4).
    * See <a href="http://docs.aws.amazon.com/general/latest/gr/sigv4-create-string-to-sign.html">String to sign documentation</a>
    */
  private case class StringToSign(
    service: String,
    region: Region,
    canonicalRequest: CanonicalRequest,
    date: String,
    dateTime: String,
    algorithm: String = Aws4Cripto.Algorithm
  ) {

    val credentialsScope = s"$date/${region.id}/$service/aws4_request"

    override def toString(): String =
      s"""$algorithm
         |$dateTime
         |$credentialsScope
         |${canonicalRequest.toHashString.toLowerCase}""".stripMargin
  }
}
