package academy.devdojo.config;

import external.dependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {
    @Bean(name = "connectionMySQL")
    public Connection connectionMysql() {
        return new Connection("localhost", "rootMySQL", "root");
    }

    @Bean(name = "connectionMongoDB")
    public Connection connectionMongo() {
        return new Connection("localhost", "rootMongoDB", "root");
    }
}
