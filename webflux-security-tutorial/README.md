Spring Security

### SecurityWebFilterChain
이 안에서 .addFilterAt(SomeFilter, AUTHENTICATION)

요기에 추가

### AuthenticationWebFilter
- Manager
  - DetailsService
- Converter
- Success Handler
- Failure Handler
- Matcher
- Repository


---


- 요청이 들어오면 setRequiresAuthenticationMatcher 여기에 매칭되는지 확인
  - 매칭되는 경우 Converter 를 통해 Exchange 가 Authentication 객체로 변경됨
  - 매칭되지 않는 경우 끝
- Converter 가 이 요청 (Exchange) 을 Authentication 객체로 변환함
- 위에서 변환된 Authentication 객체를 받아서 ReactiveAuthenticationManager 가 인증됐는지 안됐는지를 판별하고(Attempts to authenticate the provided Authentication) (authenticate method) 
- 인증 성공 시 ServerAuthenticationSuccessHandler 호출 / 실패 시ServerAuthenticationFailureHandler 호출




```java
class ServerFormLoginAuthenticationConverter {
  //...
  @Override
  @Deprecated
  public Mono<Authentication> apply(ServerWebExchange exchange) {
    return exchange.getFormData().map(this::createAuthentication);
  }

  private UsernamePasswordAuthenticationToken createAuthentication(MultiValueMap<String, String> data) {
    String username = data.getFirst(this.usernameParameter);
    String password = data.getFirst(this.passwordParameter);
    return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
  }
  // ...
}
```

```WebSessionServerSecurityContextRepository```


```java
public abstract class AbstractUserDetailsReactiveAuthenticationManager{
// ...
  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    String username = authentication.getName();
    String presentedPassword = (String) authentication.getCredentials();
    // @formatter:off
    return retrieveUser(username)
            .doOnNext(this.preAuthenticationChecks::check)
            .publishOn(this.scheduler)
            .filter((userDetails) -> this.passwordEncoder.matches(presentedPassword, userDetails.getPassword()))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("Invalid Credentials"))))
            .flatMap((userDetails) -> upgradeEncodingIfNecessary(userDetails, presentedPassword))
            .doOnNext(this.postAuthenticationChecks::check)
            .map(this::createUsernamePasswordAuthenticationToken);
    // @formatter:on
  }
}
// ...	
```

```java

public class UserDetailsRepositoryReactiveAuthenticationManager
		extends AbstractUserDetailsReactiveAuthenticationManager {

	private ReactiveUserDetailsService userDetailsService;

	public UserDetailsRepositoryReactiveAuthenticationManager(ReactiveUserDetailsService userDetailsService) {
		Assert.notNull(userDetailsService, "userDetailsService cannot be null");
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected Mono<UserDetails> retrieveUser(String username) {
		return this.userDetailsService.findByUsername(username);
	}

}
```

userDetailService 에서 findByUsername 메소드를 구현할 때 ```User.withUserDetails(result).build()``` 이런 식으로 리턴할 수 있는데, 비밀번호 암호화는
build() method 에서 알아서 해줌!!
```java
public static final class UserBuilder {
    // ...
  public UserDetails build() {
    String encodedPassword = this.passwordEncoder.apply(this.password); // << 이 부분
    return new User(this.username, encodedPassword, !this.disabled, !this.accountExpired,
            !this.credentialsExpired, !this.accountLocked, this.authorities);
  }
  // ...
}
```