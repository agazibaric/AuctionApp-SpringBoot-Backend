package com.agazibaric;

import com.agazibaric.item.ImageService;
import com.agazibaric.item.Item;
import com.agazibaric.item.ItemRepo;
import com.agazibaric.user.User;
import com.agazibaric.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AuctionAppMain extends SpringBootServletInitializer {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ImageService imageService;

    @Component
    class DataSetup implements ApplicationRunner {

        @Override
        public void run(ApplicationArguments args) throws Exception {
            imageService.init();

            List<String> img1 = new ArrayList<>();
            List<String> img2 = new ArrayList<>();
            List<String> img3 = new ArrayList<>();
            List<String> img4 = new ArrayList<>();
            List<String> img5 = new ArrayList<>();
            List<String> img6 = new ArrayList<>();
            List<String> img7 = new ArrayList<>();
            List<String> img8 = new ArrayList<>();
            img1.add("strat.jpg");
            img2.add("telly1.png");
            img3.add("classic.jpg");
            img4.add("projector.jpg");
            img5.add("basketball.jpg");
            img6.add("redmi.jpg");
            img7.add("mouse.jpg");
            img8.add("telly2.jpg");


            Item i1 = createItem("Fender Stratocaster", "1968", 1000f, Duration.ofHours(12), img1);
            Item i2 = createItem("Fender Telecaster", "1969", 1000f, Duration.ofHours(8), img2);
            Item i3 = createItem("Classical guitar", "For beginners", 1000f, Duration.ofDays(2), img3);
            Item i4 = createItem("Projector", "Mini projector", 1000f, Duration.ofMinutes(5), img4);
            Item i5 = createItem("Basketball", "Size: 5", 1000f, Duration.ofHours(24), img5);
            Item i6 = createItem("Xiaomi Redmi Note 7", "New", 1000f, Duration.ofHours(12), img6);
            Item i7 = createItem("Logitech g400", "Gaming mouse", 1000f, Duration.ofDays(4), img7);
            Item i8 = createItem("Fender Telecaster", "1970", 1000f, Duration.ofMinutes(1), img8);


            List<Item> items1 = new ArrayList<>();
            items1.add(i1);
            List<Item> items2 = new ArrayList<>();
            items2.add(i2);
            List<Item> items3 = new ArrayList<>();
            items3.add(i3);
            List<Item> items4 = new ArrayList<>();
            items4.add(i4);
            items4.add(i5);
            items4.add(i6);
            items4.add(i7);
            items4.add(i8);

            User u1 = createUser(items1, new ArrayList<>(), "nick", "ante1506@gmail.com", "pass");
            User u2 = createUser(items2, new ArrayList<>(), "nick2", "ante15062@gmail.com", "pass2");
            User u3 = createUser(items3, new ArrayList<>(), "nick3", "ante15063@gmail.com", "pass3");
            User u4 = createUser(items4, new ArrayList<>(), "nick4", "ante15064@gmail.com", "pass4");

            setUser(u1, items1);
            setUser(u2, items2);
            setUser(u3, items3);
            setUser(u4, items4);
            userRepo.save(u1);
            userRepo.save(u2);
            userRepo.save(u3);
            userRepo.save(u4);



          /*  itemRepo.save(i1);
            itemRepo.save(i2);
            itemRepo.save(i3);
            itemRepo.save(i4);
            itemRepo.save(i5);
            itemRepo.save(i6);
            itemRepo.save(i7);
            itemRepo.save(i8);*/
        }
    }

    public void setUser(User u, List<Item> items) {
        items.forEach(i -> i.setUser(u));
    }

    public User createUser(List<Item> items, List<Item> bids, String username, String email, String pass) {
        return User.builder()
                .items(items)
                .bids(bids)
                .itemsWon(new ArrayList<>())
                .username(username)
                .password(bCryptPasswordEncoder.encode(pass))
                .email(email)
                .build();
    }

    public Item createItem(String name, String des, Float minp, Duration dur, List<String> imgs) {
        return Item.builder()
                .name(name)
                .description(des)
                .minimumPrice(minp)
                .bidPrice(0.0f)
                .numberOfBids(0)
                .creationTime(LocalDateTime.now())
                .duration(dur)
                .isExpired(false)
                .images(imgs)
                .build();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AuctionAppMain.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(AuctionAppMain.class, args);
    }

}
