package week6.day2.code;

import java.lang.reflect.Type;

import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

public class SchemaGeneratorUtility {
	
	public static String generateJsonSchema(Type pojoType) {
		SchemaGeneratorConfig config = new 
				SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON).build();
		SchemaGenerator schemaGenerator = new SchemaGenerator(config);		
		return schemaGenerator.generateSchema(pojoType).toPrettyString();		
	}

}