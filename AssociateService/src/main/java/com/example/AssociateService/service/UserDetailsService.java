package com.example.AssociateService.service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AssociateRepository associateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Associate associate = associateRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(associate.getUsername(), associate.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(associate.getRole())));
    }
}