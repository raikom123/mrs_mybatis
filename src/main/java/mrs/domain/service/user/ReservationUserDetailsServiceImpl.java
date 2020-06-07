package mrs.domain.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mrs.domain.mapper.mybatis.UsrMapper;
import mrs.domain.model.mybatis.Usr;


@Service
public class ReservationUserDetailsServiceImpl implements ReservationUserDetailsService {

    private UsrMapper usrMapper;

    @Autowired
    public ReservationUserDetailsServiceImpl(UsrMapper usrMapper) {
        this.usrMapper = usrMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usr usr = Optional.ofNullable(usrMapper.selectByPrimaryKey(username)).orElseThrow(
                () -> new UsernameNotFoundException(username + " is not found."));
        return new ReservationUserDetails(usr);
    }

}
