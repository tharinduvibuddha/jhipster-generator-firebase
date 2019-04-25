package <%= packageName %>.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("${rs.pscode.firebase.database.url}")
    private String databaseUrl;

    @Value("${rs.pscode.firebase.config.path}")
    private String configPath;
@Bean
    public FirebaseAuth firebaseAuth() throws IOException{

    //FileInputStream serviceAccount = new FileInputStream(configPath);

    InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);

    FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(inputStream))
            .setDatabaseUrl(databaseUrl).build();


    FirebaseApp.initializeApp(options);

    return FirebaseAuth.getInstance();
}




}
