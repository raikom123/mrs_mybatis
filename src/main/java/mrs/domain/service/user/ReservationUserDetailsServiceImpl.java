package mrs.domain.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mrs.domain.model.User;
import mrs.domain.repository.UserRepository;

@Service
public class ReservationUserDetailsServiceImpl implements ReservationUserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public ReservationUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " is not found."));
        return new ReservationUserDetails(user);
    }

}
