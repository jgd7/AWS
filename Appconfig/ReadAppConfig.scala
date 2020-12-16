def readConfigFile(app: String, env: String, config: Stringm, id: String): Properties = {

  val config = new Properties()

  logger.info("Reading configuration from AppConfig")

  // Download the configuration from appconfig into a stream
  val appConfigClient = AppConfigClient.create()
  val appConfig = appConfigClient.getConfiguration(GetConfigurationRequest.builder()
    .application(app)
    .environment(env)
    .configuration(config)
    .clientId(id).build())
  val inputStream = appConfig.content().asInputStream()

  if (inputStream != null) {
    config.load(inputStream)
    logger.info("Configuration read: '%s'".format(config))
    return config
  }
  else {
    val msg = "property not found in the classpath"
    throw new FileNotFoundException(msg)
  }
}