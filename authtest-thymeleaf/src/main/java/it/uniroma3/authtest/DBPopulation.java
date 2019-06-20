package it.uniroma3.authtest;

import it.uniroma3.authtest.model.Funzionario;
import it.uniroma3.authtest.storage.FunzionarioRepository;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private FunzionarioRepository userRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        userRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        Funzionario admin = new Funzionario(1L, "Mario", "Rossi", "mariorossi", null, "ADMIN");
      String adminPassword = new BCryptPasswordEncoder().encode("mrpass");
      admin.setPwd(adminPassword);
      this.userRepository.save(admin);
        System.out.println("Done.\n");
    }
}
