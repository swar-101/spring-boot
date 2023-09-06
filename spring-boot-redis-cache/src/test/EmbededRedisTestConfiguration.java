package test;
@TestConfiguration
public static class EmbededRedisTestConfiguration {

  private final redis.embedded.RedisServer redisServer;

  public EmbededRedisTestConfiguration(@Value("${spring.redis.port}") final int redisPort) throws IOException {
    this.redisServer = new redis.embedded.RedisServer(redisPort);
  }

  @PostConstruct
  public void startRedis() {
    this.redisServer.start();
  }

  @PreDestroy
  public void stopRedis() {
    this.redisServer.stop();
  }
}