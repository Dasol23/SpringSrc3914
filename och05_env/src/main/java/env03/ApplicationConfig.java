package env03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfig {//어노테이션을 이용해 값을 셋팅
	@Value("${admin.id}")
	private String adminId;
	@Value("${admin.pw}")
	private String adminPW;
	@Value("${sub_admin.id}")
	private String sub_adminId;
	@Value("${sub_admin.pw}")
	private String sub_adminPw;

	@Bean         //이 객체는 생성자보다 먼저 실행됨
	public static PropertySourcesPlaceholderConfigurer properties() { 
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		System.out.println("2. Properties Run");
		Resource[] locations = new Resource[2];
		locations[0] = new ClassPathResource("admin3.properties");
		locations[1] = new ClassPathResource("sub_admin3.properties");
//System.out.println("propertysourcesPlaceholderConfigurer1 adminId->"+adminId);	
		configurer.setLocations(locations);

		return configurer;

	}

	@Bean
	public AdminConnection adminConfig() {
		AdminConnection adminConnection = new AdminConnection();
		System.out.println("3.adminConfig Run");
		adminConnection.setAdminId(adminId);
		adminConnection.setAdminPw(adminPW);
		adminConnection.setSub_adminId(sub_adminId);
		adminConnection.setSub_adminPw(sub_adminPw);
		return adminConnection;
	}
}
