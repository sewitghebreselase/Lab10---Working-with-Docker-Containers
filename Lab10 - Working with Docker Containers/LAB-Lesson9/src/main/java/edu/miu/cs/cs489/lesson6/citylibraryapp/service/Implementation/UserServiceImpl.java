package edu.miu.cs.cs489.lesson6.citylibraryapp.service.Implementation;

import edu.miu.cs.cs489.lesson6.citylibraryapp.dao.UserRepository;
import edu.miu.cs.cs489.lesson6.citylibraryapp.models.User;
import edu.miu.cs.cs489.lesson6.citylibraryapp.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User users) {
        return userRepository.save(users);
    }
}
