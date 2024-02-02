package edu.sru.MusicLibrary.secure;

import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@Lazy
class SpringValidatorConfiguration {

	@Bean
	@Lazy
	public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(final Validator validator) {
		return new HibernatePropertiesCustomizer() {

			@Override
			public void customize(Map<String, Object> hibernateProperties) {
				hibernateProperties.put("jakarta.persistence.validation.factory", validator);
			}
		};
	}
}