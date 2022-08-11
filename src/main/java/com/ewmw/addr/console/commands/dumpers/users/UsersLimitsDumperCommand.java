package com.ewmw.addr.console.commands.dumpers.users;

import com.ewmw.addr.console.commands.kernel.AbstractConsoleCommand;
import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.models.User;
import com.ewmw.addr.models.UserLimit;
import com.ewmw.addr.models.reposotories.UserLimitsRepository;
import com.ewmw.addr.models.reposotories.UsersRepository;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@NoArgsConstructor
public class UsersLimitsDumperCommand extends AbstractConsoleCommand {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserLimitsRepository userLimitsRepository;

    public UsersLimitsDumperCommand(String[] args) {
        super(args);
    }

    @Override
    public void run() throws Exception {
        System.out.println(args.length);
        if (args.length < 1) {
            System.out.println("Not enough args: user id not passed");
            return;
        }

        Long userId = Long.parseLong(args[0]);

        User user = usersRepository.findById(userId).orElseThrow();
        List<UserLimit> limits = user.getLimits();
        System.out.println(limits);
//
//        Random random = new Random();
//        User user = new User();
//        user.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");
//        user.setFirstName("Sanya_" + random.nextInt());
//        user.setLastName("Kruti_" + random.nextInt());
//        user.setPassword(RandomStringUtils.randomAlphabetic(10));
//
//        UserLimit userLimit = new UserLimit();
//        userLimit.setLimitId(12);
//        userLimit.setQueriesLeft(199);
//        userLimit.setUser(user);
//
//        user.setLimits(List.of(userLimit));
//
//        usersRepository.save(user);
    }
}
